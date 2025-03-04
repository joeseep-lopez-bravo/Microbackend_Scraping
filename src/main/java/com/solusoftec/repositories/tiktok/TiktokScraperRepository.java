package com.solusoftec.repositories.tiktok;
import com.solusoftec.entities.tiktok.TiktokScraper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiktokScraperRepository extends JpaRepository< TiktokScraper,Long>{
}
