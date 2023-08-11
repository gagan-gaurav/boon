package com.services.boon.profile;

import com.services.boon.config.JwtService;
import com.services.boon.user.User;
import com.services.boon.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ProfileProjection getUserProfile(String username){
        var user = userRepository.findByUsername(username);
        if(user.isPresent()){
            var profile = profileRepository.findByUsername(user.get().getUsername());
            return profile.get();
        }
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

    public void setProfileImage(String token, byte[] imageData){
        String jwt = token.substring(7);
        var username = jwtService.extractUsername(jwt);

        if(imageData == null) {
            var profile = profileRepository.findByUser(userRepository.findByUsername(username).get()).get();
            profile.setProfileUrl(null);
            profile.setProfilePic(false);
            profileRepository.save(profile);
            return;
        }
        String dim = "156";
        String targetUrl = "https://t1ufofmm90.execute-api.ap-south-1.amazonaws.com/default/imageUploader?filename=profileImage/" + username + "&h=" + dim + "&w=" + dim;

        WebClient webClient = WebClient.builder().baseUrl(targetUrl).build();

        Mono<ImageUploaderServiceResponse> responseMono = webClient.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(BodyInserters.fromValue(imageData))
                .retrieve()
                .bodyToMono(ImageUploaderServiceResponse.class);

        responseMono.subscribe(
                result -> {
                    // Handle the response here if needed
                    System.out.println(result.getMessage());
//                    System.out.println(result.getUrl());
                    var profile = profileRepository.findByUser(userRepository.findByUsername(username).get()).get();
                    profile.setProfileUrl(result.getUrl());
                    profile.setProfilePic(true);
                    profileRepository.save(profile);
                },
                error -> {
                    System.err.println("An error occurred: " + error.getMessage());
                }
        );

    }
}
