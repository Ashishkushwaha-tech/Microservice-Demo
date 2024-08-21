package com.ashish.microservice.movie_catalog_service.service;

import com.ashish.microservice.movie_catalog_service.model.Rating;
import com.ashish.microservice.movie_catalog_service.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {

    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://MICROSERVICE.RATINGS-SERVICE/ratingsdata/users/" + userId, UserRating.class);
    }
    private UserRating getFallbackUserRating(String userId){
        UserRating userRating=new UserRating();
        userRating.setUserId(userId);
        userRating.setRatingList(Arrays.asList(
                Rating.builder().movieId("157336").rating(5).build(),
                Rating.builder().movieId("15733").rating(4).build()));
        return userRating;
    }
}
