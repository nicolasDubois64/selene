# How to Build and Run

We can simply build the project with the standard command
	$ mvn clean install 
and we run it with the following command
	$ java -jar <artifact-name>

# How to Start and Stop the back-end on windows

When you launch the back-end on windows, it can be difficult to stop it cleanly (some processes have "trouble" ending) !
So you can start your app and saves the process id in a file with
	$ java -jar myapp.jar & echo $! > ./pid.file &
And stops your app using the saved process id
	$ kill $(cat ./pid.file)
	
You can also create scripts by simply adding #!/bin/bash

# Documentation for a good start

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/maven-plugin/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

