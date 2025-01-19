# Introduction
- this is a University Software Prototype , made for testing basic operations

## Features
- Authintication 
- Authorization
- Security (preventing sql-injection , Hashing ,  Basic Access Control)
- Performance (built with known design patterns (singleton for database connection))
- Basic Data Manipulation(An Actor can manipulate it's data)
-  Gui for simplicity (using swing framework)


## Actors
- Student: can register subjects , manipulate it's data
- Professr: can register only one subject to teach , manipulate it's data

## Design
the basic system design

### ERD
[img](https://imgur.com/a/yeQt29b)
### Tables
[img2](https://imgur.com/a/wklwh8i)

## Tools Used
- Gradle for building system
- JetBrains Idea for coding
- Apache NetBeans for designing ui
- Microsoft sql-server as a DBMS
- JetBrains DataGrip , Azure-Studio for creating  the database

## Getting Started (How To Build)
- ensure to set your connection settings in the config file
- we can start with initializing the gradle environment
 ``` shell
 ./gradlew init
 
 ```
-  we can ensure that dependecies have successfully resolved
```shell
./gradlew dependencies
```

- now we build
```shell
./gradlew build
```
- to make a jar file with shadowJar Plugin
```shell
./gradlew shadowJar
```
- Execute
```shell
java -jar build/libs/DataBase2-1.0-SNAPSHOT-all.jar
```