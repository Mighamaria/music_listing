package com.ust.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ust.project.dto.MusicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import java.util.concurrent.atomic.AtomicDouble;
import java.util.concurrent.atomic.AtomicReference;

import com.ust.project.dto.RatingDto;
import com.ust.project.model.Music;
import com.ust.project.model.Rating;
import com.ust.project.repository.MusicRepository;
import com.ust.project.repository.RatingRepository;

@Service
public class UserService {
	@Autowired
	MusicRepository musicRepository;
	@Autowired
	RatingRepository ratingRepository;

	public List<Music> fetchAllMusics() {
		return musicRepository.findAll();
	}

	public Music fetchMusicByName(String musicName) {

		return musicRepository.findByMusicName(musicName);
	}

	public List<Music> fetchMusicByMusicGenre(String musicGenre) {

		return musicRepository.findAllByMusicGenre((musicGenre));
	}

	public List<Music> fetchMusicBySongLanguage(String songLanguage) {

		return musicRepository.findAllBySongLanguage((songLanguage));
	}


	public List<Music> fetchMusicByArtistName(String artistName) {

		return musicRepository.findAllByArtistName(artistName);
	}

	public boolean addMusicRating(RatingDto ratingDto, Long musicId, Long userId) {
		Optional<Music> musicOptional = musicRepository.findById(musicId);

		if (musicOptional.isPresent()) {
			Optional<List<Rating>> ratingsOptional = ratingRepository.findByUserId(userId);
			List<Rating> ratingList = ratingsOptional.orElse(new ArrayList<>());

			boolean ratingFound = false;
			for (Rating rating : ratingList) {
				if (rating.getMusicId() == musicId) {
					rating.setRating(ratingDto.getRating());
					ratingRepository.save(rating);
					ratingFound = true;
					break;
				}
			}

			if (!ratingFound) {
				Rating newRating = new Rating();
				newRating.setMusicId(musicId);
				newRating.setUserId(userId);
				newRating.setRating(ratingDto.getRating());
				ratingRepository.save(newRating);
			}

			List<Rating> allRatings = ratingRepository.findAllByMusicId(musicId);
			double sumOfRatings = 0.0;
			int numberOfRatings = allRatings.size();
			for (Rating rating : allRatings) {
				sumOfRatings += rating.getRating();
			}

			double overallRate = sumOfRatings / numberOfRatings;

			Music music = musicOptional.get();
			music.setOverallRate(overallRate);
			musicRepository.save(music);
			return true;
		} else {
			return false;
		}
	}
	public double getMusicOverallRating(Long musicId) {
		Optional<Music> musicOptional = musicRepository.findById(musicId);
		if (musicOptional.isPresent()) {
			Music music = musicOptional.get();
			return music.getOverallRate();
		}
		else {
			return 0.0;
		}
	}


		public Music addMusic(MusicDto musicDto) {
			// Convert MusicDto to Music entity
			Music music = new Music();
			music.setMusicName(musicDto.getMusicName());
			music.setArtistName(musicDto.getArtistName());
			music.setMusicGenre(musicDto.getMusicGenre());
            music.setSongReleaseDate(musicDto.getSongReleaseDate());
			music.setSongLanguage(musicDto.getSongLanguage());
			music.setDuration(musicDto.getDuration());
			music.setCountry(musicDto.getCountry());
			music.setOverallRate(musicDto.getOverallRate());
			// ... Set other properties

			// Save the music entity
			return musicRepository.save(music);

    }
}


