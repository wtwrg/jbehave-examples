# An Example  [JBehave](http://jbehave.org/) Project using Maven


## Running

Run with `mvn integration-test`.  Actually, it runs in whatever lifecycle phase you bind it to in
`executions/execution/phase` in `jbehave-maven-plugin`'s `build` section.


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

- Will a failed JBehave cause the build to fail?  If not, look at the failsafe plugin.
- What extra files are needed for 2+ story files?
- Can it co-exist with JUnit tests with separate `test` and `integration-test` phases? Looks like it, as long as it's
  bound to the `integration-test` phase in `build/.../execution/phase`
