package com.adeptum.adeptumfaces.plugin.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "description", "name", "required", "type", "methodSignature", "defaultValue" })
public class Attribute {
	@XmlElement(name = "name", namespace = FaceletTagLib.XMLNS)
	public String name;

	@XmlElement(name = "required", namespace = FaceletTagLib.XMLNS)
	public boolean required;

	@XmlElement(name = "type", namespace = FaceletTagLib.XMLNS)
	public String type;

	@XmlElement(name = "method-signature", namespace = FaceletTagLib.XMLNS)
	public String methodSignature;

	@XmlElement(name = "default", namespace = FaceletTagLib.XMLNS)
	public String defaultValue;

	@XmlElement(name = "description", namespace = FaceletTagLib.XMLNS)
	public String description;
}
