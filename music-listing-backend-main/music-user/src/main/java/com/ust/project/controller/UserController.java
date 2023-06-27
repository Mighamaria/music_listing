package com.ust.project.controller;

import java.util.List;

import javax.validation.Valid;

import com.ust.project.dto.MusicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ust.project.dto.RatingDto;
import com.ust.project.exception.InvalidRatingException;
import com.ust.project.exception.MusicNotFoundException;
import com.ust.project.model.Music;
import com.ust.project.service.UserService;

@RestController
@Validated
@RequestMapping("/api/1.0/users")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
    ControllerConsumer controllerConsumer;
	@Autowired
	UserService userService;


//	@PostMapping("/addMusic")
//	public ResponseEntity<Music> addAMusic(@RequestBody MusicDto dto) {
//		//return ResponseEntity.ok(musicRepo.save(dto));
//		return ResponseEntity.ok(userService.add(dto));
//	}
    @GetMapping("/add/{musicId}")
    public ResponseEntity <?> addId(@PathVariable long musicId) {
        return ResponseEntity.ok(controllerConsumer.getById(musicId));
    }

	@PostMapping("/addMusic")
	public ResponseEntity<Music> addMusic(@Valid @RequestBody MusicDto musicDto) {
		Music addedMusic = userService.addMusic(musicDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedMusic);
	}

	@GetMapping("/viewAllMusics")
	public ResponseEntity<List<Music>> getAllMusics()
	{

		return ResponseEntity.ok().body(userService.fetchAllMusics());
	}
	
	
	@GetMapping("search/music/name/{musicName}")
	public ResponseEntity<Music> getMusicByName(@PathVariable String musicName)
	{
		return ResponseEntity.ok().body(userService.fetchMusicByName(musicName));
		
	}

	@GetMapping("search/music/musicGenre/{musicGenre}")
	public ResponseEntity<List<Music>> getMusicByMusicGenre(@PathVariable String musicGenre)
	{
		return ResponseEntity.ok().body(userService.fetchMusicByMusicGenre(musicGenre));
	}


	@GetMapping("search/music/songLanguage/{songLanguage}")
	public ResponseEntity<List<Music>> getMusicBySongLanguage(@PathVariable String songLanguage)
	{
		return ResponseEntity.ok().body(userService.fetchMusicBySongLanguage(songLanguage));
	}

	@GetMapping("search/music/artistName/{artistName}")
	public ResponseEntity<List<Music>> getMusicByArtistName(@PathVariable String artistName)
	{
		return ResponseEntity.ok().body(userService.fetchMusicByArtistName(artistName));
	}
	
	
	@PostMapping("add/rating/music/{musicId}/{userId}")
	public ResponseEntity<String> addRatingMusic(@Valid @RequestBody RatingDto ratingdto, @PathVariable long musicId,@PathVariable Long userId) throws InvalidRatingException, MusicNotFoundException {
		if (ratingdto.getRating() < 1 || ratingdto.getRating() > 10) {
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid rating. Please provide a rating between 1 and 10");
			throw new InvalidRatingException("Invalid rating. Please provide a rating between 1 and 10");
		}
		boolean ratingAdded = userService.addMusicRating(ratingdto, musicId,userId);

		if (ratingAdded) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Rating added successfully.");
		} else {
			throw new MusicNotFoundException("Failed to add rating, Music not found");
		}

	}
	@GetMapping("/rating/overall/{musicId}")
	public ResponseEntity<Double> getMusicOverallRating(@PathVariable long musicId) {
		double overallRating = userService.getMusicOverallRating(musicId);
		return ResponseEntity.ok(overallRating);
	}

}
