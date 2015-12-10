package com.github.kkrull.steps;

import com.github.kkrull.oracle.Machine;
import com.github.kkrull.oracle.Oracle;
import org.jbehave.core.annotations.*;

import java.util.LinkedList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AcceptancePassingSteps {

    private Machine subject;
    private String answer;

    private final List<String> whatRan = new LinkedList<>();

    @BeforeStory
    public void runBeforePassingStory() {
        System.out.println("AcceptancePassingSteps.runBeforePassingStory");
        whatRan.add("BeforeStory");
    }

    @AfterStory
    public void runAfterPassingStory() {
        whatRan.add("AfterStory");
    }

    @Given("a working feature")
    public void givenAWorkingFeature() {
        this.subject = new Oracle();
    }

    @When("I run its acceptance tests")
    public void whenIRunItsAcceptanceTests() {
        this.answer = subject.getAnswerToTheUniverse();
    }

    @Then("it should run fixture methods before my story")
    public void thenItShouldRunFixtureMethodsBeforeMyStory() {
        assertThat(whatRan, equalTo(newArrayList("BeforeStory")));
    }

    @Then("those tests pass")
    public void thenThoseTestsPass() {
        assertThat(answer, equalTo("Forty two"));
    }
}
