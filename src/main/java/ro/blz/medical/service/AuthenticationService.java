package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ro.blz.medical.auth.AuthenticationResponse;
import ro.blz.medical.auth.UserAuthenticationRequest;
import ro.blz.medical.config.JwtService;
import ro.blz.medical.domain.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ProfileService profileService;



    public AuthenticationResponse authenticate(UserAuthenticationRequest login) {

        Authentication user = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        System.out.println("DR AUth login getemail : " + login.getEmail());
        System.out.println("DR AUth login password : " + login.getPassword());
        System.out.println("before user.getprincipal()");
        User user2 = (User) user.getPrincipal();
        System.out.println("after user.getprincipal()");
        var jwtToken = jwtService.generateToken(user2);
        System.out.println("after jwt token was generated !");

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        authenticationResponse.setProfile(profileService.getProfile(user2.getUser_id(),user2.getRole()));
        System.out.println("After profile service getprofile called ");
        return authenticationResponse;
    }
}
