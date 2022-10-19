package com.epam.epmcacm.songservice.controller;

import com.epam.epmcacm.songservice.controller.SongController;
import com.epam.epmcacm.songservice.repository.SongRepository;
import com.epam.epmcacm.songservice.repository.model.Song;
import com.epam.epmcacm.songservice.service.api.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SongController.class)
public class SongControllerTest {

    @MockBean
    SongService songService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    Song SONG = new Song(1l,"All Eyez On Me(ft. Big Syke)", "2Pac", "16 pieces", "4:34", 1l, "1999");

    @Test
    public void getSongByIdTest_success() throws Exception {

        Mockito.when(songService.getSongById(SONG.getId())).thenReturn(SONG);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/songs/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", notNullValue()))
                        .andExpect(jsonPath("$.name", is(SONG.getName())))
                        .andExpect(jsonPath("$.artist", is(SONG.getArtist())))
                        .andExpect(jsonPath("$.album", is(SONG.getAlbum())))
                        .andExpect(jsonPath("$.length", is(SONG.getLength())))
                        .andExpect(jsonPath("$.resourceId", is(1)))
                        .andExpect(jsonPath("$.year", is("1999")))
                        .andDo(MockMvcResultHandlers.print());

    }

//    @Test
//    public void saveSongTest_success() throws Exception {
//
//        Mockito.when(songService.saveSong(SONG)).thenReturn(SONG);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/songs")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(this.mapper.writeValueAsString(SONG)))
//                        .andExpect(status().isOk())
//                        .andExpect(content().json("{'id':1}"))
//                        .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void updateSongTest_success() throws Exception {
        Song updatedSong = new Song(SONG);
        updatedSong.setYear("2000");
        updatedSong.setName("Jezebel");
        updatedSong.setLength("5:00");

        Mockito.when(songService.updateSong(SONG.getId(), updatedSong)).thenReturn(updatedSong);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/songs/1")
                        .content(this.mapper.writeValueAsString(updatedSong))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().json("{'id':1}"))
                        .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteSongTest_success() throws Exception {
        List<Long> ids = Arrays.asList(new Long[]{1l});
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/songs?id=1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", notNullValue()))
                        .andExpect(content().json("{'ids':[1]}"))
                        .andDo(MockMvcResultHandlers.print());

    }
}
