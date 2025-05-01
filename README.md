# fanCode

# API Testing Framework

This is a comprehensive API testing framework designed for automated testing of RESTful APIs. It utilizes tools like **Cucumber (BDD)**, **RestAssured**, and **ExtentReports** for reporting. The framework is designed to execute API tests, validate the responses, and generate detailed reports.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Test Environment Configuration](#test-environment-configuration)
- [Running Tests](#running-tests)
- [Test Reporting](#test-reporting)
- [Parallel Test Execution](#parallel-test-execution)
- [CI/CD Integration](#cicd-integration)
- [Project Structure](#project-structure)

## Prerequisites

Before running the tests, make sure you have the following installed:

- Java (JDK 8 or higher)
- Maven
- IDE (e.g., Eclipse, IntelliJ IDEA)
- Git

## Setup Instructions

1. **Clone the Repository:**
   Clone the repository to your local machine using the following command:
   ```bash
   git clone https://github.com/TharuAutomation/fanCode.git
2. **Navigate into the project directory:**
   ```bash
      cd fanCode
3. **Install dependencies:**
   ```bash
   mvn clean install

## Test Environment Configuration
   All environment settings are managed via config.properties located in src/test/resources/.
   Example configuration:
   baseURI=http://jsonplaceholder.typicode.com
   
## Running Tests
1. **To run all tests:**
   ```bash
   mvn test
2. **To run all tests in parallel:**
   Ensure TestRunner.java is configured for parallel execution and then run mvn clean test

## Test Reporting
1. **Extent Reports:**
   target/extent-report.html
2. **Cucumber Reports:**
   target/cucumber-reports/ and \src\test\resources\output

## Parallel Test Execution
   This framework supports parallel execution via Maven Surefire and Cucumber.
   Make sure your step definitions and test data are thread-safe.
   To configure:
      Use maven-surefire-plugin with thread count set in pom.xml.
      Use unique data or avoid shared state in tests.
      
## CI/CD Integration
   You can integrate this framework into your CI/CD pipelines:
   Example setups:
      Jenkins: Configure a Maven pipeline job.
      GitLab CI: Add the following to .gitlab-ci.yml:   
         test-api:
           script:
             - mvn clean test
markdown
## 📁 Project Structure
fanCode/ ├── src/ │ ├── main/ │ │ └── java/ │ │ └── utils/ │ │ ├── ApiUtils.java │ │ └── ErrorCaptureUtil.java │ ├── test/ │ ├── resources/ │ │ ├── features/ │ │ │ └── fancode_todo_testdata.feature │ │ ├── output/ │ │ ├── config.properties │ │ └── cucumber.properties │ │ ├── java/ │ │ ├── runner/ │ │ │ └── TestRunner.java │ │ ├── stepdefs/ │ │ │ └── TodoStepDefs.java │ │ └── Hooks.java │ ├── target/ │ ├── extent-report.html │ ├── logs/ │ └── cucumber-reports/ │ ├── pom.xml └── README.md

