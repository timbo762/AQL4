package taskmanager;


import org.example.tp3_part2.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIntegrationTest {

    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("taskmanager")
            .withUsername("root")
            .withPassword("root");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateTask() {
        // Given
        Task task = new Task("Test Task", "Test Description");

        // When
        ResponseEntity<Task> response = restTemplate.postForEntity("/api/tasks", task, Task.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Task", response.getBody().getTitle());
        assertEquals("Test Description", response.getBody().getDescription());
    }

    @Test
    public void testGetTaskById() {
        // Given
        Task task = new Task("Test Task", "Test Description");
        ResponseEntity<Task> createResponse = restTemplate.postForEntity("/api/tasks", task, Task.class);
        Long taskId = createResponse.getBody().getId();

        // When
        ResponseEntity<Task> response = restTemplate.getForEntity("/api/tasks/" + taskId, Task.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(taskId, response.getBody().getId());
        assertEquals("Test Task", response.getBody().getTitle());
    }

    @Test
    public void testUpdateTask() {
        // Given
        Task task = new Task("Test Task", "Test Description");
        ResponseEntity<Task> createResponse = restTemplate.postForEntity("/api/tasks", task, Task.class);
        Long taskId = createResponse.getBody().getId();

        Task updatedTask = new Task("Updated Task", "Updated Description");
        updatedTask.setId(taskId);

        // When
        ResponseEntity<Task> response = restTemplate.exchange(
                "/api/tasks/" + taskId,
                HttpMethod.PUT,
                new HttpEntity<>(updatedTask),
                Task.class
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(taskId, response.getBody().getId());
        assertEquals("Updated Task", response.getBody().getTitle());
        assertEquals("Updated Description", response.getBody().getDescription());
    }

    @Test
    public void testDeleteTask() {
        // Given
        Task task = new Task("Test Task", "Test Description");
        ResponseEntity<Task> createResponse = restTemplate.postForEntity("/api/tasks", task, Task.class);
        Long taskId = createResponse.getBody().getId();

        // When
        restTemplate.delete("/api/tasks/" + taskId);

        // Then
        ResponseEntity<Task> response = restTemplate.getForEntity("/api/tasks/" + taskId, Task.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}