# LAB 01

Simple maven introduction: https://www.devdungeon.com/content/maven-basics-java-developers

# What is Maven?

The regular “build” of a (large) project takes several steps, such as obtaining dependencies (required external components/libraries), compiling source code, packaging artifacts, updating documentation, installing on the server, etc..  In medium to large projects, these tasks are coordinated by a build tool. Maven is the most popular build tool used among professional projects.
That being said, Maven **automates the compiling, dependency management, packaging, and even deployment of Java applications**. Some Maven's alternatives are ANT and GRADLE.

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

App name (for exercise 1.2): *MyWeatherRadar*

Maven has a concept called *archetypes*, which are essentially prebuilt project templates that include folder structure and files needed to get started for a specific type of project. There are tons of archetypes out there that come with templates for building things like command line apps, empty projects, gui apps, and web apps. There is at least one archetype worth memorizing and that is the maven-archetype-quickstart. This generates the simplest possible project with one source file ready for us to edit, as we are going to use in this project.

```
mvn archetype:generate -DgroupId=com.MyWeatherRadar.app -DartifactId=MyWeatherRadar -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false 
```
The example just above also includes the optional -DinteractiveMode=false flag, which disables the prompt to press Y and review the information. Leave interactiveMode set to true if you do want to review the information before it creates the project.

**groupId**: It uniquely identifies your project across all projects. A group ID should follow Java's package name rules!! This means it starts with a reversed domain name you control. For example, *org.apache.maven*, *org.apache.commons*. This rule is not mandatory, as we can use single word groupId's, but it's not recommended and it will be difficult to get a new single word group ID approved for inclusion in the Maven Central repository.

**artifactId**: It's the name of the jar without version. If you created it, then you can choose whatever name you want with lowercase letters and no strange symbols. If it's a third party jar, you have to take the name of the jar as it's distributed.

**version**:  If you distribute it, then you can choose any typical version with numbers and dots (1.0, 1.1, 1.0.1, ...). Don't use dates as they are usually associated with SNAPSHOT (nightly) builds. If it's a third party artifact, you have to use their version number whatever it is, and as strange as it can look.

Note: “-D” switch is used to define/pass a property to Maven in CLI. 

# API request:

Start by analyzing the struct of the API requests and the replies (e.g.: check the 5-days aggregateforecast for a location - “Previsão Meteorológica Diária até 5 dias agregada por Local”). You may use any HTTP client, such as the browser or the curl utility. For example, to get a 5-day forecast for Aveiro (which has the internal id=1010500): 

```
curl http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1010500.json | json_pp 
```

This command will print out the JSON info available at that URL.

# What is POM?

A Project Object Model or POM is the fundamental unit of work in Maven. It is an XML file that contains information about the project and configuration details used by Maven to build the project. It contains default values for most projects. Examples for this is the build directory, which is target; the source directory, which is src/main/java; the test source directory, which is src/test/java; and so on. When executing a task or goal, Maven looks for the POM in the current directory. It reads the POM, gets the needed configuration information, then executes the goal.

Some of the configuration that can be specified in the POM are the project dependencies, the plugins or goals that can be executed, the build profiles, and so on. Other information such as the project version, description, developers, mailing lists and such can also be specified.

For example, on the properties code block we can change the Java version to "11" or we can add some dependencies needed for the project (ex: Retrofit).

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

This *mvn* commands are performed in the Maven project root (in the folder where *pom.xml* resides)

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

**Build image** - consistently package everything your application needs to run
**Ship image** - easily ship these images to runtimes in the cloud or on your local dev machine
**Run image** - easily and consistently execute your applications

# Docker container

Containers only use host machine kernel, instead of using virtual machines.
It can start up in seconds instead of minutes, it also uses less memory.
A container is a running instance of an image (snapshot of the system at a particular time).

The OS, software and application code is confined in a container.

Dockerfile -> text file with a list of steps to perform to create that image.

Run dockerfile -> Get an image -> Run the image -> Container

# Docker useful commands

```
Creating a Container
`docker create [IMAGE_NAME]`

Creating and Running a Container
`docker run [IMAGE_NAME]`

Starting a Stopped Container
`docker start [CONTAINER_NAME]`

Stopping a Running Container
`docker stop [CONTAINER_NAME]`

Restarting a Running Container
`docker restart [CONTAINER_NAME]`

Pausing a Running Container
`docker pause [CONTAINER_NAME]`

Resuming a Paused Container
`docker unpause [CONTAINER_NAME]`

List currently running containers
`docker ps`

List all containers
`docker ps -a`

Removing a Container
`docker rm [CONTAINER_NAME]`

Building an Image from a Dockerfile
`docker build -f [DOCKERFILE_PATH]`


Building an Image from a Container
`docker commit [CONTAINER_NAME] [IMAGE_NAME]`

Pulling an Image from the Docker Hub
`docker image pull [IMAGE_NAME]`

Pushing an Image to the Docker Hub
`docker image push [IMAGE_NAME]`

List Container Images
`docker image ls` or `docker images`

Deleting an Image from your System
`docker image remove [IMAGE_NAME]`
```

**Working with Docker Volumes**
Attaching Docker Volumes to containers via the docker run, or docker create commands will allow some of the data in your container to persist across image rebuilds. The following docker commands will help you get started with working with docker volumes.

