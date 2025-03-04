package com.solusoftec.repositories.twitter;

import com.solusoftec.entities.twitter.XRetweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional("twitterTransactionManager")
@Repository
public interface XRetweetRepository extends JpaRepository<XRetweet, Integer> {

    // Método para obtener todos los retweets de una publicación por su ID
    List<XRetweet> findByPublicacionId(Integer publicacionId);
}
