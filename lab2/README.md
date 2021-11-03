# LAB 02

# Server-side programming with servlets

Nice article about java servlets: https://medium.com/edureka/java-servlets-62f583d69c7e

**Java Servlet** is the foundation web specification in the Java Enterprise environment. A Servlet is a Java class that runs at the server, handles (client) requests, processes them, and reply with a response. A servlet must be deployed into a (multithreaded) Servlet Container to be usable. Containers handle multiple requests concurrently. Servlet is a generic interface and the HttpServletis an extension of that interface(the most used type of Servlets).

When an application running in a web server receives a request, the Server hands the request to the Servlet Container which in turn passes it to the target Servlet.

# Apache Tomcat

What is Apache Tomcat??

Essentially it’s an open-source Java servlet and Java Server Page container that lets developers implement an array of enterprise Java applications. Tomcat also runs a HTTP web server environment in which Java code can run.

How to run Apache Tomcat: Execute the script inside the bin/ folder - ./(apachefolder)/bin/startup.sh

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

# HTTP GET and POST methods

**GET method:**

GET is used to request data from a specified resource.
The query string (name/value pairs) is sent in the URL of a GET request.

- GET requests can be cached
- GET requests remain in the browser history
- GET requests can be bookmarked
- GET requests should never be used when dealing with sensitive data
- GET requests have length restrictions
- GET requests are only used to request data (not modify)

**POST method:**

POST is used to send data to a server to create/update a resource.
The data sent to the server with POST is stored in the request body of the HTTP request.

- POST requests are never cached
- POST requests do not remain in the browser history
- POST requests cannot be bookmarked
- POST requests have no restrictions on data length


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

In the 2.1 h) subexercise, the *user* and *color* parameters must be passed as a query in the URL as described below:

```
http://localhost:8080/tomcat_webapp-1.1/welcome?user=ricardo&color=blue
```

Note that we use the '&' character to separate different request parameters.
We can use the NullPointerException to throw an exception if some parameter is not fulfilled.

# Server-side programming with embedded servers

Now, our goal is to develop the Servlet example presented on the last exercise but with an embedded server approach. For that, we will be using the Embedded **Jetty** Server.

Jetty can be used in standalone mode, but the main purpose behind building jetty was so that it could be used inside an application instead of deploying an application on jetty server.

Generally you write a web application and build that in a WAR file and deploy WAR file on jetty server. In Embedded Jetty, you write a web application and instantiate jetty server in the same code base, which is very effective compared to the previous methodology.

To run a simple web application on a embedded server, we first need to instantiate some jetty dependencies in *pom.xml*: *jetty-server* and *jetty-servlet*.

We can create a new server with the jetty Server class, passing the port of the network as a parameter of the constructor.
The ServletHandler class is a Servlet HttpHandler. This handler maps requests to servlets that implement the javax.servlet.http.HttpServlet API.
That being said, we want to set the created handler as the server handler, and we can do it by using the setHandler() method.
The addServletWithMapping() is used to add a servlet and has two parameters: the servlet class and the url-pattern.
After that, we can start the server and the defined Servlet handler will handle the http requests.

In jetty, we can get the http parameters with the request.getParameter("parameter"), as we did before..
However, to present HTML code, we use the response.getWriter().println("code") method.

Now, instead of having to undeploy and deploy the project after making alterations, we just need to build the project with Maven, run the Java file and access the http://localhost:{port}/{url-pattern}.

# Introduction to web apps with a full-featured framework (Spring Boot)

**What is Spring Boot?**

Spring Boot is a rapid application development platform built on top of the popular Spring Framework. By assuming opinionated choices by default (e.g.: assumes common configuration options without the need to specify everything), Spring Boot is a **convention-over-configuration** addition to the Spring platform, useful to get started with minimum effort and create stand-alone, production-grade applications. TLDR: Spring Boot reduces the time-expensive configuration, as it uses a set of default configurations for Spring (we just need to adjust some things).

**And what about the Spring framework?**

Things the Spring framework can do:
- Microservices -> Quickly deliver production‑grade features with independently evolvable microservices.
- Reactive - >Spring's asynchronous, nonblocking architecture means you can get more from your computing resources.
- Cloud -> Your code, any cloud—we’ve got you covered. Connect and scale your services, whatever your platform.
- Web apps -> Frameworks for fast, secure, and responsive web applications connected to any data store.
- Serverless -> The ultimate flexibility. Scale up on demand and scale to zero when there’s no demand.
- Event Driven -> Integrate with your enterprise. React to business events. Act on your streaming data in realtime.
- Batch -> Automated tasks. Offline processing of data at a time to suit you.

