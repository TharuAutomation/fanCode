# Author: your.email@your.domain.com
# Keywords Summary: Scenario Outline example with dynamic lat, long, and threshold values.
# Feature: Validate Users Todos Completion Based on City Coordinates

@apiTest
Feature: Validate Users Todos Completion Based on City Coordinates

  # Background for common setup
  Background: 
    Given Users are fetched from the "/users" endpoint
    
  # Scenario Outline for validating todos completion based on coordinates and threshold
  @validation
  Scenario Outline: Validate users' todos completion based on a threshold and city coordinates    
    And Users belonging to the city are identified by lat between <lat_min> and <lat_max> and long between <long_min> and <long_max>
    When Todos are fetched from "/todos"
    Then Each user should have completed more than <threshold> percent of their todos

    Examples:
      | lat_min | lat_max | long_min | long_max | threshold |
      | -40     | 5       | 5        | 100      | 50        |