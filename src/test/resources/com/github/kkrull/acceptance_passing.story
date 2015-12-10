Scenario: Passing acceptance test
Given a working feature
When I run its acceptance tests
Then it should run fixture methods before my story
And those tests pass