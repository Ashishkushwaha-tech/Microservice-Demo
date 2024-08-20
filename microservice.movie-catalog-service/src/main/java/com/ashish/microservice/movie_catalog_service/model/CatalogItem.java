package com.ashish.microservice.movie_catalog_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CatalogItem {

    public String name;
    public String description;
    public int ratings;

}

