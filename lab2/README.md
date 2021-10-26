# LAB 02

# Server-side programming with servlets

Nice article about java servlets: https://medium.com/edureka/java-servlets-62f583d69c7e

**Java Servlet** is the foundation web specification in the Java Enterprise environment. A Servlet is a Java class that runs at the server, handles (client) requests, processes them, and reply with a response. A servlet must be deployed into a (multithreaded) Servlet Container to be usable. Containers handle multiple requests concurrently. Servlet is a generic interface and the HttpServletis an extension of that interface(the most used type of Servlets).

When an application running in a web server receives a request, the Server hands the request to the Servlet Container which in turn passes it to the target Servlet.

# Apache Tomcat

What is Apache Tomcat??

Essentially it’s an open-source Java servlet and Java Server Page container that lets developers implement an array of enterprise Java applications. Tomcat also runs a HTTP web server environment in which Java code can run.

There are 3 methods to see if the Tomcat server is running:

1. Curl tool: ```curl -I 127.0.0.1:8080``` 
2. Access the default page in the browser: ```http://localhost:8080```
3. Observe the server log: ```tail logs/catalina.out```

The Tomcat installation includes a management environment (“Manager app”) you can use to control the server, including deploying and un-deploying applications you develop(http://localhost:8080/manager).

To use the manager app, we first need to register at least one role. This can be done by changing the *conf/tomcat-users.xml* file and add this:

```
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="admin" password="admin" roles="manager-gui, manager-script"/>
```

That being said:
Username: ```admin```
Password: ```admin```

In the http://localhost:8080/manager website, we can inspect some servlet examples inside in Examples --> Servlet page. We can execute and check the source code of the examples presented.

In the **Request Paramaters** source code, we can see that the RequestParamExamples class extends **HttpServlet**.

HttpServlet provides an abstract class to be subclassed to create an HTTP servlet suitable for a Web site. A subclass of HttpServlet must override at least one method, usually one of these:
- doGet, if the servlet supports HTTP GET requests
- doPost, for HTTP POST requests
- doPut, for HTTP PUT requests
- doDelete, for HTTP DELETE requests
- init and destroy, to manage resources that are held for the life of the servlet
- getServletInfo, which the servlet uses to provide information about itself

As we can see, the *RequestParamExample* class overrides the *doGet* and *doPost* methods from the HttpServlet abstract class.

Request:
- getParameter("text"): Stores the values for each form field and returns it as string or null.
- getAttribute("object"): Returns the picked value as an object. It doesn't store the form fields.
- getCookies(): Returns an array containing all of the Cookie objects the client sent with this request. This method returns null if no cookies were sent.

In the *cookie* example, we can set a cookie by getting the *cookiename* and *cookievalue* parameters (with .getParameter()) and add a new Cookie object to the cookie array. To print out the *cookies*, we can use the getCookies() method that returns a Cookie array.

When we type the "submit" button, all the form data will be sent using the POST HTTP method.

# Create web application and deploying it into Tomcat

We can start by creating a maven-based web application project (with the correct maven archetype for web-based projects).

archetypeGroupId=org.codehaus.mojo.archetypes
archetypeArtifactId=webapp-javaee7
archetypeVersion=1.1

```
mvn archetype:generate -DgroupId=org.codehaus.mojo.archetypes -DartifactId=webapp-javaee7 -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.4 -DinteractiveMode=false
```

We use the **maven-archetype-webapp**, as it simply generates a Maven webapp project we need.

After that, we type ```mvn install``` to build the project and check if there are any errors.

Confirm that you have a .war file in ```<project folder>/target```. This is your application packaged as a Web Archive (you may inspect its contents in a regular Zip tool, though not required).

We can deploy the packed application (.war file) into the application server. We just need to use the tomcat management interface to upload and deploy a .war file.

Deploying using the Tomcat Manager page has some disadvantages: it is not “connected” with the IDE and is specific to Tomcat. The productive alternative is to use the IDE integrated deployment support.

In VSCode, if you have the Tomcat extension, you can manage all your local Tomcat servers within the editor and easily debug and run your war package on Tomcat and link Tomcat into your workspace. You can also create a new Tomcat server from the explorer using the Add button (+) and run a war package on it. You can also create the server during the deployment. 

If address is already in use:

If you know what port the process is running you can type: ```<lsof -i:<port>.```
For instance, lsof -i:8080, to list the process (pid) running on port 8080.
Then kill the process with kill ```<pid>```

