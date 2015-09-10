Narrative: Use a test fixture
    As a developer
    I want a test fixture to run before and after my scenario
    In order to start and stop services I need during the test

Meta:
@storyFixture on
@somethingElse off

Scenario: The fixture should run when the scenario is tagged appropriately
!--Note that commenting out a meta tag has no effect.  Fixture methods are still called with whatever value is assigned.
Given I have a before-story method tagged to set up a test fixture
When I run this scenario
Then that method has been executed
