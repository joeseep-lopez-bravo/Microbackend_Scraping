package com.solusoftec.services.twitter;

import com.solusoftec.entities.twitter.XRetweet;
import com.solusoftec.repositories.twitter.XRetweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class XRetweetService {

    private final XRetweetRepository retweetRepository;

    @Autowired
    public XRetweetService(XRetweetRepository retweetRepository) {
        this.retweetRepository = retweetRepository;
    }

    // Obtener los retweets de una publicación
    public List<XRetweet> getRetweetsByPublicacionId(Integer publicacionId) {
        return retweetRepository.findByPublicacionId(publicacionId);
    }

    // Crear un nuevo retweet
    public XRetweet createRetweet(XRetweet retweet) {
        return retweetRepository.save(retweet);
    }
}
