package com.services.boon.search;

import com.services.boon.blogs.BlogRepository;
import com.services.boon.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public List<List> search(String query){
        List<List> response = new ArrayList<>();
        var userResults = userRepository.searchUser(query);
        var blogResults = blogRepository.searchBlog(query);
        if(userResults.isPresent()) {
            response.add(userResults.get());
        }else response.add(new ArrayList());
        if(blogResults.isPresent()){
            response.add(blogResults.get());
        }else response.add(new ArrayList());
        return response;
    }
}