```
Create a Docker Volume
`docker volume create [VOLUME_NAME]`

Remove a Docker Volume
`docker volume rm [VOLUME_NAME]`

Inspect a Docker Volume
`docker volume inspect [VOLUME_NAME]`

List all Docker Volumes
`docker volume ls`
```

**Working with Docker Networks**
Docker networks determine how containers connect to each other, and the internet. Private networks can be created for various software application stacks to ensure data security.

```
Creating a Docker Network
`docker network create [NETWORK_NAME]`

Connecting a Container to a Network
`docker network connect [NETWORK_NAME] [CONTAINER_NAME]`

Disconnecting a Container from a Network
`docker network disconnect [NETWORK_NAME] [CONTAINER_NAME]`

Inspecting a Network
`docker network inspect [NETWORK_NAME]`

Listing all Networks
`docker network ls`

Removing a Network
`docker network rm [NETWORK_NAME]`
```

# How to use another maven project as a dependency

Separate both projects into different maven projects.

Steps:

- Add a dependency in the *pom.xml* of the project we want to use as a dependency (use IpmaApiClient as a dependency in the WeatherForecastByCity pom.xml file)
- ```mvn package``` in the dependency project (ImpaApiClient)
- ```mvn install:install-file -Dfile="/home/ricardo/Documents/IES_98388/lab1/lab1_5/IpmaApiClient/IpmaApiClient/target/IpmaApiClient-1.0-SNAPSHOT.jar" -DgroupId="com.weather.app" -DartifactId="IpmaApiClient" -Dversion="1.0-SNAPSHOT" -Dpackaging=jar``` (insert this command inside the main maven project)
- ```mvn package``` in the main maven project



# Review questions

Notes: 
- exercise 1.2 is the result of the 1.3 (added some functionalities and added log4j2)
- the log files are inside the logs/ folder, which resides inside the maven project root (with pom.xml and src/ folder)




## A) Maven has three lifecycles: clean, site and default. Explain the main phases in the default lifecycle.**

The maven default lifecycle is the main one as it's responsible for project deployment and it consists of many phases as described below:

- validate: check if all information necessary for the build is available
- compile: compile the source code
- test-compile: compile the test source code
- test: run unit tests
- package: package compiled source code into the distributable format (jar, war, …)
- integration-test: process and deploy the package if needed to run integration tests
- install: install the package to a local repository
- deploy: copy the package to the remote repository

For example, when we are executing ```mvn package``` we are running a specific phase (package phase).
That being said, we run a specific phase using the command ```mvn <PHASE>```.
It's important to know that running a specific phase will also execute all previous phases as well.




## B) Maven is a build tool; is it appropriate to run your project to?**

Maven is a very effective build tool to use when developing a project for two main reasons:

- Managing dependencies: The majority of projects use libraries, plugins and many other important stuff. Without maven, we can manually download of those libraries, put them on the classpath, but it's time expensive and it can be very frustating. With Maven, we can just write the libraries we need (and the respective versions) and it does all the hard work for you without needing to waste time.

- Builds: Although we already have dependency management done, we also need to deploy our project that can have different purposes. Well, maven can do this hard work too. It can compile, test, package the application to the format we want (.jar or .war, for example), which is very advantageous.




## C) What would be a likely sequence of Git commands required to contribute with a new feature to a given project? (i.e., get a fresh copy, develop some increment, post back the added functionality)

After adding new functionalities to the original project (project1) in another folder (project2), we can do the following steps:

- ```git init``` -> if there's no git repository estabilished
- ``` git add *``` -> add all files
- ```git commit -m "message"``` -> commit
- ```git remote add origin [original project github repository link]``` -> it will start pushing to the original project1 remote repository after a *push* command
- ```git push``` -> push to the original repository
- ```git log --reverse --oneline``` -> (optional command) this will present the repository history




## D) There are strong opinions on how to write Git commit messages… Find some best practices online and give your own informed recommendations on how to write good commit message (in a team project).

The most important thing when writing commit messages is clarity. It's important to make sure others will understand clearly what has been done in that commit and that the message is informative. For example, after finishing some function or a code file, we should write down the deed.

Examples:
- ```git commit -m "lab1_5 done"```
- ```git commit -m "xyz function completed"```
- ```git commit -m "abc bug fixed"```

It doesn't need to have few characters. But each commit message should summarize all the work that has been done in the commit span.




## E) Docker automatically prepares the required volume space as you start a container. Why is it important that you take an extra step configuring the volumes for a (production) database?

By default, all files created inside a container are stored on a writable container layer. This means that data doesn't persists when the container no longer exists, we can't easily move the data to somewhere else and writing into a container's writable layer requires a storage driver to manage the filesystem. All of these aspects are very inneffective if we want to store or restore data, for example.

That being said, docker has two options for containers to store files in the host machine: *volumes* and *bind mounts*.

Unlike bind mounts, which can be stored anywhere on the host system, volumes are stored in a part of the host file system which is managed by Docker and non-Docker processes can't modify this part of the file system. 

Volumes are the best way to persist data in Docker, as Docker volumes are file systems mounted on containers to preserve data generated by the running container. They're stored on the host, independently from the container lifecycle, although container's data can also be stored on a remote host/cloud. This allows users to backup data and share file systems between containers easily.

When no running container is using a volume, the volume is still available to Docker and it's not removed automatically, unless we manually remove unused volumes using the ```docker volume prune``` command.