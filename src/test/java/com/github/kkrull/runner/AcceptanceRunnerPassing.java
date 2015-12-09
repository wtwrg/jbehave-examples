package com.github.kkrull.runner;

import com.github.kkrull.jbehave.SingleStoryRunner;
import com.github.kkrull.steps.AcceptanceSteps;

public class AcceptanceRunnerPassing extends SingleStoryRunner {
    protected String storyFilename() { return "acceptance_passing.story"; }
    protected Object stepDefinitionClass() { return new AcceptanceSteps(); }
}
