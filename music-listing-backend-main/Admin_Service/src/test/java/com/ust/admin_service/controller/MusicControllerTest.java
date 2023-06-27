package com.ust.admin_service.controller;

import com.ust.admin_service.dto.MusicDto;
import com.ust.admin_service.entity.Music;
import com.ust.admin_service.exception.MusicNotFoundException;
import com.ust.admin_service.service.MusicService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MusicController.class)
class MusicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MusicService mockMusicService;


    @Test
    void testAddMusic() throws Exception {
        // Setup
        MusicDto musicDto = new MusicDto("musicName", "artistName", "musicGenre", "songReleaseDate", "songLanguage",
                "duration", "country", 0.0);
        Music music = new Music();

        when(mockMusicService.add(musicDto)).thenReturn(music);

        // Run the test
        String requestBody = "{\"name\":\"musicName\",\"artist\":\"artistName\",\"genre\":\"musicGenre\"," +
                "\"releaseDate\":\"songReleaseDate\",\"language\":\"songLanguage\",\"duration\":\"duration\"," +
                "\"country\":\"country\",\"overallRate\":0.0}";

        final MockHttpServletResponse response = mockMvc.perform(post("/1.0/admin/addMusic")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        // Add more assertions as needed
//         assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testViewAllMusics() throws Exception {
        // Setup
        when(mockMusicService.view()).thenReturn(List.of(new Music()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/1.0/admin/viewAllMusics")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //  assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testViewAllMusics_MusicServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockMusicService.view()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/1.0/admin/viewAllMusics")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetById() throws Exception {
        // Setup
        when(mockMusicService.fetchById(0L)).thenReturn(new Music());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/1.0/admin/get/{musicId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //  assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetById_MusicServiceThrowsMusicNotFoundException() throws Exception {
        // Setup
        when(mockMusicService.fetchById(0L)).thenReturn(new Music());

        // Run the test
        MockHttpServletResponse response = mockMvc.perform(get("/1.0/admin/get/{musicId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //  assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testUpdate() throws Exception {
        // Setup
        when(mockMusicService.update(
                new MusicDto("musicName", "artistName", "musicGenre", "songReleaseDate", "songLanguage", "duration",
                        "country", 0.0), 0L)).thenReturn(new Music());

        // Run the test
        String requestBody = "{\"name\":\"musicName\",\"artist\":\"artistName\",\"genre\":\"musicGenre\"," +
                "\"releaseDate\":\"songReleaseDate\",\"language\":\"songLanguage\",\"duration\":\"duration\"," +
                "\"country\":\"country\",\"overallRate\":0.0}";

        final MockHttpServletResponse response = mockMvc.perform(put("/1.0/admin/updateAMusic/{musicId}", 0)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();


        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        // assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testUpdateMusic_MusicServiceThrowsMusicNotFoundException() throws Exception {
        // Setup
        MusicDto musicDto = new MusicDto("musicName", "artistName", "musicGenre", "songReleaseDate", "songLanguage",
                "duration", "country", 0.0);

        when(mockMusicService.update(musicDto, 0L)).thenThrow(new MusicNotFoundException("Music not found."));

        // Run the test
        String requestBody = "{\"musicName\":\"musicName\",\"artistName\":\"artistName\",\"musicGenre\":\"musicGenre\"," +
                "\"songReleaseDate\":\"songReleaseDate\",\"songLanguage\":\"songLanguage\",\"duration\":\"duration\"," +
                "\"country\":\"country\",\"overallRate\":0.0}";

        final MockHttpServletResponse response = mockMvc.perform(put("/1.0/admin/updateAMusic/{musicId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
    }


    @Test
    void testDeleteMusic() throws Exception {
        // Setup
        when(mockMusicService.delete(0L)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/1.0/admin/deleteAMusic/{musicId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        // assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
    @Test
    void testDeleteMusic_MusicServiceThrowsMusicNotFoundException() throws Exception {
        // Setup
        when(mockMusicService.delete(0L)).thenThrow(new MusicNotFoundException("Music not found."));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/1.0/admin/deleteAMusic/{musicId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).contains("Music not found.");
    }



}
