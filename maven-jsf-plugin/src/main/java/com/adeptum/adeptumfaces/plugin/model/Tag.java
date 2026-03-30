package com.adeptum.adeptumfaces.plugin.model;

import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "description", "name", "componentType", "rendererType", "tagClass", "attributes" })
public class Tag {
	@XmlElement(name = "tag-name", namespace = FaceletTagLib.XMLNS)
	public String name;

	@XmlElement(name = "description", namespace = FaceletTagLib.XMLNS)
	public String description;

	@XmlElement(name = "component-type", namespace = FaceletTagLib.XMLNS)
	public String componentType;

	@XmlElement(name = "renderer-type", namespace = FaceletTagLib.XMLNS)
	public String rendererType;

	@XmlElement(name = "handler-class", namespace = FaceletTagLib.XMLNS)
	public String tagClass;

	@XmlElement(name = "attribute", namespace = FaceletTagLib.XMLNS)
	public List<Attribute> attributes;
}
