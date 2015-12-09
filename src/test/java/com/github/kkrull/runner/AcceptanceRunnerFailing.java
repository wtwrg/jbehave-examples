package com.github.kkrull.runner;

import com.github.kkrull.jbehave.BaseJBehaveRunner;
import com.github.kkrull.steps.AcceptanceSteps;

public class AcceptanceRunnerFailing extends BaseJBehaveRunner<AcceptanceSteps> {
    protected String storyFilenameGlob() { return "acceptance_failing.story"; }
    protected AcceptanceSteps newStepDefinitions() { return new AcceptanceSteps(); }
}
