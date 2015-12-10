package com.github.kkrull.runner;

import com.github.kkrull.jbehave.BaseJBehaveRunner;
import com.github.kkrull.steps.AcceptanceFailingSteps;

public class AcceptanceRunnerFailing extends BaseJBehaveRunner<AcceptanceFailingSteps> {
    protected String storyFilenameGlob() { return "acceptance_failing.story"; }
    protected AcceptanceFailingSteps newStepDefinitions() { return new AcceptanceFailingSteps(); }
}
