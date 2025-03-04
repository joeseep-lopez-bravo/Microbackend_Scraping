package com.solusoftec.controllers.twitter;

import com.solusoftec.entities.twitter.XRetweet;
import com.solusoftec.services.twitter.XRetweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("X/publicaciones/{id}/retweets")
public class XRetweetController {

    private final XRetweetService retweetService;

    @Autowired
    public XRetweetController(XRetweetService retweetService) {
        this.retweetService = retweetService;
    }

    // Endpoint para obtener todos los retweets de una publicación
    @GetMapping
    public List<XRetweet> getRetweetsByPublicacionId(@PathVariable Integer id) {
        return retweetService.getRetweetsByPublicacionId(id);
    }

    // Endpoint para crear un retweet
    @PostMapping
    public XRetweet createRetweet(@PathVariable Integer id, @RequestBody XRetweet retweet) {
        retweet.setPublicacionId(id);  // Aseguramos que el retweet tenga la ID de la publicación
        return retweetService.createRetweet(retweet);
    }
}

