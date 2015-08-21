# An Example  [JBehave](http://jbehave.org/) Project using Maven


## Running

Run JBehave tests with `mvn integration-test`.  Actually, it runs in whatever lifecycle phase you bind it to in
`executions/execution/phase` in `jbehave-maven-plugin`'s `build` section.  As long as you pick a phase after `test`,
running that will run *both* JUnit and JBehave tests.

Run JUnit tests like usual with `mvn test`.

What if you just want to run JBehave tests, or a specific JBehave test?  Add the following to the `Scenario` class
(the one that extends `JUnitStory`):

```
public class ATMScenario extends JUnitStory {
    @Test //Make this recognizable to IDEs so you can run the scenario directly without making an extra Runner class.
    public void run() throws Throwable { super.run(); }

    @Override
    public Configuration configuration() { ... }

    ...
}
```

With the way JBehave is currently configured (i.e. nobody is telling it to ignore failures in tests), a failed story
will fail the overall result of `mvn integration-test`.  Good.

Running with `jbehave-maven-plugin` works fine when there's 1 scenario.  When adding a second scenario, it runs the
first scenario and then fails with the following:


```
[INFO] Generating reports view to '/home/kkrull/Workspace/sandbox/jbehave-example/target/jbehave' using formats '[stats, ansi_console, html]' and view properties '{navigator=ftl/jbehave-navigator.ftl, views=ftl/jbehave-views.ftl, reports=ftl/jbehave-reports.ftl, nonDecorated=ftl/jbehave-report-non-decorated.ftl, decorated=ftl/jbehave-report-decorated.ftl, maps=ftl/jbehave-maps.ftl}'
[INFO] Reports view generated with 2 stories (of which 0 pending) containing 1 scenarios (of which 0 pending)
[INFO] Running embeddable com.github.kkrull.jbehave.atm.ATMScenario
[INFO] Processing system properties {}
[INFO] Using controls UnmodifiableEmbedderControls[EmbedderControls[batch=false,skip=false,generateViewAfterStories=true,ignoreFailureInStories=false,ignoreFailureInView=false,verboseFailures=false,verboseFiltering=false,storyTimeouts=300,threads=1,failOnStoryTimeout=false]]

(BeforeStories)

[INFO] Generating reports view to '/home/kkrull/Workspace/sandbox/jbehave-example/target/jbehave' using formats '[stats, ansi_console, html]' and view properties '{navigator=ftl/jbehave-navigator.ftl, views=ftl/jbehave-views.ftl, reports=ftl/jbehave-reports.ftl, nonDecorated=ftl/jbehave-report-non-decorated.ftl, decorated=ftl/jbehave-report-decorated.ftl, maps=ftl/jbehave-maps.ftl}'
[INFO] Reports view generated with 2 stories (of which 0 pending) containing 1 scenarios (of which 0 pending)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.317 s
[INFO] Finished at: 2015-08-21T13:57:38-05:00
[INFO] Final Memory: 21M/209M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.jbehave:jbehave-maven-plugin:4.0.3:run-stories-as-embeddables (run-stories) on project jbehave-example: Failed to run stories as embeddables: Failure in running embeddable: com.github.kkrull.jbehave.atm.ATMScenario: Task java.util.concurrent.FutureTask@7da31a40 rejected from java.util.concurrent.ThreadPoolExecutor@28ee7bee[Terminated, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 1] -> [Help 1]
```

This happens whether there are two `execution/configuration/includes/include` with wildcards, or one with a
sufficiently-relaxed wildcard to match both.  So we need another plugin, because `jbehave-maven-plugin` is jacked up.


## Files

- `src/test/<package>/.story`: The Gherkin-ish syntax
- `src/test/<package>/_Scenario`: Identifies story files and associates them with step definition classes
- `src/test/<package>/_Steps`: The step definitions that match the Given/When/Then in the .story file

Can the story file be renamed?  Why does renaming cause the storyDurations.props file to go missing?  Oh, that's
because there is a poorly-documented convention that is described in [Putting the pieces
together](https://blog.codecentric.de/en/2011/03/automated-acceptance-testing-using-jbehave/).  Not getting the
`.story` and `Scenario` filenames (and paths) synchronized can lead to not finding any stories, which in turn can lead
to not copying `storyDurations.props`, which in turn leads to a meaningless exception for each story that really
clutters the output.  Nice going, JBehave.


## Console Output

- `StoryReporterBuilder.withFormats(CONSOLE)`: '(BeforeStories)', story filenames, contents of story files.
- `StoryReporterBuilder.withFormats(ANSI_CONSOLE)`: The same as `CONSOLE`, but with color output.
- Can output be limited to only show status and errors?  There's a [FAQ](http://jbehave.org/reference/stable/faq.html)
  on that.


## Questions

- What extra files are needed for 2+ story files?
