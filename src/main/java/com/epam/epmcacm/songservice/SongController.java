package com.epam.epmcacm.songservice;

import com.epam.epmcacm.songservice.exceptions.SongNotFoundException;
import com.epam.epmcacm.songservice.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/songs")
public class SongController {

    @Autowired
    SongRepository songRepository;

    @PostMapping
    public ResponseEntity<?> saveResource(@RequestBody Song song) {
        Map<String, Long> response = new HashMap<>();
        Song newSong = songRepository.save(song);
        response.put("id",newSong.getId());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getResourceById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(songRepository.findById(id),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteResourcesWithIds(@RequestParam("id") List<Long> ids) {
        songRepository.deleteAllById(ids);
        Map<String, List<Long>> response = new HashMap<>();
        response.put("ids", ids);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
