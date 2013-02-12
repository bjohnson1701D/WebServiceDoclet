package com.webservice.doclet;

import java.util.ArrayList;
import java.util.List;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;

/**
 * Class that actually parses all of the information about the project
 * 
 * @author bjohnson
 *
 */
public class DocParser {

	/**
	 * Adds all Spring annotated controllers to the services List
	 * 
	 * @param classes
	 * @param services
	 */
	public static void addControllers(ClassDoc[] classes,
			List<WebServiceClass> services) {
		for (int i = 0; i < classes.length; ++i) {
			WebServiceClass wsc = new WebServiceClass();
			wsc.setClassDoc(classes[i]);
			wsc.setClassName(classes[i].name());
			wsc.setMethods(new ArrayList<WebServiceMethod>());
			wsc.setDescription(classes[i].commentText());
			wsc.setLocation(classes[i].qualifiedName().toString());

			try {
				wsc.setAuthor(classes[i].tags("author")[0].text().toString());
			} catch (Exception e) {
				//No Need to do anything.
			}

			AnnotationDesc[] annotations = classes[i].annotations();
			for (int j = 0; j < annotations.length; j++) {
				if (annotations[j].annotationType().name().equals("Controller")) {
					for (int k = 0; k < annotations.length; k++) {
						if (annotations[k].annotationType().name()
								.equals("RequestMapping")) {
							ElementValuePair[] vals = annotations[k]
									.elementValues();
							for (int l = 0; l < vals.length; l++) {
								if (vals[l].toString().contains("value=")) {
									wsc.setRoot(vals[l].value().toString()
											.replace("\"", ""));
								}
							}
						}
					}
					services.add(wsc);
				}
			}
		}
	}

	/**
	 * Adds all methods (including those in superclass) which are annotated w/
	 * RequestMapping to the WebServiceClass' list of WebServiceMethods.
	 * 
	 * @param services
	 */
	public static void addMethods(List<WebServiceClass> services) {
		for (WebServiceClass wsc : services) {
			MethodDoc[] methods = wsc.getClassDoc().methods();
			for (int i = 0; i < methods.length; ++i) {
				WebServiceMethod wsm = new WebServiceMethod();
				AnnotationDesc[] annotations = methods[i].annotations();
				for (int j = 0; j < annotations.length; j++) {
					if (annotations[j].annotationType().name()
							.equals("RequestMapping")) {
						ElementValuePair[] vals = annotations[j]
								.elementValues();
						for (int l = 0; l < vals.length; l++) {
							if (vals[l].toString().contains("value=")) {
								String map = vals[l].value().toString();
								wsm.setEndpoint(map);
							} else if (vals[l].toString().contains("method=")) {
								// TODO: Does not check for annotation @POST,
								// @GET...etc
								wsm.setMethodType(vals[l]
										.value()
										.toString()
										.replace(
												"org.springframework.web.bind.annotation.RequestMethod.",
												""));
							}

						}
						try {
							String returnComment = methods[i].tags("return")[0]
									.toString();
							returnComment = returnComment.replace("@return:",
									"");
							wsm.setReturns(returnComment);
						} catch (Exception e) {
							wsm.setReturns("");
						}
						wsm.setComment(methods[i].commentText());
						wsc.addNewMethod(wsm);
					}
				}
			}

			// Include methods in super class that have RequestMapping
			// Annotation
			MethodDoc[] superMethods = wsc.getClassDoc().superclass().methods();
			for (int l = 0; l < superMethods.length; l++) {
				WebServiceMethod wsm = new WebServiceMethod();
				// Checks if this method in the superclass has been overridden.
				// TODO: Fix!!! This just checks method names being equal right
				// now.
				for (MethodDoc md : methods) {
					if (md.name().trim().equals(superMethods[l].name().trim())) {
						wsm.setOverridden(true);
					}
				}
				AnnotationDesc[] annotations = superMethods[l].annotations();
				for (int k = 0; k < annotations.length; k++) {
					if (annotations[k].annotationType().name()
							.equals("RequestMapping")) {
						ElementValuePair[] vals = annotations[k]
								.elementValues();
						for (int y = 0; y < vals.length; y++) {
							if (vals[y].toString().contains("value=")) {
								String map = vals[y].value().toString();
								wsm.setEndpoint(map);
							} else if (vals[y].toString().contains("method=")) {
								// TODO: Does not check for annotation @POST,
								// @GET...etc
								wsm.setMethodType(vals[y]
										.value()
										.toString()
										.replace(
												"org.springframework.web.bind.annotation.RequestMethod.",
												""));
							}
						}
						try {
							String returnComment = superMethods[l]
									.tags("return")[0].toString();
							returnComment = returnComment.replace("@return:",
									"");
							wsm.setReturns(returnComment);
						} catch (Exception e) {
							wsm.setReturns("");
						}
						wsm.setComment(superMethods[l].commentText());
						wsc.addNewMethod(wsm);
					}
				}
			}
		}
	}
}
