package com.epam.epmcacm.songservice.controller;

import com.epam.epmcacm.songservice.repository.model.Song;
import com.epam.epmcacm.songservice.service.api.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/songs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SongController {

    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSongById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(songService.getSongById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveSong(@RequestBody Song song) {
        Map<String, Long> response = new HashMap<>();
        Song newSong = songService.saveSong(song);
        response.put("id",newSong.getId());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateSong(@PathVariable("id") Long id,@RequestBody Song song) {
        Map<String, Long> response = new HashMap<>();
        songService.updateSong(id,song);
        response.put("id", id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteSongsWithIds(@RequestParam("id") List<Long> ids) {
        songService.deleteSongByIds(ids);
        Map<String, List<Long>> response = new HashMap<>();
        response.put("ids", ids);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
