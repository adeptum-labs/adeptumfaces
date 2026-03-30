package com.adeptum.adeptumfaces.plugin.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "facelet-taglib", namespace = FaceletTagLib.XMLNS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "description", "namespace", "shortName", "tags" })
public class FaceletTagLib {
	public static final String XMLNS = "https://jakarta.ee/xml/ns/jakartaee";
	public static final String XSI = "http://www.w3.org/2001/XMLSchema-instance";
	public static final String SCHEMA_LOCATION = "https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-facelettaglibrary_4_0.xsd";

	@XmlAttribute(name = "version")
	public String version = "4.0";

	@XmlElement(name = "description", namespace = XMLNS)
	public String description;

	@XmlElement(name = "namespace", namespace = XMLNS)
	public String namespace;

	@XmlElement(name = "short-name", namespace = XMLNS)
	public String shortName;

	@XmlElement(name = "tag", namespace = XMLNS)
	public List<Tag> tags = new ArrayList<>();
}