Basically, Spring framework has libraries for database connection, authentication management and restful api creation.

**Spring Initializr**

This web tools allows us to create Maven-based Spring Boot project for a web application. We can manually add project dependencies (ex: Spring web dependency) as it simplifies the pom.xml setup.

The generated maven project created by Spring Initializr also contains a Maven wrapper script (.mnvw file). It's a good option for projects that need a specific version of Maven, for users who don't want to install Maven as we can just use the project-specific wrapper script.

**Maven Wrapper Setup:**

1. Run this command in the main folder of the project (optional maven version specification):
```
mvn -N io.takari:maven:wrapper (-Dmaven=<version>)
```
Note that we're using the Takari Maven plugin available at: https://github.com/takari/takari-maven-plugin

2. After executing this command, more files and directories will be created in the project:
- mvnw: it's an executable Unix shell script used in place of a fully installed Maven
- mvnw.cmd: it's the Batch version of the above script
- mvn: the hidden folder that holds the Maven Wrapper Java library and its properties file

**Build and run the program**

Two options to run the Spring Boot Maven-based project:
```
 mvninstall -DskipTests && java -jar target\webapp1-0.0.1-SNAPSHOT.jar
```
or
```
./mvnwspring-boot:run
```

If we access http://localhost:8080/, we shall get the following error:

>Whitelabel Error Page
>This application has no explicit mapping for /error, so you are seeing this as a fallback.

>Sat Oct 30 18:54:05 WEST 2021
>There was an unexpected error (type=Not Found, status=404).

By default, Spring boot applications start with embedded tomcat server start at **default port 8080**. We can change default embedded server port to any other port, using any one of below technique.

- Changing server port in *application.properties*: server.port={port}
- Changing server port in *application.yml*: server: port: {port}

The *application.properties* file can be found inside the src/main/resources folder.

These are the simplest steps to change the server port, although we can change it programatically or from the command line.



# Serving Web content with Spring MVC

Goal: to develop a web application that will accept HTTP GET requests (like we did before two times) and display a greeting with an optional *name* passed as a parameter in the query string (in the URL).

After setting up the project, we can proceed with the web controller implementation.

In Spring framework, HTTP requests are handled by a controller, that can be easily identified by the *@Controller* annotation.

Note: The controller class needs its own file.

Controller code breakdown:

The @GetMapping annotation ensures that HTTP GET requests to /welcome are mapped to the welcome() method.
@RequestParam binds the value of the query string parameter name into the name parameter of the welcome() method. This query string parameter is required (although it can be disabled with the 'false' value). If it is absent in the request (if required = false), the defaultValue will be set as the default value. The value of the name parameter is added to a Model object, ultimately making it accessible to the view template.

Note: the string returned by the welcome() method must be equal to the name of the .html file that resides inside the templates/ folder, otherwise it won't work.

The implementation of the method body relies on a view technology (in this case, **Thymeleaf**) to perform server-side rendering of the HTML. Thymeleaf parses the welcome.html template and evaluates the th:text expression to render the value of the ${name} parameter that was set in the controller.

**Spring Boot Devtools**

A common methodology of web development is making changes, restarting the application and then refresh the browser to view alterations. However, this process is time-expensive and we can speed it up by using a handy module Spring Boot offers known as **spring-boot-devtools**. It enables hot swapping, switches template engines to disable caching, enables LiveReload to automatically refresh the browser and other features...

**Running the application**

By default, Spring Initializr will create an application class and we don't need no further alterations on this file.

@SpringBootApplication is a convenience annotation that adds all of the following:

- @Configuration: Tags the class as a source of bean definitions for the application context.
- @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
- @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.

The main() method of this file does not refer to any xml file, as everything is dealt with pure Java.

**Building an executable JAR**

In Maven, you can run the application by using ```./mvnw spring-boot:run```. 

Alternatively, you can build the JAR file with ./mvnw clean package and then run the JAR file with ```java -jar target/gs-serving-web-content-0.1.0.jar```

Link example to run the web application: http://localhost:9000/welcome?name=Ricardo%20Rodriguez

**Creating a REST endpoint**

Goal of the exercise: Create a REST endpoint that listens to the HTTP request and answers with a JSON result (greeting message).

As we already know, **JSON** or JavaScript Object Notation is a lightweight format for storing/transporting data, it's easy to understand as data is stored in pairs (key-value pairs) and different objects (hold by curly braces) are separated by commas.

