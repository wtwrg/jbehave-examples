Scenario: Failing acceptance test
Given a broken feature
When I run its acceptance tests
Then those tests pass
