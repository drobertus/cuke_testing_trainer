cuke_testing_trainer
====================

Training for Developers and SDETs


This is a short courses that covers unit and integration testing through several frameworks and
different levels f test, from unit to mid-level/service, to a mid-level test driven by the Cucumber BDD
Framework.
Spock, Junit, and Cucumber.

The use of Gherkin, TDD and BDD is examined from the perspective of Product Owners, developers and testers, with 
elements of the financial and productivity payoffs associated with proper use of TDD/BDD and properly early usage of
testing frameworks at the service and unit level.


__Our Project Use Case__

The starting point of the project is this:

There is an existing Report Processor service, but the decision has been ade to make it
accessible via a REST interface with two calls.

The calls are to create a report, and then to get the report.

To get a good feel for the desired behavior of these methods look at the "report_generator.feature"

The project as it stands compiles, has good code coverage, a basic unit test on the main
 business logic, and an integration test that stands up the service and exercises it with live
 HTTP calls.

__Unit tests vs. Midlevel/Service tests__

 To show how these different levels of testing can reveal the behavior of the app try this:

 Change the @Get annotation on ReportGenerator.show to be @Post

 The unit tests will still pass since they do not exercise the container and context of the service.
 The integration test will fail, though, since the test harness is geared to send a GET, not a POST
  request.

 As more and more behaviour is driven by the context of our business logic, by containers, annotations, and
 other wrappers around core logic, the more important this level of testing becomes.

 Set the annotation back to @Get.

__Adding Cucumber__

 Notice that we have a feature file that defines the desired behavior, but we are not using Cucumber (yet)
 to turn it into a truly runnable specification.

To do this we need to have a runner.  A Cucumber runner is a plugin to the JUnit framework that enabled
the Cucumber runtime to read a Gherkin feature file and run it.

You can run the feature file in two ways.  Through Gradle you will need to remove the @Ignore annotation from
the ReportGeneratorCukeTest class.  If you are using an IDE like IntelliJ you can add the
Gherkin and Cucumber plugins and then right click on the feature file itself and run it.

__Cucumber Framework Architecture__

* Steps- The simplest description of the steps files is a set of classes that have methods annotated
  with text that matches the Gherkin steps via a simple regex.  Parameters in the Gherkin script
  can be mapped and converted to method parameters in-line as part of the Cucumber engine.
  Steps should have minimal logic- most processing and should be handled in the Fixture layer.
  You can have as few as one Steps file, but it is good practice to separate them by type such as
  "client steps", "server steps", or "validation steps" and "action steps"  and so on.  There are
  Cucumber annotations for Before and After, much as in JUnit, that act as hidden steps.
* Fixtures- A Fixture is a wrapper around a piece (or pieces) of the code base being tested or used
 to drive the testing.  You can have as few or many as needed or practical.  It is good practice to wrap
 an individual service in its own Fixture, same for clients, database access, and so on.
* Using Injection to wire fixtures and steps together- the example code uses Guice injection to wire
the stateful Fixtures with the stateless Steps files.  Notice the @Inject annotation on the constructors
of the Steps, the @Singleton annotations on the Fixture files, the cucumber-guice.properties file, and the
StepsModule class.  Injection is not a required element of a test framework but is a best-practice.  The
injection framework used is defined through the build.gradle file (in this case the 'info.cukes:cucumber-guice:1.2.0'
dependency).  Available injection frameworks for Cucumber include Guice, Spring, and Picocontainer.
Guice was selected since it is also in use in the test project code itself.

__Generating Steps__

Cucumber runners give a big boost teo development by generating the basic steps that a feature files
needs.  Run the feature file through your IDE, or remove the Ignore of the runner and call "./gradlew
integTest".  The steps that are missing will be written out for you.

As it happens the steps for the feature file are already in place, commented out, on the ClientSteps
and ReportGeneratorSteps classes.  Uncomment those files and rerun your feature file/gradle command.

This time instead of a list of missing steps you should see a thrown PendingException that is the marker
for a step that is present but has not been implemented.

__Mapping Gherkin parameters and tables to Cucumber steps method parameters__

Notice that the steps that help setup the background, "these valid user" and "these valid reports"
already have their implementations wired in and their method signatures have been altered from the
default that was generated by the Cucumber runner.

There are three basic Gherkin parameter types - String, int, and DataTable.  There are ways of altering these
basic types directly in the step method declaration that can make these more useful.

For example, notice how the String in "these valid users" is converted, in-line, to a String list using the
built in Cucumber @Delimiter annotation.

DataTables are very powerful but clumsy by themselves.  A cleaner implementation defines a List of a
 user defined object type.  To make such a object type make a simple class that has a camel-cased set of
 attributes that map to the first row of the table in the step.   Our example uses a List<ReportDefinition>.
 Look at the ReportDefinition DTO to see how minimal this can be.  You don't need getters and setting on these objects
 to populate the values.  Cucumber uses reflection to set them even if the value is private.


__Try it out__

You now have all the basic wiring in place.  As you wire in new steps to the fixtures you will find steps
that reveal shortcomings of the product code relative to the specifications.

Update the product code to meet the specification as you go.

 If you can come up with cleaner or better ways of defining steps, by all means try them out, so
  long as the intention of the specification is equivalent.






