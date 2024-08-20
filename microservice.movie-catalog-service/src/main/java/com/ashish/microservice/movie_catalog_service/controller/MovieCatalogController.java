package com.ashish.microservice.movie_catalog_service.controller;

import com.ashish.microservice.movie_catalog_service.model.CatalogItem;
import com.ashish.microservice.movie_catalog_service.model.Movie;
import com.ashish.microservice.movie_catalog_service.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movieCatalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

       Rating rating=restTemplate.getForObject("http://localhost:9191/ratingsdata/users/1/"+userId, Rating.class);
        System.out.println(rating);
       List<Rating> ratings=new ArrayList<>();
       ratings.add(rating);
        // get all rated movie Id's
        return ratings.stream().map((rat)->{
            Movie movie =restTemplate.getForObject("http://localhost:9193/movieInfo/"+rat.getMovieId(), Movie.class);
            return new CatalogItem().builder().
                    name(movie.getName()).ratings(rat.getRating()).
                    description("test").build();
        }).collect(Collectors.toList());
        // For each movie ID, call movie info service and get details

        // put them all together
//            return Arrays.asList(new CatalogItem().builder().name("dhammal").ratings(5).build(),
//                    new CatalogItem().builder().name("sammy").ratings(4).build());
    }
}
