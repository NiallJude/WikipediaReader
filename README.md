# WikipediaReader
An API Reader for Wikipedia

This program fetches a Wikipedia page and report the top n words on that page.
The parameters to the program should be 'page_id' and 'n'.

When two or more words have the same frequency, they are included on the same line separated by a comma.
A word is defined as a sequence of at least four alphabetic characters.

**n** and **page_id** are defined in _src/main/resource/config.properties_ 
as **page.wordsToDisplay** and **page.pageID** respectively. These can be updated at
anytime to change the page fetched and words displayed.

Furthermore, there is an *examples* directory on the root of the project which contains 
typically output from the project where **n** is equal to 5 and equal to 8. These were
included for the purposes of showing the simplicity with which the program functionality
can be changed by updating these parameters.

This is a Java project, built using Maven. The JDK used is 1.8 
and the project is mostly conventional Java 7 with elements of Java 8 included (particularly Streams).

### Directory Structure

The project comprises of 8 Java Classes in 4 packages.

    - api 
        - APICaller.java
    - app 
        - ApplicationLogger.java
        - Main.java
    - files
        - JSONManager.java
        - PropertyManager.java
    - wikipedia
        - Pages.java
        - Query.java
        - Wikipedia.java
        
The packages details are as follows:
* api - _Apache HTTPClient implementation to handle API calls_
* app - _The Main class and the Application Logger_
* files - _File managers to format JSON and Property files_
* wikipedia -  _The POJO objects which Jackson will map to JSON values_

### Dependencies

The project uses a few external libraries in the interest of effort saving.
Centrally the project relies on the following:
* Lombok (For mapping of Getters, Setters (etc.) in POJOs).
* Apache HttpClient (For getting and saving of API call(s) in the project).
* Jackson (for mapping JSON values to project POJOs).
* JUnit (for Unit Testing).
* Mockito (for Mocking objects during testing).

Specific versions of all these dependencies can be found in _pom.xml_.

### Project Build Process

In order to build this project, you will need to have either Maven installed & registered in your PATH variable,
or you will require an IDE with an implementation of Maven. The command to build the code is:

````
mvn clean install
````

If this build is successful, the "_target_" directory will be populated with the _.jar_ file
which can be executed from a terminal (with Java in the PATH) or anywhere else where .jar files are 
typically executed. The .jar to run is the one packaged **with dependencies**. With the command below:

````
java -jar target/wiki-reader-1.0-SNAPSHOT-jar-with-dependencies.jar
````

The above command assumes that one is at the ROOT directory of the project.

### Secrets Management

Since the project does not require any complex logins or authentication, there are no credentials stored anywhere here.
However, there is still quasi-secret information which has been factored out to the 'src/main/resources/config.properties'
file. This is also good practise since it allows the URI for the API call to be changed more easily since it
will only have to be edited in one place (the aforementioned config file).

Furthermore, the specific PageID on Wikipedia for which the project searches can also be edited in this
config file, under the property _page.pageID_.

### Future Improvements

The following list are acknowledgements of where the project could be improved in future development cycles.

* Testing. First and foremost, the standard of testing across the project is low given the time constraints in the development cycle.
In future, more complex acceptance and load testing would have occurred. Alongside more sophisticated mocking.

* Springboot.The project, were it to be deployed, would have benefited from being architected within a modern "quick and easy" framework
and this could easily port into a frontend service like Angular for WebApp deployment.

* Database. The project currently runs, but only outputs information to the console. In a Production scenario,
we would prefer the project to persist data gathered in some way, such as Amazon DynamoDB or similar.

* Documentation and Logging. The documentation and logging and quite ad-hoc. During future cycles, we will
focus on bringing this to enterprise standards and including UML diagrams and Lucid charts (or similar).