package com.github.kkrull.jbehave.oracle;

import com.github.kkrull.jbehave.junit.SingleStoryRunner;

public class OracleJUnitStoryRunner extends SingleStoryRunner {
    protected String storyFilename() { return "oracle.story"; }
    protected Object stepDefinitionClass() { return new OracleSteps(); }
}
