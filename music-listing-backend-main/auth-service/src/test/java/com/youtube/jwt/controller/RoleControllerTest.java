//package com.youtube.jwt.controller;
//
//import com.youtube.jwt.entity.Role;
//import com.youtube.jwt.service.RoleService;
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
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(RoleController.class)
//class RoleControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RoleService mockRoleService;
//
//    @Test
//    void testCreateNewRole() throws Exception {
//        // Setup
//        // Configure RoleService.createNewRole(...).
//        final Role role = new Role();
//        role.setRoleName("roleName");
//        role.setRoleDescription("roleDescription");
//        final Role role1 = new Role();
//        role1.setRoleName("roleName");
//        role1.setRoleDescription("roleDescription");
//        when(mockRoleService.createNewRole(role1)).thenReturn(role);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/createNewRole")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        //assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//}
