package com.github.kkrull.runner;

import com.github.kkrull.jbehave.BaseJBehaveRunner;
import com.github.kkrull.steps.AcceptancePassingSteps;
import com.github.kkrull.steps.AcceptanceSteps;

public class AcceptanceRunnerPassing extends BaseJBehaveRunner<AcceptancePassingSteps> {
    protected String storyFilenameGlob() { return "acceptance_passing.story"; }
    protected AcceptancePassingSteps newStepDefinitions() { return new AcceptancePassingSteps(); }
}
