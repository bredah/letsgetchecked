# Lets Get Checked

This example contains only two scenarios: one presents complete validation and another presents failure in which it presents evidence where it failed (screenshot).

## Required software

- Java JDK 11+
- Maven installed and in your classpath

### Execution types

There are two execution types: **local** and **remote**.

The `TargetFactory` class will resolve the target execution based on the `target` property value located on `environment.properties` file.

Its usage is placed on the `BaseWeb` class before each test execution.

#### Local execution
This execution type uses [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) class to instantiate the web browsers.

When the `target` is `local` the `createDriver()` method is used from the `BrowserFactory` class to return the browser instance.

The browser used in the test is placed on the `browser` property in the `environment.properties` file. 

Here are some examples of how to run:

```sh
# browser: chrome (local)
browser=chrome mvn test

# browser: firefox (local)
browser=chrome mvn test

# browser: chrome (remote)
target=remote grid_url=localhost grid_port=4444 browser=chrome mvn test

# browser: firefox (remote)
target=remote grid_url=localhost grid_port=4444 browser=firefox mvn test
```

## Generating the test report

This project uses Allure Report to automatically generate the test report. There are some configuration to make it happen:

- aspectj configuration on `pom.xml` file

You can use the command line to generate it in two ways:

- `mvn allure:serve`: will open the HTML report into the browser
- `mvn allure:report`: will generate the HTML port at target/site/allure-maven-plugin folder

## About the project

## src/main/java

### test

Tests used in the example of using Selenium + TestNG libraries

### config

The Configuration class is the connections and authentication data between the `environment.properties` and `grid.properties` properties file located in `src/test/resources/`.


## src/test/resources

It has a `schemas` folder with the JSON Schemas to enable Contract Testing using Rest-Assured. Also, the properties file to easily configure the API URI.

## Libraries

* [Java 11](https://openjdk.java.net/projects/jdk/11/) as the programming language
* [JUnit](https://junit.org/junit5/) as the UnitTest framework to support the test creation
* [Selenium WebDriver](https://www.selenium.dev/) as the web browser automation framework using the Java binding
* [Allure Report](https://docs.qameta.io/allure/) as the testing report strategy
* [Log4J2](https://logging.apache.org/log4j/2.x/) as the logging management strategy
* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) as the Selenium binaries management
* [Owner](http://owner.aeonbits.org/) to minimize the code to handle the properties file

## Future improvements

- Validate support for multiple browsers;
- Improve test scenarios, they have many lines generating a lot of maintenance over time;
- Clone the project and start cucumber support. This change will generate benefits when developing scripts for ATDD;

## Inspect project dependencies

```sh
mvn dependency-check:check
```

- Update maven local cache

```sh
mvn dependency-check:update-only
```

> For more details, access [the documentation](https://jeremylong.github.io/DependencyCheck/dependency-check-maven/plugin-info.html).