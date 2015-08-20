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
