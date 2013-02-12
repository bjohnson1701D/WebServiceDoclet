package org.spring.webservices;

import org.apache.commons.lang.StringUtils;

public class WebServiceMethod {
	private String endpoint = "";
	private String returns = "";
	private String comment = "";
	private String methodType = "";
	private boolean isOverridden;

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint
	 *            the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint.replace("\"", "").trim();
	}

	/**
	 * @return the returns
	 */
	public String getReturns() {
		return returns;
	}

	/**
	 * @param returns
	 *            the returns to set
	 */
	public void setReturns(String returns) {
		if (returns.startsWith("-"))
			returns = returns.replace("-", "");
		returns = StringUtils.capitalize(returns);
		this.returns = returns.trim();
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		comment = StringUtils.capitalize(comment);
		this.comment = comment.trim();
	}

	/**
	 * @return the methodType
	 */
	public String getMethodType() {
		return methodType;
	}

	/**
	 * @param methodType
	 *            the methodType to set
	 */
	public void setMethodType(String methodType) {
		this.methodType = methodType.trim();
	}

	/**
	 * @return the isOverride
	 */
	public boolean isOverridden() {
		return isOverridden;
	}

	/**
	 * @param isOverride
	 *            the isOverride to set
	 */
	public void setOverridden(boolean isOverride) {
		this.isOverridden = isOverride;
	}

	public String generateTable(String mappingRoot, String className) {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr><td valign='top'>");
		sb.append(this.methodType);
		sb.append("</td><td class='nowrap' valign='top'");
		sb.append("<code>/" + mappingRoot + this.endpoint + "</code>");
		sb.append("</td><td valign='top'>");
		sb.append(this.returns);
		sb.append("</td><td valign='top'>");
		sb.append(this.comment);
		sb.append("</td></tr>");
		return sb.toString();
	}
}
