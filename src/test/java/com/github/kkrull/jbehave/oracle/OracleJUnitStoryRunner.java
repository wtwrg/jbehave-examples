package com.github.kkrull.jbehave.oracle;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;

import static org.jbehave.core.reporters.Format.ANSI_CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

public class OracleJUnitStoryRunner extends JUnitStory {

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), stepDefinitionClass());
    }

    @Override
    public Configuration configuration() {
        Configuration configuration = new MostUsefulConfiguration();
        configuration.useStoryPathResolver(storyPathResolver());
        configuration.useStoryReporterBuilder(storyReporter());
        return configuration;
    }

    private StoryReporterBuilder storyReporter() {
        return new StoryReporterBuilder().withFormats(ANSI_CONSOLE, HTML);
    }

    private StoryPathResolver storyPathResolver() {
        return runnerClass -> pathMatchingGlob(String.format("**/%s", storyFilename()));
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

    private static class StoryNotFound extends RuntimeException {
        public static StoryNotFound forGlobPattern(String glob) {
            String message = String.format("No stories found matching pattern: %s", glob);
            return new StoryNotFound(message);
        }

        public StoryNotFound(String message) {
            super(message);
        }
    }

    private static class MultipleStoriesFound extends RuntimeException {
        public static MultipleStoriesFound forGlobPattern(String glob, List<String> matchingPaths) {
            String message = String.format("Multiple stories found matching pattern '%s': %s", glob, matchingPaths);
            return new MultipleStoriesFound(message);
        }

        public MultipleStoriesFound(String message) {
            super(message);
        }
    }

    /* Configurable */

    protected String storyFilename() { return "oracle.story"; }
    private Object stepDefinitionClass() { return new OracleSteps(); }
}
