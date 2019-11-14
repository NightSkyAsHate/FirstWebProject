package com.nightsky.serverapi.repository;

import com.nightsky.serverapi.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findAll();
}
