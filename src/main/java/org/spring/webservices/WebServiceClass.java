package org.spring.webservices;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.sun.javadoc.ClassDoc;

public class WebServiceClass {
	private String className = "";
	private String root = "";
	private String description = "";
	private String location = "";
	private String author = "";
	private ClassDoc classDoc;
	private List<WebServiceMethod> methods;

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the root
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * @return the methods
	 */
	public List<WebServiceMethod> getMethods() {
		return methods;
	}

	/**
	 * @param methods
	 *            the methods to set
	 */
	public void setMethods(List<WebServiceMethod> methods) {
		this.methods = methods;
	}

	/**
	 * Adds a new method to the methods set
	 * 
	 * @param method
	 *            to be added
	 */
	public void addNewMethod(WebServiceMethod method) {
		this.methods.add(method);
	}

	public ClassDoc getClassDoc() {
		return classDoc;
	}

	public void setClassDoc(ClassDoc classDoc) {
		this.classDoc = classDoc;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		description = StringUtils.capitalize(description);
		this.description = description.trim();
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLegendTitle() {
		String legend = "";
		if (this.root == null || this.root.equals("")) {
			legend = new String(this.className + " Methods").toUpperCase();
		} else {
			legend = new String(this.root + " Methods").toUpperCase();
		}
		return legend;
	}
}
