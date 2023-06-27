package com.ust.admin_service.controller;

import com.ust.admin_service.dto.MusicDto;
import com.ust.admin_service.entity.Music;
import com.ust.admin_service.exception.MusicNotFoundException;
import com.ust.admin_service.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/1.0/admin")
@CrossOrigin("*")
public class MusicController {

    @Autowired
    MusicService musicService;


    @PostMapping("/addMusic")
    public ResponseEntity<Music> addAMusic(@RequestBody MusicDto dto) {
        //return ResponseEntity.ok(musicRepo.save(dto));
        return ResponseEntity.ok(musicService.add(dto));
    }

    @GetMapping("/viewAllMusics")
    public List<Music> viewAllMusics() {
        return musicService.view();
    }

    @GetMapping("/get/{musicId}")
    public ResponseEntity<Music> getById(@PathVariable long musicId) throws MusicNotFoundException {
        return ResponseEntity.ok(musicService.fetchById(musicId));
    }

    @PutMapping("/updateAMusic/{musicId}")
    public ResponseEntity<Music> updateMusic(@PathVariable @Valid long musicId,@RequestBody MusicDto dto ) throws MusicNotFoundException {
        return ResponseEntity.ok(musicService.update(dto,musicId));
    }

    @DeleteMapping("/deleteAMusic/{musicId}")
    public String deleteMusic(@PathVariable long musicId)  throws MusicNotFoundException {

        return	musicService.delete(musicId);

    }
}
