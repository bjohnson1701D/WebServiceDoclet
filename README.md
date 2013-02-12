WebServiceDoclet
================

Doclet that provides basic information about Web Services using Spring Annotations

Sample Usage In Spring pom.xml:

```xml
  <profile>
    	<id>WebServiceDocs</id>
			<build>
				<plugins>
					<plugin>
						<groupId>org.apache.maven.plugins</groupId>
						<artifactId>maven-javadoc-plugin</artifactId>
						<version>2.9</version>
						<configuration>
							<doclet>com.webservice.doclet.WebServiceDoclet</doclet>
							<docletArtifact>
								<groupId>com.webservice.doclet</groupId>
								<artifactId>WebServiceDoclet</artifactId>
								<version>0.0.9</version>
							</docletArtifact>
							<additionalparam>
								-fileName "ws.html"
								-title "My Web Services"
							</additionalparam>
							<useStandardDocletOptions>false</useStandardDocletOptions>
							<destDir>wsdocs</destDir>
						</configuration>
					</plugin>
				</plugins>
			</build>
		</profile>
```
Maven Command to run this example:<br/>
<code>mvn -PWebServiceDocs javadoc:javadoc</code>    
