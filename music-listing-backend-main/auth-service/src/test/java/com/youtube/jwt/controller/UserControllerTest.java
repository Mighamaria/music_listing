//package com.youtube.jwt.controller;
//
//import com.youtube.jwt.dto.UserRegistrationRequest;
//import com.youtube.jwt.entity.Role;
//import com.youtube.jwt.entity.User;
//import com.youtube.jwt.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.security.test.context.TestExecutionListeners;
//import org.springframework.security.test.context.TestSecurityContextHolder;
//import org.springframework.security.test.context.support.WithSecurityContext;
//import org.springframework.security.test.context.support.WithSecurityContextFactory;
//import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
//import org.springframework.test.context.TestExecutionListeners.MergeMode;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserController.class)
//@TestExecutionListeners(listeners = WithSecurityContextTestExecutionListener.class, mergeMode = MergeMode.MERGE_WITH_DEFAULTS)
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService mockUserService;
//
//    @Test
//    void testRegisterNewUser() throws Exception {
//        // Setup
//        // Configure UserService.findByUserName(...).
//        final User user = new User();
//        user.setUserName("userName");
//        user.setUserFirstName("userFirstName");
//        user.setUserLastName("userLastName");
//        user.setUserPassword("userPassword");
//        final Role role = new Role();
//        user.setRole(Set.of(role));
//        when(mockUserService.findByUserName("userName")).thenReturn(user);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/registerNewUser")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testRegisterNewUser_UserServiceFindByUserNameReturnsNull() throws Exception {
//        // Setup
//        when(mockUserService.findByUserName("userName")).thenReturn(null);
//
//        // Configure UserService.registerNewUser(...).
//        final User user = new User();
//        user.setUserName("userName");
//        user.setUserFirstName("userFirstName");
//        user.setUserLastName("userLastName");
//        user.setUserPassword("userPassword");
//        final Role role = new Role();
//        user.setRole(Set.of(role));
//        when(mockUserService.registerNewUser(
//                new UserRegistrationRequest("userName", "userFirstName", "userLastName", "userPassword")))
//                .thenReturn(user);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/registerNewUser")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    @WithUserDetails("userName")
//    void testForAdmin() throws Exception {
//        // Setup
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/forAdmin")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    @WithUserDetails("userName")
//    void testForUser() throws Exception {
//        // Setup
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/forUser")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//}
