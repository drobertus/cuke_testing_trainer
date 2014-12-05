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
    When "Tom" requests the "Annual Budget" report be run with values
      | parameter Name | value |
      | fiscalYear     | 2010  |
    Then the response should be a unique report id "X"

    Given the server will set the state of report "X" to "Scheduled"
    When "Tom" requests report "X"
    Then the response will indicate it is "Scheduled"

    Given the server will set the state of report "X" to "Running"
    When "Tom" requests report "X"
    Then the response will indicate it is "Running"

    Given the server will set the state of report "X" to "Complete"
    And creates a dummy report
    When "Tom" requests report "X"
    Then the response will indicate it is "Complete"
    And the response will contain the report body

  Scenario: Users can not access reports they have not requested
    Given that report "X" has been requested by user "Tom"
    When "Dick" requests report "X"
    Then the response message will be "You do not have access to this report"


  #Bonus - Write the steps that test this behavior
  Scenario: A user requests a duplicate report and the second report request is denied





