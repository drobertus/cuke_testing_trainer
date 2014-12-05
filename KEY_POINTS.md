Key points:

The unit tests can use mocks or implementations for dependent objects.
But...
* dependent behavior is dependent on writing code, sometimes more one way than another.
* with mocking consistent behavior is dependent on the developer knowing what a real life response looks like
* with test implementations we may duplicate production code that might change

The product can be refactored many ways that would behave the same way (example- the createReport could call the
UserService itself, we could create a User object that is passed around, etc).
But...
* this would break unit tests that would then need to be updated, added, deleted
* the behavioral based tests will need much fewer changes, if any


Mid-level tests provide the following advantages:
* lower fragility when refactoring
* dependency responses are consistent (often completely) with minimal developer intervention (vs. mocks or test implementations of dependencies)


Mid-level these disadvantages relative to unit tests:
* Can be harder to set up
* longer run time
* deeper dependency chain
* harder/less timely to exercise complex logic with many execution paths

Cucumber/Gherkin driven tests have these advantages:
* ties back to specifications
* portable across implementations
* can operate at low, mid, or high level testing level

Cuke/Gherkin has these disadvantages
* requires extra work to implement
* reporting/error handling in JVM lines up poorly with JUnit standards
* run time higher dues to framework spinup

