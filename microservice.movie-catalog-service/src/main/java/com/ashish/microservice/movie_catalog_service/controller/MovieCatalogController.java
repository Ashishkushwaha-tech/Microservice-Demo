package com.ashish.microservice.movie_catalog_service.controller;

import com.ashish.microservice.movie_catalog_service.model.*;
import com.ashish.microservice.movie_catalog_service.service.CatalogService;
import com.ashish.microservice.movie_catalog_service.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movieCatalog")
public class MovieCatalogController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRatingService userRatingService;
    @Autowired
    private CatalogService catalogService;
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating ratings= userRatingService.getUserRating(userId);
        ;
        return ratings.getRatingList().stream().map(rat -> {
            return catalogService.getCatalogItem(rat);
        }).collect(Collectors.toList());
    }

}
