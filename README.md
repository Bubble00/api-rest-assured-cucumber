Overview
This project is designed to implement and run automated tests for APIs using Maven, Cucumber, and JUnit. It integrates with GitHub Actions for continuous integration (CI), ensuring that test suites are automatically executed upon code changes or new commits. This setup enables consistent, reliable testing and reporting of results.

Key Features
Cucumber BDD: Allows writing test cases in a Gherkin format, making them easy to read and maintain.
JUnit Integration: Manages test execution and provides test reporting.
Maven Surefire Plugin: Ensures tests are executed as part of the Maven lifecycle.
GitHub Actions CI/CD: Automates test execution on code pushes and pull requests.

Prerequisites
Java 11 or higher: Ensure Java is installed and configured in your system.
Maven 3.6 or higher: Required for building and managing the project dependencies.
Git: For version control.
IDE (e.g., VS Code, IntelliJ IDEA): Recommended for development and debugging.

Getting Started
1. Clone the repository to your local machine
2. Building the project
   mvn clean install
3. Running tests locally
   mvn test
