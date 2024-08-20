package com.ashish.microservice.movie_catalog_service.controller;

import com.ashish.microservice.movie_catalog_service.model.CatalogItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class MovieCatalogController {

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        // get all rated movie Id's

        // For each movie ID, call movie info service and get details

        // put them all together
            return Arrays.asList(new CatalogItem().builder().name("dhammal").ratings(5).build(),
                    new CatalogItem().builder().name("sammy").ratings(4).build());
    }
}