The service will handle GET requests for /greeting, optionally with a name parameter in the query string. The GET request should return a 200 OK response with JSON in the body that represents a greeting. It should resemble the following output:

>{
>    "id": 1,
>    "content": "Hello, <user>!"
>}

The *id* field is a unique identifier for the greeting, and *content* is the textual representation of the greeting.

First, we create a Java resource representation class named *Greeting.java*, it will be representing the JSON result as a class. To do that, we need the attributes, get() methods and the constructor for the id and the name.

After the representation class creation, we also need to create a resource controller. This is because in Spring’s approach to building RESTful web services, HTTP requests are handled by a controller (identified by the *@RestController* annotation).

This controller should handle HTTP GET requests for the URL path provided in the *@GetMapping* annotation, returning a new object of the *Greeting.java* class (resource representation class). Note that the getGreeting() method will be returning a new instance of the *Greeting* class, as we wanted. The AtomicLong *counter* instance is a *long* value that may be update atomically with the incrementAndGet() method, returning a new id for the next greeting. The *%s* in the *template* variable will be replaced by the *name* variable (thanks to the String.format() method).

@RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method. If the name parameter is absent in the request, the defaultValue of World ("dear friend") is used.

A key **difference between a traditional MVC controller and the RESTful web service controller shown earlier** is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, **this RESTful web service controller populates and returns a Greeting object**. The **object data will be written directly to the HTTP response as JSON**.

The Greeting object must be converted to JSON. Thanks to Spring’s HTTP message converter support (MappingJackson2HttpMessageConverter), you need not do this conversion manually. 

