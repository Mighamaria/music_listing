package com.ust.project.controller;

import com.ust.project.dto.MusicDto;
import com.ust.project.model.Music;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name="ADMIN-SERVICE")
public interface ControllerConsumer {

    @PostMapping("/1.0/admin/addMusic")
    public ResponseEntity<Music> addAMusic(@RequestBody MusicDto dto);

    @PutMapping("/1.0/admin/updateAMusic/{musicId}")
    public ResponseEntity<Music> updateMusic(@RequestBody MusicDto dto,@PathVariable @Valid long musicId );

    @GetMapping("/1.0/admin/viewAllMusics")
    public List<Music> viewAllMusics();

    @GetMapping("/1.0/admin/get/{musicId}")
    public ResponseEntity<Music> getById(@PathVariable long musicId);


}
