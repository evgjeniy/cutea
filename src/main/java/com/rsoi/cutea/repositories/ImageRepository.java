package com.rsoi.cutea.repositories;

import com.rsoi.cutea.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
