Feature: As a user I need to be able to request reports, determine the state of processing, and return them
  when they are ready


  Background:
    Given these valid users "Tom, Dick, Harry"
    And these valid reports
      | name              | param names  |
      | Annual Budget     | fiscalYear   |
      | Quarterly Report  | year, quarter |

  Scenario: User requests a report, gets its status, returns it when it is completed
    Given the default settings
    When "Tom" requests the "Annual Budget" report to be run with values
      | parameter Name | value |
      | fiscalYear     | 2010  |
    Then the response should be a unique report id "X"
    And the status of report "X" should be "Scheduled"

    When "Tom" requests report "X"
    Then the response will indicate it is "Scheduled"

    Given the Report Processor will set the state of report "X" to "Running"
    When "Tom" requests report "X"
    Then the response will indicate it is "Running"

    Given the Report Processor completes report "X"
    When "Tom" requests report "X"
    And the response will contain the report body

  Scenario: Users can not access reports they have not requested
    When "Tom" requests the "Annual Budget" report to be run with values
      | parameter Name | value |
      | fiscalYear     | 2010  |
    Then the response should be a unique report id "Y"
    When "Dick" requests report "Y"
    Then the response message will be "You do not have access to this report"


  #Bonus - Write the steps that test this behavior
  Scenario: A user requests a duplicate report and the second report request is denied



