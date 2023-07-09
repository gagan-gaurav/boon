package com.services.boon.project;

import com.services.boon.config.JwtService;
import com.services.boon.user.User;
import com.services.boon.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public void createProject(String token, ProjectRequest request){
        String jwt = token.substring(7);
        System.out.println(jwtService.extractUsername(jwt));
        var user = userRepository.findByUsername(jwtService.extractUsername(jwt));
        if(!user.isPresent()) {
            System.out.println("No user present");
            return;
        }
        var project = Project.builder()
                .user(user.get())
                .showProject(request.getShowProject())
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .content(request.getContent())
                .build();
        projectRepository.save(project);
    }

    public List<ProjectProjection> getUserProjects(String username){
        User user = userRepository.findByUsername(username).get();
        Optional<List<ProjectProjection>> projects = projectRepository.findByUser(user);
        if(projects.isPresent()) return projects.get();
        return new ArrayList<ProjectProjection>();
    }
}
