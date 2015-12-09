package com.github.kkrull.runner;

import com.github.kkrull.jbehave.SingleStoryRunner;
import com.github.kkrull.steps.SystemSteps;

public class SystemRunner extends SingleStoryRunner {
    protected String storyFilename() { return "system.story"; }
    protected Object stepDefinitionClass() { return new SystemSteps(); }
}
