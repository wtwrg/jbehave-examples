package com.github.kkrull.jbehave;

import com.github.kkrull.jbehave.atm.ATMSteps;
import com.github.kkrull.jbehave.oracle.OracleSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.junit.Test;

import java.util.List;

import static org.jbehave.core.reporters.Format.ANSI_CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

public class JBehaveStories extends JUnitStories {
    @Test //Make it easier for IDEs to recognize this as a test
    @Override
    public void run() throws Throwable { super.run(); }

    @Override
    protected List<String> storyPaths() {
        StoryFinder finder = new StoryFinder();
        return finder.findPaths(CodeLocations.codeLocationFromClass(getClass()), "**/*.story", "");
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
            new ATMSteps(),
            new OracleSteps());
    }

    @Override
    public Configuration configuration() {
        StoryReporterBuilder storyReporter = new StoryReporterBuilder().withFormats(ANSI_CONSOLE, HTML);
        return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(getClass()))
            .useStoryReporterBuilder(storyReporter)
            .useStepMonitor(new SilentStepMonitor());
    }
}
