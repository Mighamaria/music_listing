//package com.youtube.jwt.controller;
//
//import com.youtube.jwt.entity.JwtRequest;
//import com.youtube.jwt.entity.JwtResponse;
//import com.youtube.jwt.entity.Role;
//import com.youtube.jwt.entity.User;
//import com.youtube.jwt.service.JwtService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(JwtController.class)
//class JwtControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private JwtService mockJwtService;
//
//    @Test
//    void testCreateJwtToken() throws Exception {
//        // Setup
//        // Configure JwtService.createJwtToken(...).
//        final User user = new User();
//        user.setUserName("userName");
//        user.setUserFirstName("userFirstName");
//        user.setUserLastName("userLastName");
//        user.setUserPassword("userPassword");
//        final Role role = new Role();
//        user.setRole(Set.of(role));
//        final JwtResponse jwtResponse = new JwtResponse(user, "jwtToken");
//        final JwtRequest jwtRequest = new JwtRequest();
//        jwtRequest.setUserName("userName");
//        jwtRequest.setUserPassword("userPassword");
//        when(mockJwtService.createJwtToken(jwtRequest)).thenReturn(jwtResponse);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/authenticate")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        //assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testCreateJwtToken_JwtServiceThrowsException() throws Exception {
//        // Setup
//        // Configure JwtService.createJwtToken(...).
//        final JwtRequest jwtRequest = new JwtRequest();
//        jwtRequest.setUserName("userName");
//        jwtRequest.setUserPassword("userPassword");
//        when(mockJwtService.createJwtToken(jwtRequest)).thenThrow(Exception.class);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/authenticate")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        //assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//}
