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

    public Boolean createProject(String token, ProjectRequest request){
        String jwt = token.substring(7);
        System.out.println(jwtService.extractUsername(jwt));
        var user = userRepository.findByUsername(jwtService.extractUsername(jwt));
        if(!user.isPresent()) {
            System.out.println("No user present");
            return false;
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
        return true;
    }

    public Boolean updateProject(String token, Integer projectId, ProjectRequest request){
        String jwt = token.substring(7);
        var user = userRepository.findByUsername(jwtService.extractUsername(jwt));
        if(!user.isPresent()) {
            System.out.println("No user present");
            return false;
        }

        var project = projectRepository.findById(projectId);
        if(project.isPresent()){
            Project currentProject = project.get();
            currentProject.setShowProject(request.getShowProject());
            currentProject.setContent(request.getContent());
            currentProject.setTitle(request.getTitle());
            currentProject.setDescription(request.getDescription());
            currentProject.setStartDate(request.getStartDate());
            currentProject.setEndDate(request.getEndDate());
            projectRepository.save(currentProject);
            return true;
        }
        return false;
    }

    public List<ProjectProjection> getUserProjects(String username){
        var user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            var projects = projectRepository.findByUser(user.get().getId());
            if(projects.isPresent()) return projects.get();
            return new ArrayList<ProjectProjection>();
        }
        return new ArrayList<ProjectProjection>();
    }

    public Boolean deleteProject(String token, Integer projectId){
        String jwt = token.substring(7);
        var user = userRepository.findByUsername(jwtService.extractUsername(jwt));
        if(!user.isPresent()) {
            System.out.println("No user present");
            return false;
        }
        projectRepository.deleteById(projectId);
        return true;
    }
}
