package ro.blz.medical.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.blz.medical.auth.AuthenticationResponse;
import ro.blz.medical.auth.UserAuthenticationRequest;
import ro.blz.medical.auth.UserRegistrationRequest;
import ro.blz.medical.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody UserRegistrationRequest registration){
        return ResponseEntity.ok(authenticationService.registerPatient(registration));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login (@RequestBody UserAuthenticationRequest login){
        System.out.println("LOGIN CONTROLLER EMAIL : "+login.getEmail());
        AuthenticationResponse response;
        try {
            response = authenticationService.authenticate(login);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(response);
    }

}
