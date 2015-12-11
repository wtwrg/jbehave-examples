package com.github.kkrull.jbehave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.jbehave.core.reporters.Format.*;

/**
 * Runs a single JBehave story with a single step definition class.
 *
 * This runner is suitable for step definitions that use @BeforeStory / @AfterStory or @BeforeScenario / @AfterScenario.
 *
 * There are a number of ways to configure how long a story can run before it's assumed to be failing and cancelled.
 *
 * - The 'storyTimeouts' system property, if present, takes precedence over all other values.  Use this if it is
 *   necessary to change the timeout at runtime, for example if you can not re-compile or if the tests take longer
 *   than usual to run in your environment.
 * - If there is no system property but you override this method to return a value, that value is used as a timeout.
 *   Use this when you have a story for which you know - in most circumstances - JBehave's default timeout would be
 *   inappropriate.
 * - If neither of these timeouts are set, the JBehave default timeout will be used.
 *   This value is currently not well-documented, but is visible in the jbehave-core source code:
 *   https://github.com/jbehave/jbehave-core/blob/master/jbehave-core/src/main/java/org/jbehave/core/embedder/StoryTimeouts.java
 */
public abstract class BaseJBehaveRunner<S> extends JUnitStory {
    /* Step definitions */

    @Override
    public final InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), newStepDefinitions());
    }

    /** @return An instance of the one and only class containing step definitions for your story */
    protected abstract S newStepDefinitions();

    /* Configuration */

    @Override
    public final Configuration configuration() {
        return new MostUsefulConfiguration()
            .useStoryPathResolver(singleStoryFileResolver(storyFilenameGlob()))
            .useStoryReporterBuilder(storyReporter());
    }

    private StoryPathResolver singleStoryFileResolver(String storyFilename) {
        return runnerClass -> pathMatchingGlob(String.format("**/%s", storyFilename));
    }

    private String pathMatchingGlob(String glob) {
        StoryFinder finder = new StoryFinder();
        List<String> paths = finder.findPaths(CodeLocations.codeLocationFromClass(getClass()), glob, "");
        switch(paths.size()) {
            case 0: throw StoryNotFound.forGlobPattern(glob);
            case 1: return paths.get(0);
            default: throw MultipleStoriesFound.forGlobPattern(glob, paths);
        }
    }

    /** @return A glob pattern matching the single JBehave .story file to run.  Relative to src/{main|test}/resources */
    protected abstract String storyFilenameGlob();

    private StoryReporterBuilder storyReporter() {
        return new StoryReporterBuilder()
            .withFormats(ANSI_CONSOLE, STATS, XML, HTML)
            .withFailureTrace(true);
    }

    /* Embedder */

    @Override
    public final Embedder configuredEmbedder() {
        Embedder embedder = super.configuredEmbedder();
        List<String> metaFilters = metaFilters();
        embedder.useMetaFilters(metaFilters);
        storyTimeouts().ifPresent(x -> embedder.embedderControls().useStoryTimeouts(x));
        return embedder;
    }

    private List<String> metaFilters() {
        String metaFilterOverride = System.getProperty("meta.filter");
        String metaFilterLine = Optional.ofNullable(metaFilterOverride).orElse("-skip");
        return Arrays.stream(metaFilterLine.split("(?=[-+])"))
            .map(String::trim)
            .collect(toList());
    }

    private Optional<String> storyTimeouts() {
        Optional<String> timeoutsOverride = storyTimeoutsOverride();
        Optional<String> timeoutFromRunner = storyTimeout()
            .map(this::durationToSeconds)
            .map(String::valueOf);
        return Stream.of(timeoutsOverride, timeoutFromRunner)
            .filter(Optional::isPresent)
            .findFirst()
            .orElse(Optional.empty()); //Revert to the JBehave default
    }

    private long durationToSeconds(Duration duration) {
        return duration.toMillis() / 1000;
    }

    private Optional<String> storyTimeoutsOverride() {
        //Supports units such as "10s" and "5m", defaulting to seconds if no units given.
        //http://jbehave.org/reference/stable/story-timeouts.html
        return Optional.ofNullable(System.getProperty("storyTimeouts"));
    }

    /** @return The time to allow for this story to run, unless overridden at runtime with 'storyTimeouts' */
    protected Optional<Duration> storyTimeout() {
        return Optional.empty();
    }
}
