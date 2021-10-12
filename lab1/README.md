# LAB 01

# Creating a Maven project:

App name: *MyWeatherRadar*

```
mvn archetype:generate -DgroupId=com.MyWeatherRadar.app -DartifactId=MyWeatherRadar -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false 
```

# API request:

Start by analyzing the struct of the API requests and the replies (e.g.: check the 5-days aggregateforecast for a location - “Previsão Meteorológica Diária até 5 dias agregada por Local”). You may use any HTTP client, such as the browser or the curl utility. For example, to get a 5-day forecast for Aveiro (which has the internal id=1010500): 

```
curl http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1010500.json | json_pp 
```

# What is POM?

A Project Object Model or POM is the fundamental unit of work in Maven. It is an XML file that contains information about the project and configuration details used by Maven to build the project. It contains default values for most projects. Examples for this is the build directory, which is target; the source directory, which is src/main/java; the test source directory, which is src/test/java; and so on. When executing a task or goal, Maven looks for the POM in the current directory. It reads the POM, gets the needed configuration information, then executes the goal.

Some of the configuration that can be specified in the POM are the project dependencies, the plugins or goals that can be executed, the build profiles, and so on. Other information such as the project version, description, developers, mailing lists and such can also be specified.

For example, on the properties code block we can change the Java version to "11".

# Declaring project dependencies

Build tools allow to state the project dependencies on external artifacts. Maven will be able to retrieve well-known dependencies from the Maven central repository automatically.

In this project, we will need to open a HTTP connection, create a well-formatted GET request, get the JSON response, process the response content.  Instead of programming all these (demanding) steps by hand, we should use a good library, or, in Maven terms, an artifact

