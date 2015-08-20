package com.github.kkrull.jbehave;

import com.github.kkrull.jbehave.atm.ATMScenarioSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.junit.Test;

import java.util.List;

import static org.jbehave.core.reporters.Format.ANSI_CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

public class OracleScenario extends JUnitStory {
    @Test //Make this recognizable to IDEs so you can run the scenario directly without making an extra Runner class.
    public void run() throws Throwable { super.run(); }

    @Override
    public Configuration configuration() {
        StoryReporterBuilder storyReporter = new StoryReporterBuilder()
            .withDefaultFormats()
            .withFormats(ANSI_CONSOLE, HTML)
            ;
        Configuration configuration = new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryReporterBuilder(storyReporter)
            .useStepMonitor(new SilentStepMonitor())
//            .useDefaultStoryReporter(new SilentSuccessFilter(new ConsoleOutput()))
            ;

        return configuration;
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new OracleSteps())
            .createCandidateSteps();
    }
}