Note: **we need to add 3 jackson dependencies** (they must be the same version otherwise it won't work):
- jackson-databind 
- jackson-core
- jackson-annotations
Version (most recent one): 2.13.0

Link: http://localhost:9000/greeting?name=Ricardo%20Rodriguez

However, we can also access the REST endpoint using the *curl* utility.

**curl** is a command-line tool for transferring data, and it supports about 22 protocols, including HTTP. This combination makes it a very good ad-hoc tool for testing our REST services. It can support a vast variety of command-line options.

**Verbose:**

When we're testing, it’s a good idea to set the verbose mode on: ```curl -v http://localhost:9000/greeting?name=Ricardo%20Rodriguez```
As a result, the commands provide helpful information such as the resolved IP address, the port we're trying to connect to, and the headers.

Result:
```
curl -v http://localhost:9000/greeting?name=Ricardo%20Rodriguez
*   Trying 127.0.0.1:9000...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 9000 (#0)
> GET /greeting?name=Ricardo%20Rodriguez HTTP/1.1
> Host: localhost:9000
> User-Agent: curl/7.68.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 31 Oct 2021 15:32:16 GMT
< 
* Connection #0 to host localhost left intact
{"id":26,"name":"Hello, Ricardo Rodriguez!"}
```

**Output:**

By default, curl outputs the response body to standard output. Additionally, we can provide the output option to save to a file: ```curl -o output_file.json http://localhost:9000/greeting?name=Ricardo%20Rodriguez```
This is especially helpful when the response size is large.

Result:
```
$ cat output_file.json 
{"id":27,"name":"Hello, Ricardo Rodriguez!"}
```



# Wrapping-up & integrating concepts

We now want to develop a web server (REST API) that offers random quotes from movies/shows from an existing API. 

Because this is a practical class to improve our REST API skills, we can simply add the quotes/movie information in a static way and we don't need to add every quote/show that exists.



**Links where I got the quotes & movies/show info:**

- [Get tv shows info](https://movie-quote-api.herokuapp.com/v1/shows/)

- [Get random quote](https://movie-quote-api.herokuapp.com/v1/quote/)

- [Get random quote from movie/show](https://movie-quote-api.herokuapp.com/v1/shows/show-slug) (needs show-slug argument)



**Web server link**: http://localhost:8080/ (already has labels that redirect to the different functionalities)



**Functionalities of the web application:**

- **api/quote** -> Returns a random quote from a random show/film.
- **api/shows** -> List of all available shows (for which some quote exists).
- **api/quotes?show=<show_id>** -> Returns a random quote for the specified show/film (show_id is an identifier in the 1-5 range).

All responses are presented as JSON data.

For this project, we need a REST controller for each of the URL patterns / functionalities. *ShowsController* that returns all existing shows, *QuoteController* that shows a random quote from a movie/show and finally the *QuoteShowController* that returns a random quote from the show id we selected (as a GET parameter).

Besides that, I also created the *Show* and *Quote* classes that represent the entities.



# Review questions



## A. What are the responsibilities/services of a “servlet container”?



A web server uses HTTP protocol to send (http request) and receive (http response) data. Generally speaking, a user types the URL of the website and he will be given a webpage.

However, sometimes the user may need to send data to the web server to get a dynamic web page, based on his input. The solution to avoid these static responses from the web server is to use Servlet containers. With Servlet containers, the web server can handle requests in Java to render a response based on that received input and present it to the user.

A **Servlet** is a class that handles requests, processes them and replies back with a response. These are under the control of another Java application called a **Servlet Container**. When an application running in a web server receives a request*,* the Server hands the request to the Servlet Container – which in turn passes it to the target Servlet.

#### A Servlet container can provide a series of services:

- Load, initialize and execute servlets.

- Communication between the servlet and the web server 

- Lifecycle management : It manages the set of methods which define the lifecycle of a servlet. Methods: 

  - ***init()*** - If an instance of the servlet does not exist, the web container loads the servlet class, creates an instance of the servlet class and initializes it by calling the init method; 

  - ***service()*** - This method is only called after the servlet's *init()* method has completed successfully*.* The container calls this method to handle client requests, it interprets the HTTP request type (GET,POST,PUT,DELETE,etc..) and calls *doGet*, *doPost*, *doPut*, *doDelete* and other methods as appropriate; 
  - ***destroy()*** - Called by the Servlet Container to take the Servlet out of service.

- Multithreading support : It automatically creates a new thread for every servlet request received. When the Servlet service() method completes, the thread dies. Note: one servlet container can hold multiple active servlets.

- Declarative security : It manages the security inside the XML deployment descriptor file.

- JSP support : The container is responsible for converting JSPs to servlets and for maintaining them.



## B. Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web. (You may exemplify with the context of the previous exercises.)



Sprint Boot is a Spring (framework) project that simplifies the configuration and building of a application. You just need to specify which modules you want to use in your project and he will recognize and configure it for you to use.

#### Spring Boot main functionalities:

- Create stand-alone applications
- Provide starting dependencies to simplify building configuration
- No requirement for *pom.xml* configuration nor code generation (as it does all for us)
- Embed Tomcat, Jetty directly (without the need to deploy WAR files)

**Spring MVC** is a model view controller-based web framework under the Spring framework. It helps us to develop strong and flexible web applications.

#### Spring MVC functionalities:

- Receive HTTP requests
- Dispatch processing of data to other components
- Prepare the response needed to the user

#### Spring MVC steps:

1. User sends a HTTP request to a server that runs on Spring MVC and the framework controller (Spring MVC) receives it.
2. The framework controller searches which class is responsible to handle the HTTP request (this can be done by looking for the class with the correct @GetMapping() annotation) and delivers all data sent by the browser. This class is the controller (it has the @Controller annotation). Example: *WelcomeController* is the controller that handles all HTTP requests that maps to the "/welcome" URL pattern.
3. The controller sends data to the model (ex: through the *model.addAttribute()* method). This last one executes all the logic behind the business core (operations, validations, database access, class instantiations, etc..) and returns the results to the controller.
4.  The controller returns the name of the view alongside the data it needs to render the web page. (Ex: *WelcomeController* returns "index" as it's the name of the view).
5. The Spring MVC framework finds the view that processes data, transforming the result in HTML, which in turn is sent back to the user's browser.



## C. Inspect the POM.xml for the previous Spring Boot projects. What is the role of the “starters” dependencies?



In the previous Spring Boot projects, we used the following starter dependencies:

- **Spring Web** > The spring web dependency contains common web specific utilities for both Servlet and Portlet environments. It allows us to build web (RESTful included) applications using the Spring MVC framework (previously mentioned).
- **Thymeleaf** > Is a modern server-side Java template engine for both web and stand-alone environments. It allows HTML to be correctly displayed in browsers and as static prototypes. In the previous project, the HTML file uses Thymeleaf as *${name}* will be replaced by the value of the "name" attribute.
- **Spring Boot DevTools** > It provides fast application restarts, LiveReload and configurations for a better development experience. 
- **Jackson Databind** > It automatically marshal instances of a class into JSON. Jackson is included by default by the web starter. Example: *GreetingController* returns a new instance of the *Greeting* class and the application uses Jackson to transform the instance of the *Greeting* class into JSON, presenting it on the web server.







