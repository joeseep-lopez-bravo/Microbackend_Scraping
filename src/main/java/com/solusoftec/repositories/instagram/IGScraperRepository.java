package com.solusoftec.repositories.instagram;

import com.solusoftec.entities.instagram.IGScraper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGScraperRepository  extends JpaRepository<IGScraper,Long> {
}
