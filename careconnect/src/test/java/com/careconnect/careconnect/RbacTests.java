package com.careconnect.careconnect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class RbacTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminAccess() {
        ResponseEntity<String> response = restTemplate.getForEntity("/admin/some-endpoint", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUserAccess() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/some-endpoint", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUserAccessAdminEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("/admin/some-endpoint", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
