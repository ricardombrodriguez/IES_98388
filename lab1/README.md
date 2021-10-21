# LAB 01

Simple maven introduction: https://www.devdungeon.com/content/maven-basics-java-developers

# What is Maven?

Maven is a build tool that automates the compiling, dependency management, packaging, and even deployment of Java applications. Some Maven's alternatives are ANT and GRADLE.

# Why use Maven instead of the typical **javac**?

Maven advantages:

- Generate pre-built templates for different types of projects, which is very effective
- Maven manages building the .jar, which requires manifest files and other tedious configuration if done by hand, it can also create windows.exe files, Mac .app files and Devian/Ubuntu based .deb packages
- Very extensible with many community plugins, as it is broadly used in the Java community
- Easy to run on the command line, and supported by all major IDEs (VSCode included)
- Other people can easy load and build your project using Maven
- Install packages to your local repository
- Deploy packages to remote repositories
- Generate JavaDoc documentation in HTML format

# Creating a Maven project:

App name: *MyWeatherRadar*

Maven has a concept called *archetypes*, which are essentially prebuilt project templates that include folder structure and files needed to get started for a specific type of project. There are tons of archetypes out there that come with templates for building things like command line apps, empty projects, gui apps, and web apps. There is at least one archetype worth memorizing and that is the maven-archetype-quickstart. This generates the simplest possible project with one source file ready for us to edit.

```
mvn archetype:generate -DgroupId=com.MyWeatherRadar.app -DartifactId=MyWeatherRadar -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false 
```
The example just above also includes the optional -DinteractiveMode=false flag, which disables the prompt to press Y and review the information. Leave interactiveMode set to true if you do want to review the information before it creates the project.

groupId: Namespace of the project / website (ex: GitHub account)

artifactId: Name of the package within the domain (groupId)

version: Version of the project

# API request:

Start by analyzing the struct of the API requests and the replies (e.g.: check the 5-days aggregateforecast for a location - “Previsão Meteorológica Diária até 5 dias agregada por Local”). You may use any HTTP client, such as the browser or the curl utility. For example, to get a 5-day forecast for Aveiro (which has the internal id=1010500): 

```
curl http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1010500.json | json_pp 
```

# What is POM?

A Project Object Model or POM is the fundamental unit of work in Maven. It is an XML file that contains information about the project and configuration details used by Maven to build the project. It contains default values for most projects. Examples for this is the build directory, which is target; the source directory, which is src/main/java; the test source directory, which is src/test/java; and so on. When executing a task or goal, Maven looks for the POM in the current directory. It reads the POM, gets the needed configuration information, then executes the goal.

Some of the configuration that can be specified in the POM are the project dependencies, the plugins or goals that can be executed, the build profiles, and so on. Other information such as the project version, description, developers, mailing lists and such can also be specified.

For example, on the properties code block we can change the Java version to "11".

# Resources

Sometimes your application needs to include static resources like images or icons. You can specify special directories for Maven to look for resources. These resources will be packed in to the JAR. The default directory which requires no configuration is src/main/resources/.

To read and write files from the resource directory, you want to use a special function, java.lang.Class.getResource(). Example:

```
// Get a URL that points to the file you want
java.net.URL filepath = getClass().getResource("/src/main/resources/test.txt");
// Then pass the URL to something that will open the file like `javax.imageio.ImageIO.read()`

// You can also use `java.lang.ClassLoader.getResourceAsStream()` to get a `java.io.InputStream`
java.io.InputStream instream = getClass().getResourceAsStream("/src/main/resources/largefile.bin");
// Then use something like `java.io.BufferedReader` to read the stream.
```

If you want to change or add more directories, you can add a configuration in the build section of your pom.xml.

# Declaring project dependencies

Build tools allow to state the project dependencies on external artifacts. Maven will be able to retrieve well-known dependencies from the Maven central repository automatically.

In this project, we will need to open a HTTP connection, create a well-formatted GET request, get the JSON response, process the response content.  Instead of programming all these (demanding) steps by hand, we should use a good library, or, in Maven terms, an artifact.

Tools to be used during this project: *Retrofit* and *Gson*.

Google’s Gson is a Java library that can be used to convert Java Objects into their JSON representation; Square’s Retrofit is a type-safe HTTP client for Java, that allows mapping an external REST API into a local (Java) interface.

The next step is to declare both of these dependencies in the POM file (*pom.xml*).

In POM, we also have to declare direct dependencies, because some artifacts depend on others.

# After developing the project

Compile and run the project, either from the IDE or the CLI.

```
mvn package #get dependencies, compiles the project and creates the jar 
mvn exec:java -Dexec.mainClass="weather.WeatherStarter" -D exec.cleanupDaemonThreads=false #adapt to match your own package and class name  
```

# Maven arguments

The *mvn exec:java* command can also receive command-line arguments.
```
mvn exec:java -Dexec.mainClass="weather.WeatherStarter" -D exec.cleanupDaemonThreads=false Dexec.args="'argument separated with space' 'another one'"
```

When executing a java file with Maven, we can insert multiple arguments, separated with spaces as shown above.

In the Java file, arguments are accepted as in normal Java (with args[n])

# Logging

Logging is a powerful aid for understanding and debugging program's run-time behavior. Logs capture and persist the important data and make it available for analysis at any point in time. Example: Git command *git log*, it's basically a book with the history of the project.

**How to enable logging?**

All the logging frameworks discussed in the article share the notion of loggers, appenders and layouts. Enabling logging inside the project follows three common steps:

- Adding needed libraries
- Configuration
- Placing log statements

# Repository history

To see the repo history, we can type the following command:

```
git log --reverse --oneline
```

# Docker and its advantages of using it

How is 'simply installing everything' simpler than just building an image from a Dockerfile that defines everything you need?

The key advantage of Docker is that all your dependencies are written in code. So if you have a Dockerfile in your repository, the code will run no matter where it is running, as long as Docker is installed. That's very powerful.

Second, it's isolation. If you have two apps running on the same server, one can easily use Python 3.5 while the other uses 3.9.

It gets immensely more complicated setting up a dev environment and dealing with version and dependency conflicts, that's another factor of why we should use docker.

It's easier because you can run everything up in just one command..

# Docker container

Containers only use host machine kernel, instead of using virtual machines.
It can start up in seconds instead of minutes, it uses less memory.
A container is a running instance of an image (snapshot of the system at a particular time)

The OS, software and application code is confined in a container.

Dockerfile -> text file with a list of steps to perform to create that image.

Run dockerfile -> Get an image -> Run the image -> Container

# Docker commands

docker build -t image_name ->  Build the container image using the docker build command

docker run -dp 3000:3000 image_name -> Start the container. What about the the -d and -p flags? We’re running the new container in “detached” mode (in the background) and creating a mapping between the host’s port 3000 to the container’s port 3000. Without the port mapping, we wouldn’t be able to access the application.

# How to use another maven project as a dependency

Separate both projects into different maven projects.

Steps:

- Add a dependency in the *pom.xml* of the project we want to use as a dependency (use IpmaApiClient as a dependency in the WeatherForecastByCity pom.xml file)
- ```mvn package``` in the dependency project (ImpaApiClient)
- ```mvn install:install-file -Dfile="/home/ricardo/Documents/IES_98388/lab1/lab1_5/IpmaApiClient/IpmaApiClient/target/IpmaApiClient-1.0-SNAPSHOT.jar" -DgroupId="com.weather.app" -DartifactId="IpmaApiClient" -Dversion="1.0-SNAPSHOT" -Dpackaging=jar``` (insert this command inside the main maven project)
- ```mvn package``` in the main maven project

