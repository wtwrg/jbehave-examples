package com.github.kkrull.jbehave.fixture;

import com.github.kkrull.jbehave.Steps;
import org.jbehave.core.annotations.*;

@Steps
public class FixtureSteps {
    @BeforeStory
    public void storyFixture_getsCalledWhetherTheTagIsThereOrNot_thatIsSoStupid(@Named("storyFixture") String onOrBlank) {
        System.out.println(String.format("--storyFixture: '%s'\n", onOrBlank));
        switch(onOrBlank.toLowerCase()) {
            case "on": TestFixture.setup(); return;
            default: return;
        }
    }

    @Given("I have a before-story method tagged to set up a test fixture")
    public void givenIHaveABeforestoryMethodTaggedToSetUpATestFixture() { /* do nothing */ }

    @When("I run this scenario")
    public void whenIRunThisScenario() { /* do nothing */ }

    @Then("that method has been executed")
    public void thenThatMethodHasBeenExecuted() {
        TestFixture.shouldHaveBeenSetUpOnce();
    }
}
