package com.github.kkrull.runner;

import com.github.kkrull.jbehave.BaseJBehaveRunner;
import com.github.kkrull.steps.SystemSteps;

public class SystemRunner extends BaseJBehaveRunner<SystemSteps> {
    protected String storyFilenameGlob() { return "system.story"; }
    protected SystemSteps newStepDefinitions() { return new SystemSteps(); }
}
