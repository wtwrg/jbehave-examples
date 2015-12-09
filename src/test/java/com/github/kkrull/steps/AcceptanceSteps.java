package com.github.kkrull.steps;

import com.github.kkrull.oracle.Oracle;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AcceptanceSteps {

    private Oracle subject;
    private String answer;

    @Given("a working feature")
    public void givenAWorkingFeature() {
        this.subject = new Oracle();
    }

    @When("I run its acceptance tests")
    public void whenIRunItsAcceptanceTests() {
        this.answer = subject.getAnswerToTheUniverse();
    }

    @Then("those tests pass")
    public void thenThoseTestsPass() {
        assertThat(answer, equalTo("Forty two"));
    }
}
