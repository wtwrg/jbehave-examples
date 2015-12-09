package com.github.kkrull.steps;

import com.github.kkrull.oracle.Dunce;
import com.github.kkrull.oracle.Machine;
import com.github.kkrull.oracle.Oracle;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AcceptanceSteps {

    private Machine subject;
    private String answer;

    @Given("a broken feature")
    public void givenABrokenFeature() {
        this.subject = new Dunce();
    }

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
