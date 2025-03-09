package com.solusoftec.repositories.twitter;

import com.solusoftec.entities.twitter.XScraper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface XScraperRepository extends JpaRepository<XScraper,Long> {


}
