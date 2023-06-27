package com.ust.project.repository;

import java.util.List;
import java.util.Optional;

import com.ust.project.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.project.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

	Optional<Music> findByMusicId(Long musicId);

	Optional<List<Rating>> findByUserId(Long userId);


	List<Rating> findAllByMusicId(Long musicId);
}
