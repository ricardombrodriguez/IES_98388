# Maven commands

```
mvn package #get dependencies, compiles the project and creates the jar 
mvn exec:java -Dexec.mainClass="weather.WeatherStarter" -D exec.cleanupDaemonThreads=false #adapt to match your own package and class name  
```