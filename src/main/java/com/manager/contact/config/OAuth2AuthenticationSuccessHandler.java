package com.manager.contact.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.manager.contact.entities.Providers;
import com.manager.contact.entities.User;
import com.manager.contact.entities.repositories.UserRepo;
import com.manager.contact.helper.AppConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.info("Inside AuthenticationSuccessHandler onAuthenticationSuccess");
        
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        user.getAttributes().forEach((key,value)->{
            log.info("{}: {}", key, value);
        });
        
        User user1 = User.builder()
                         .fName(user.getAttribute("given_name"))
                         .lName(user.getAttribute("family_name"))
                         .email(user.getAttribute("email"))
                         .profilePic(user.getAttribute("picture"))
                         .emailVerified(user.getAttribute("email_verified"))
                         .id(UUID.randomUUID().toString())
                         .provider(Providers.GOOGLE)
                         .enabled(true)
                         .providerId(user.getName())
                         .roleList(List.of(AppConstants.ROLE_USER))
                         .build();
        User user2 = userRepo.findByEmail(user.getAttribute("email")).orElse(null);
        if(user2 == null){
            userRepo.save(user1);
            log.info("User Saved Successfully");
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
        
    }
}
