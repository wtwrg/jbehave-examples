package com.github.kkrull.runner;

import com.github.kkrull.jbehave.SingleStoryRunner;
import com.github.kkrull.steps.OracleSteps;

public class OracleRunner extends SingleStoryRunner {
    protected String storyFilename() { return "oracle.story"; }
    protected Object stepDefinitionClass() { return new OracleSteps(); }
}
