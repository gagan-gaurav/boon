package com.services.boon.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/public/search/{query}")
    ResponseEntity<List<List>> search(@PathVariable("query") String query){
        return ResponseEntity.ok(searchService.search(query));
    }
}
