package com.adeptum.adeptumfaces.plugin;

import com.adeptum.adeptumfaces.plugin.model.FaceletTagLib;
import com.adeptum.adeptumfaces.plugin.model.Tag;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.PROCESS_CLASSES, threadSafe = true)
public class GenerateTagLibMojo extends AbstractMojo {
	private static final String FACES_COMPONENT_ANNOTATION = "jakarta.faces.component.FacesComponent";

	@Parameter(defaultValue = "${project}", readonly = true, required = true)
	private MavenProject project;

	@Parameter
	private List<String> packages = List.of("com.acme");

	@Parameter(defaultValue = "${project.build.directory}/generated-resources/META-INF/${project.artifactId}.taglib.xml")
	private File outputFile;

	@Parameter(defaultValue = "4.0")
	private String taglibVersion;

	@Parameter(defaultValue = "http://adeptumfaces.org/ui")
	private String namespace;

	@Parameter(defaultValue = "a")
	private String shortName;

	@Parameter(defaultValue = "${project.name} tag library")
	private String description;

	@Parameter(defaultValue = "true")
	private boolean includeInherited;

	@Parameter(defaultValue = "true")
	private boolean attachAsResource;

	private List<Tag> scan(File classesDir) throws MojoExecutionException {
		try (URLClassLoader classLoader = new URLClassLoader(classpathUrls(), getClass().getClassLoader());
				Stream<Path> paths = Files.walk(classesDir.toPath())) {
			return paths.filter(path -> path.toString().endsWith(".class"))
				.filter(path -> !path.getFileName().toString().contains("$"))
				.map(path -> toClassName(classesDir.toPath(), path))
				.filter(this::isSelectedPackage)
				.map(className -> loadTag(classLoader, className))
				.filter(Objects::nonNull)
				.sorted(Comparator.comparing(tag -> tag.name))
				.collect(Collectors.toList());

		} catch (IOException e) {
			throw new MojoExecutionException("Failed to scan compiled classes.", e);
		}
	}

	private URL[] classpathUrls() throws MojoExecutionException {
		try {
			final List<URL> urls = new ArrayList<>();

			for (String element : project.getCompileClasspathElements()) {
				urls.add(new File(element).toURI().toURL());
			}

			return urls.toArray(URL[]::new);

		} catch (DependencyResolutionRequiredException | MalformedURLException e) {
			throw new MojoExecutionException("Failed to build plugin classpath.", e);
		}
	}

	private String toClassName(Path classesRoot, Path classFile) {
		String relative = classesRoot.relativize(classFile).toString();

		return relative.substring(0, relative.length() - ".class".length())
			.replace(File.separatorChar, '.');
	}

	private boolean isSelectedPackage(String className) {
		if (packages == null || packages.isEmpty()) {
			return true;
		}

		return packages.stream().anyMatch(pkg -> className.startsWith(pkg + ".")
			|| className.equals(pkg));
	}

	private Tag loadTag(URLClassLoader classLoader, String className) {
		try {
			final Class<?> candidate = Class.forName(className, false, classLoader);

			if (!isFacesComponent(candidate) || Modifier.isAbstract(candidate.getModifiers())
				|| candidate.isInterface()) {
				return null;
			}

			final Tag tag = new Tag();

			tag.name = toTagName(candidate.getSimpleName());
			tag.componentType = stringConstant(candidate, "COMPONENT_TYPE", candidate.getName());
			tag.rendererType = rendererType(classLoader, candidate);
			tag.description = "Generated from " + candidate.getName();
			tag.attributes = List.of();

			return tag;

		} catch (ReflectiveOperationException | LinkageError e) {
			getLog().debug("Skipping class " + className + ": " + e.getMessage());
			return null;
		}
	}

	private boolean isFacesComponent(Class<?> candidate) {
		for (Annotation annotation : candidate.getAnnotations()) {
			if (FACES_COMPONENT_ANNOTATION.equals(annotation.annotationType().getName())) {
				return true;
			}
		}

		return false;
	}

	private String toTagName(String simpleName) {
		if (simpleName.isEmpty()) {
			return simpleName;
		}

		if (simpleName.length() == 1) {
			return simpleName.toLowerCase(Locale.ROOT);
		}

		return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
	}

	private String rendererType(URLClassLoader classLoader, Class<?> componentClass) {
		try {
			final Class<?> rendererClass = Class.forName(componentClass.getName() + "Renderer", false, classLoader);
			return stringConstant(rendererClass, "RENDERER_TYPE", null);

		} catch (ReflectiveOperationException | LinkageError e) {
			return null;
		}
	}

	private String stringConstant(Class<?> type, String fieldName, String fallback) {
		try {
			final Field field = type.getField(fieldName);

			if (field.getType() == String.class && Modifier.isStatic(field.getModifiers())) {
				return (String) field.get(null);
			}

		} catch (ReflectiveOperationException | SecurityException e) {
			getLog().debug("Unable to read " + type.getName() + "." + fieldName + ": " + e.getMessage());
		}

		return fallback;
	}

	private void writeTaglib(List<Tag> tags, File file) throws MojoExecutionException {
		final FaceletTagLib tagLib = new FaceletTagLib();

		tagLib.version = taglibVersion;
		tagLib.namespace = namespace;
		tagLib.shortName = shortName;
		tagLib.description = description;
		tagLib.tags = tags;

		final File parent = file.getParentFile();

		if (parent != null && !parent.isDirectory() && !parent.mkdirs()) {
			throw new MojoExecutionException("Failed to create output directory " + parent);
		}

		try {
			final JAXBContext context = JAXBContext.newInstance(FaceletTagLib.class);
			final Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, FaceletTagLib.SCHEMA_LOCATION);
			marshaller.marshal(tagLib, file);
			getLog().info("Generated taglib at " + file);

		} catch (JAXBException e) {
			throw new MojoExecutionException("Failed to write taglib XML.", e);
		}
	}

	private Resource resource(File directory) {
		Resource resource = new Resource();
		resource.setDirectory(directory.getAbsolutePath());
		return resource;
	}

	@Override
	public void execute() throws MojoExecutionException {
		final File classesDir = new File(project.getBuild().getOutputDirectory());

		if (!classesDir.isDirectory()) {
			getLog().info("No classes directory found, skipping.");
			return;
		}

		final List<Tag> tags = scan(classesDir);
		writeTaglib(tags, outputFile);

		if (attachAsResource) {
			project.addResource(resource(new File(project.getBuild().getDirectory(),
				"generated-resources"))
			);
		}
	}
}
