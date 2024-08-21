package com.ashish.microservice.movie_catalog_service.service;

import com.ashish.microservice.movie_catalog_service.model.CatalogItem;
import com.ashish.microservice.movie_catalog_service.model.Movie;
import com.ashish.microservice.movie_catalog_service.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogService {

    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rat) {
        Movie movie = restTemplate.getForObject("http://MICROSERVICE.MOVIE/movieInfo/" + rat.getMovieId(), Movie.class);
        return new CatalogItem().builder()
                .name(movie.getName())
                .ratings(rat.getRating())
                .description(movie.getDescription())
                .build();
    }
    private CatalogItem getFallbackCatalogItem(Rating rat){
        return CatalogItem.builder().name("movie name not found")
                .description("description not available")
                .ratings(0).build();
    }

}
