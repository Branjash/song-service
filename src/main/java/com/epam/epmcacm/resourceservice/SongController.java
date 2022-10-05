package com.epam.epmcacm.resourceservice;

import com.epam.epmcacm.resourceservice.exceptions.ResourceNotFoundException;
import com.epam.epmcacm.resourceservice.exceptions.ResourceS3Exception;
import com.epam.epmcacm.resourceservice.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/songs")
public class SongController {

    @Autowired
    SongRepository songRepository;

    @PostMapping
    public ResponseEntity<?> saveResource(@RequestBody Song file) {
       return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getResourceById(@PathVariable("id") Long id) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteResourcesWithIds(@RequestParam("id") List<Long> ids) {
        return null;
    }
}
