package com.github.kkrull.steps;

import com.github.kkrull.oracle.Machine;
import com.github.kkrull.oracle.Oracle;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SystemSteps {

    private Machine subject;
    private String answer;

    @Given("a working system")
    public void givenAWorkingSystem() {
        this.subject = new Oracle();
    }

    @When("I run its system tests")
    public void whenIRunItsSystemTests() {
        this.answer = subject.getAnswerToTheUniverse();
    }

    @Then("those tests pass")
    public void thenThoseTestsPass() {
        assertThat(answer, equalTo("Forty two"));
    }
}
