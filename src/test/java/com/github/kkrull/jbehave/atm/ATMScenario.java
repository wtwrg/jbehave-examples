package com.github.kkrull.jbehave.atm;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.*;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;

public class ATMScenario extends JUnitStory {

    // Here we specify the configuration, starting from default MostUsefulConfiguration, and changing only what is needed
    @Override
    public Configuration configuration() {
        StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
            .withDefaultFormats()
            .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML);
        StoryReporter filter = new SilentSuccessFilter(new ConsoleOutput());
        Configuration configuration = new MostUsefulConfiguration()
            .useDefaultStoryReporter(filter)
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
//            .useStoryReporterBuilder(reporterBuilder)
            ;

        return configuration;
    }

    // Here we specify the steps classes
    @Override
    public List<CandidateSteps> candidateSteps() {
        // varargs, can have more that one steps classes
        return new InstanceStepsFactory(configuration(), new ATMScenarioSteps())
            .createCandidateSteps();
    }
}
