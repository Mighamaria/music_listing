package com.ust.project.controller;

import com.ust.project.dto.RatingDto;
import com.ust.project.model.Music;
import com.ust.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    void testGetAllMusics() throws Exception {
        // Setup
        // Configure UserService.fetchAllMusics(...).
        final List<Music> music = List.of(
                new Music(0L, "musicName", "artistName", "musicGenre", "songReleaseDate", "songLanguage", "duration",
                        "country", 0.0));
        when(mockUserService.fetchAllMusics()).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/1.0/users/viewAllMusics")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //   assertThat(response.getContentAsString()).isEqualTo("[{\"id\":0,\"name\":\"musicName\",\"artist\":\"artistName\",\"genre\":\"musicGenre\",\"releaseDate\":\"songReleaseDate\",\"language\":\"songLanguage\",\"duration\":\"duration\",\"country\":\"country\",\"rating\":0.0}]");
    }


    @Test
    void testGetAllMusics_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.fetchAllMusics()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/1.0/users/viewAllMusics")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetMusicByName() throws Exception {
        // Setup
        // Configure UserService.fetchMusicByName(...).
        final Music music = new Music(0L, "musicName", "artistName", "musicGenre", "songReleaseDate", "songLanguage",
                "duration", "country", 0.0);
        when(mockUserService.fetchMusicByName("musicName")).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/music/name/{musicName}", "musicName")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        // assertThat(response.getContentAsString()).isEqualTo("{\"id\":0,\"name\":\"musicName\",\"artist\":\"artistName\",\"genre\":\"musicGenre\",\"releaseDate\":\"songReleaseDate\",\"language\":\"songLanguage\",\"duration\":\"duration\",\"country\":\"country\",\"rating\":0.0}");
    }


    @Test
    void testGetMusicByMusicGenre() throws Exception {
        // Setup
        // Configure UserService.fetchMusicByMusicGenre(...).
        final List<Music> music = List.of(
                new Music(0L, "musicName", "artistName", "musicGenre", "songReleaseDate", "songLanguage", "duration",
                        "country", 0.0));
        when(mockUserService.fetchMusicByMusicGenre("musicGenre")).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/music/musicGenre/{musicGenre}", "musicGenre")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();




        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo("[{\"id\":0,\"name\":\"musicName\",\"artist\":\"artistName\",\"genre\":\"musicGenre\",\"releaseDate\":\"songReleaseDate\",\"language\":\"songLanguage\",\"duration\":\"duration\",\"country\":\"country\",\"rating\":0.0}]");
    }


    @Test
    void testGetMusicByMusicGenre_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.fetchMusicByMusicGenre("musicGenre")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/music/musicGenre/{musicGenre}", "musicGenre")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetMusicBySongLanguage() throws Exception {
        // Setup
        // Configure UserService.fetchMusicBySongLanguage(...).
        final List<Music> music = List.of(
                new Music(0L, "musicName", "artistName", "musicGenre", "songReleaseDate", "songLanguage", "duration",
                        "country", 0.0));
        when(mockUserService.fetchMusicBySongLanguage("songLanguage")).thenReturn(music);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/music/songLanguage/{songLanguage}", "songLanguage")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo("[{\"id\":0,\"name\":\"musicName\",\"artist\":\"artistName\",\"genre\":\"musicGenre\",\"releaseDate\":\"songReleaseDate\",\"language\":\"songLanguage\",\"duration\":\"duration\",\"country\":\"country\",\"rating\":0
    }
    @Test
    void testGetMusicBySongLanguage_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.fetchMusicBySongLanguage("songLanguage")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/1.0/users/search/music/songLanguage/{songLanguage}", "songLanguage")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
    @Test
    void testAddRatingMusic() throws Exception {
        // Setup
        when(mockUserService.addMusicRating(new RatingDto(0.0), 0L, 0L)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(

                        post("/api/1.0/users/add/rating/music/{musicId}/{userId}", 0, 0)
                                .content("{\"rating\":0.00}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().
                getResponse();
//
//        final MockHttpServletResponse response = mockMvc.perform(post("/api/1.0/users/add/rating/music/{musicId}/{userId}")
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn()
//                .getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


}
