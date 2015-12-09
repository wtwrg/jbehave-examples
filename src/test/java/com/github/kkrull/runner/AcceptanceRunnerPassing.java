package com.github.kkrull.runner;

import com.github.kkrull.jbehave.BaseJBehaveRunner;
import com.github.kkrull.steps.AcceptanceSteps;

//TODO KDK: Reports failure during view generation if there was a prior failure, even if this particular test passed.  Try not generating the view as part of the runner and doing it in the post-integration-test phase instead.
public class AcceptanceRunnerPassing extends BaseJBehaveRunner<AcceptanceSteps> {
    protected String storyFilenameGlob() { return "acceptance_passing.story"; }
    protected AcceptanceSteps newStepDefinitions() { return new AcceptanceSteps(); }
}
