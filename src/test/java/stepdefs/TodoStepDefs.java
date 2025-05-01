package stepdefs;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utils.ApiUtils;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static stepdefs.Hooks.test;

public class TodoStepDefs {

    List<Map<String, Object>> allUsers;
    private List<Map<String, Object>> fancodeUsers;
    private List<Map<String, Object>> todos;
    
    private double latMin, latMax, lngMin, lngMax;
    
    List<Integer> fanCodeUserIds = new ArrayList<>();
    Map<Integer, List<Map<String, Object>>> todosByUser = new HashMap<>();
    
    @Given("Users are fetched from the {string} endpoint")
    public void users_are_fetched_from_endpoint(String endpoint) {
    	test.info("Fetching users from endpoint: " + endpoint);
        Response response = ApiUtils.getendPoint(endpoint);
        allUsers = response.jsonPath().getList("$");
        assertNotNull(allUsers);
        test.pass("Users fetched successfully: " + allUsers.size());
    }
    
    @And("Users belonging to the city are identified by lat between {double} and {double} and long between {double} and {double}")
    public void users_filtered_by_location(double lat1, double lat2, double lng1, double lng2) {
        latMin = Math.min(lat1, lat2);
        latMax = Math.max(lat1, lat2);
        lngMin = Math.min(lng1, lng2);
        lngMax = Math.max(lng1, lng2);
        test.info(String.format("Filtering users by lat between %.2f and %.2f, long between %.2f and %.2f", lat1, lat2, lng1, lng2));
        fancodeUsers = new ArrayList<>();
        for (Map<String, Object> user : allUsers) {
            Map<String, Object> address = (Map<String, Object>) user.get("address");
            Map<String, String> geo = (Map<String, String>) address.get("geo");
            double lat = Double.parseDouble(geo.get("lat"));
            double lng = Double.parseDouble(geo.get("lng"));
            if (lat >= latMin && lat <= latMax && lng >= lngMin && lng <= lngMax) {
                fancodeUsers.add(user);
            }
        }
        assertFalse("No users found in given lat-long range", fancodeUsers.isEmpty());
    }

    @When("Todos are fetched from {string}")
    public void fetch_todos_from_endpoint(String endpoint) {
    	test.info("Fetching todos from endpoint: " + endpoint);
        Response response = ApiUtils.getendPoint(endpoint);
        todos = response.jsonPath().getList("$");
        assertNotNull(todos);
        test.pass("Todos fetched successfully: " + todos.size());
    }

    @Then("Each user should have completed more than {int} percent of their todos")
    public void check_completed_percentage(int threshold) {
        for (Map<String, Object> user : fancodeUsers) {
            int userId = (int) user.get("id");
            
            List<Map<String, Object>> userTodos = todos.stream()
                    .filter(todo -> ((int) todo.get("userId")) == userId)
                    .toList();

            long completed = userTodos.stream()
                    .filter(todo -> Boolean.TRUE.equals(todo.get("completed")))
                    .count();

            double percentComplete = userTodos.isEmpty() ? 0 : (completed * 100.0 / userTodos.size());
            test.info(String.format("User %d has %.2f%% completed todos", userId, percentComplete));

            System.out.printf("User %d has %.2f%% completed todos%n", userId, percentComplete);
            assertTrue("User " + userId + " has less than " + threshold + "% completed", percentComplete > threshold);
        }
        test.pass("All users have completed more than " + threshold + "% of their todos");
    }
}
