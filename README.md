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

Output:

- `StoryReporterBuilder.withFormats(CONSOLE)`: '(BeforeStories)', story filenames, contents of story files.
- `StoryReporterBuilder.withFormats(ANSI_CONSOLE)`: The same as `CONSOLE`, but with color output.


# Questions

- Can the story file be renamed?  Why does renaming cause the storyDurations.props file to go missing?  Oh, that's
  because there is a poorly-documented convention that is described in [Putting the pieces
  together](https://blog.codecentric.de/en/2011/03/automated-acceptance-testing-using-jbehave/).  Not getting the
  `.story` and `Scenario` filenames (and paths) synchronized can lead to not finding any stories, which in turn can lead
  to not copying `storyDurations.props`, which in turn leads to a meaningless exception for each story that really
  clutters the output.  Nice going, JBehave.
- Will a failed JBehave cause the build to fail?  If not, look at the failsafe plugin.
- What extra files are needed for 2+ story files?
- Can it co-exist with JUnit tests with separate `test` and `integration-test` phases? Looks like it, as long as it's
  bound to the `integration-test` phase in `build/.../execution/phase`
- Can output be limited to only show status and errors?  There's a [FAQ](http://jbehave.org/reference/stable/faq.html)
  on that.

## Annoying output

```
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building JBehave examples 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ jbehave-example ---
[INFO] Deleting /home/kkrull/Workspace/sandbox/jbehave-example/target
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ jbehave-example ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/kkrull/Workspace/sandbox/jbehave-example/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ jbehave-example ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 3 source files to /home/kkrull/Workspace/sandbox/jbehave-example/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ jbehave-example ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ jbehave-example ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to /home/kkrull/Workspace/sandbox/jbehave-example/target/test-classes
[WARNING] /home/kkrull/Workspace/sandbox/jbehave-example/src/test/java/com/github/kkrull/jbehave/atm/ATMScenario.java: /home/kkrull/Workspace/sandbox/jbehave-example/src/test/java/com/github/kkrull/jbehave/atm/ATMScenario.java uses or overrides a deprecated API.
[WARNING] /home/kkrull/Workspace/sandbox/jbehave-example/src/test/java/com/github/kkrull/jbehave/atm/ATMScenario.java: Recompile with -Xlint:deprecation for details.
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ jbehave-example ---
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ jbehave-example ---
[INFO] Building jar: /home/kkrull/Workspace/sandbox/jbehave-example/target/jbehave-example-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- jbehave-maven-plugin:4.0.3:run-stories-as-embeddables (run-stories) @ jbehave-example ---
[INFO] Running stories as embeddables using embedder Embedder[storyMapper=StoryMapper,embedderMonitor=MavenEmbedderMonitor,classLoader=EmbedderClassLoader[urls=[/home/kkrull/Workspace/sandbox/jbehave-example/target/test-classes/, /home/kkrull/Workspace/sandbox/jbehave-example/target/classes/, junit-4.8.2.jar, jbehave-core-4.0.3.jar, hamcrest-core-1.3.jar, hamcrest-library-1.3.jar, hamcrest-integration-1.3.jar, commons-collections-3.2.1.jar, commons-io-2.4.jar, commons-lang-2.6.jar, plexus-utils-3.0.10.jar, freemarker-2.3.20.jar, paranamer-2.4.jar, xstream-1.4.7.jar, xmlpull-1.1.3.1.jar, xpp3_min-1.1.4c.jar],parent=ClassRealm[plugin>org.jbehave:jbehave-maven-plugin:4.0.3, parent: sun.misc.Launcher$AppClassLoader@4e25154f]],embedderControls=UnmodifiableEmbedderControls[EmbedderControls[batch=false,skip=false,generateViewAfterStories=true,ignoreFailureInStories=false,ignoreFailureInView=false,verboseFailures=false,verboseFiltering=false,storyTimeouts=300,threads=1,failOnStoryTimeout=false]],embedderFailureStrategy=<null>,configuration=org.jbehave.core.configuration.MostUsefulConfiguration@5c8e67b9,candidateSteps=<null>,stepsFactory=<null>,metaFilters=<null>,metaMatchers=<null>,systemProperties=<null>,executorService=<null>,executorServiceCreated=false,performableTree=PerformableTree,storyManager=<null>,timeoutParsers=<null>]
[INFO] Found class names: [com.github.kkrull.jbehave.atm.ATMScenario]
[INFO] Using controls UnmodifiableEmbedderControls[EmbedderControls[batch=false,skip=false,generateViewAfterStories=true,ignoreFailureInStories=false,ignoreFailureInView=false,verboseFailures=false,verboseFiltering=false,storyTimeouts=300,threads=1,failOnStoryTimeout=false]]
[INFO] Running embeddable com.github.kkrull.jbehave.atm.ATMScenario
[INFO] Processing system properties {}
[INFO] Using controls UnmodifiableEmbedderControls[EmbedderControls[batch=false,skip=false,generateViewAfterStories=true,ignoreFailureInStories=false,ignoreFailureInView=false,verboseFailures=false,verboseFiltering=false,storyTimeouts=300,threads=1,failOnStoryTimeout=false]]

(BeforeStories)

[INFO] Running story com/github/kkrull/jbehave/atm/a_t_m_scenario.story

(com/github/kkrull/jbehave/atm/a_t_m_scenario.story)
Scenario: Account has sufficient funds
Examples:
Given the Account balance is <account_balance>
When the card is valid
When the machine contains <atm_available>
When the Account Holder requests <request>
Then the ATM should dispense <result>
Then the account balance should be <newBalance>
Then the card should be returned

|account_balance|atm_available|request|result|newBalance|
|100|100|20|20|80|
|100|100|10|10|90|

Example: {account_balance=100, atm_available=100, request=20, result=20, newBalance=80}
Given the Account balance is 100
When the card is valid
When the machine contains 100
When the Account Holder requests 20
Then the ATM should dispense 20
Then the account balance should be 80
Then the card should be returned

Example: {account_balance=100, atm_available=100, request=10, result=10, newBalance=90}
Given the Account balance is 100
When the card is valid
When the machine contains 100
When the Account Holder requests 10
Then the ATM should dispense 10
Then the account balance should be 90
Then the card should be returned




(AfterStories)

[INFO] Generating reports view to '/home/kkrull/Workspace/sandbox/jbehave-example/target/jbehave' using formats '[stats, console, html]' and view properties '{navigator=ftl/jbehave-navigator.ftl, views=ftl/jbehave-views.ftl, reports=ftl/jbehave-reports.ftl, nonDecorated=ftl/jbehave-report-non-decorated.ftl, decorated=ftl/jbehave-report-decorated.ftl, maps=ftl/jbehave-maps.ftl}'
[INFO] Reports view generated with 2 stories (of which 0 pending) containing 1 scenarios (of which 0 pending)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.876 s
[INFO] Finished at: 2015-08-19T16:47:13-05:00
[INFO] Final Memory: 21M/217M
[INFO] ------------------------------------------------------------------------
```
