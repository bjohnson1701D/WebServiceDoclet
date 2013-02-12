package com.webservice.doclet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.sun.javadoc.*;
import com.sun.tools.doclets.standard.Standard;
import org.apache.commons.io.FileUtils;

public class SpringWSDoclet extends Standard {
	private static String title = "Web Services API";
	private static String fileName = "wsAPI.html";
	private static List<WebServiceClass> services;

	public static boolean start(RootDoc root) {
		services = new ArrayList<WebServiceClass>();
		readOptions(root.options());
		DocParser.addControllers(root.classes(), services);
		DocParser.addMethods(services);
		createFiles();
		return true;
	}

	/**
	 * Reads the custom command line parameters available
	 * 
	 * @param options
	 *            - The command line options
	 */
	private static void readOptions(String[][] options) {
		for (int i = 0; i < options.length; i++) {
			String[] opt = options[i];
			if (opt[0].equals("-title")) {
				title = opt[1];
			} else if (opt[0].equals("-fileName"))
				fileName = opt[1];
		}
	}

	/**
	 * Required for using and custom command line parameters
	 * 
	 * @param option
	 *            - the command line parameter
	 * @return
	 */
	public static int optionLength(String option) {
		if (option.equals("-fileName") || option.equals("-title")) {
			return 2;
		}
		return 0;
	}

	/**
	 * Creates Html & Css files required for viewing the documentation
	 */
	private static void createFiles() {
		try {
			FileUtils.writeStringToFile(new File("doclet_style.css"),
					DocletCssContent.CSS_CONTENT);
			FileUtils.writeStringToFile(new File(fileName),
					HTMLBuilder.createContent(services, title));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets language version
	 * 
	 * @return
	 */
	public static LanguageVersion languageVersion() {
		return LanguageVersion.JAVA_1_5;
	}

}
