package com.webservice.doclet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class that builds the Html as a string
 * 
 * @author bjohnson
 *
 */
public class HTMLBuilder {

	public static String createContent(List<WebServiceClass> services,
			String title) {
		StringBuilder sb = new StringBuilder();
	   
	    SimpleDateFormat ft = new SimpleDateFormat ("EEEEE, MMMMM dd yyyy 'at' hh:mm:ss a");
	    
		sb.append("<!DOCTYPE html><html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"doclet_style.css\"></head><body><center><h1>"
				+ title + "</h1><b>Last Updated:</b>&nbsp;&nbsp;"+ft.format(new Date())+"</center><br/><br/>");

		for (WebServiceClass wsc : services) {
			sb.append("<fieldset><legend><center><h3><b>"
					+ wsc.getLegendTitle()
					+ "</b></h3></center></legend><div class='fieldContent'><br/><br/>");

			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td><b>Class:</b></td>");
			sb.append("<td>" + wsc.getLocation() + "</td>");
			sb.append("</tr>");
			if (!wsc.getDescription().equals("")) {
				sb.append("<tr>");
				sb.append("<td><b>Description:</b></td>");
				sb.append("<td>" + wsc.getDescription() + "</td>");
				sb.append("</tr>");
			}
			if (!wsc.getAuthor().equals("")) {
				sb.append("<tr>");
				sb.append("<td><b>Author:</b></td>");
				sb.append("<td>" + wsc.getAuthor() + "</td>");
				sb.append("</tr>");
			}
			sb.append("</table><br/>");

			sb.append("<table><tr class='firstRow'><td><b>Type</b></td><td><b>Endpoint</b></td>"
					+ "<td><b>Returns</b></td><td><b>Comments</b></td></tr>");

			for (WebServiceMethod wsm : wsc.getMethods()) {
				if (!wsm.isOverridden())
					sb.append(wsm.generateTable(wsc.getRoot(),
							wsc.getClassName()));
			}

			sb.append("</table><br/></div></fieldset><br/><br/>");
		}

		sb.append("<br/><hr><br/><center>Spring Web Services Doclet</center><br/></body></html>");
		return sb.toString();
	}
}
