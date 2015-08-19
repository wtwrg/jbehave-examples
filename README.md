# An Example  [JBehave](http://jbehave.org/) Project using Maven
This is an example maven JBehave project that uses maven to build and run some simple scenarios based around withdrawing cash from an ATM.

## Background

JBehave is a test automation tool following the principles of Behavioural Driven Design and living documentation. Specifications are written in a concise human readable form and executed in continuous integration. 

## Use

After you have installed maven: mvn clean install will run the tests and produce the test output.


# Kyle's notes

Run with `mvn integration-test`.

Topology:

- `src/test/<package>/.story`: The Gherkin-ish syntax
- `src/test/<package>/_Scenario`: Identifies story files and associates them with step definition classes
- `src/test/<package>/_Steps`: The step definitions that match the Given/When/Then in the .story file

# Questions

- Will a failed JBehave cause the build to fail?  If not, look at the failsafe plugin.
- Can the story file be put in `src/test/resources`?
- What extra files are needed for 2+ story files?
- Can it co-exist with JUnit tests with separate `test` and `integration-test` phases?
