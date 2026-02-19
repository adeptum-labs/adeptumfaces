package com.adeptum.adeptumfaces.annotation;

public @interface Attribute {
	String name();
	boolean required() default false;
	String description();
}
