package com.services.boon.profile;

import com.services.boon.config.JwtService;
import com.services.boon.user.User;
import com.services.boon.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ProfileProjection getUserProfile(String username){
        var user = userRepository.findByUsername(username).get();
        var profile = profileRepository.findByUserCustom(user);
        if(profile.isPresent()) return profile.get();
        return null;
    }

    public void setProfile(String token, ProfileRequest request){
        String jwt = token.substring(7);
        System.out.println(jwt);
        User user = userRepository.findByUsername(jwtService.extractUsername(jwt)).get();
        var profile = profileRepository.findByUser(user);
        if(profile.isPresent()){
            Profile previousProfile = profile.get();
            previousProfile.setDescription(request.getDescription())
                    .setLinkedin(request.getLinkedin())
                    .setShowLinkedin(request.getShowLinkedin())
                    .setGithub(request.getGithub())
                    .setShowGithub(request.getShowGithub())
                    .setResume(request.getResume())
                    .setShowResume(request.getShowResume())
                    .setGmail(request.getGmail())
                    .setShowGmail(request.getShowGmail());
            profileRepository.save(previousProfile);
            return;
        }
        var newProfile = Profile.builder()
                .user(user)
                .description(request.getDescription())
                .resume(request.getResume())
                .showResume(request.getShowResume())
                .linkedin(request.getLinkedin())
                .showLinkedin(request.getShowLinkedin())
                .github(request.getGithub())
                .showGithub(request.getShowGithub())
                .gmail(request.getGmail())
                .showGmail(request.getShowGmail())
                .build();

        profileRepository.save(newProfile);
    }
}
