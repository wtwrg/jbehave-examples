package com.github.kkrull.jbehave.atm;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.ConsoleOutput;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.SilentSuccessFilter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;

import java.util.List;

import static org.jbehave.core.reporters.Format.*;

public class ATMScenario extends JUnitStory {

    @Test //Make this recognizable to IDEs so you can run the scenario directly without making an extra Runner class.
    public void run() throws Throwable { super.run(); }

    @Override
    public Configuration configuration() {
        Configuration configuration = new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(CONSOLE, TXT, HTML, XML))
//            .useDefaultStoryReporter(new SilentSuccessFilter(new ConsoleOutput()))
            ;

        return configuration;
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new ATMScenarioSteps())
            .createCandidateSteps();
    }
}
