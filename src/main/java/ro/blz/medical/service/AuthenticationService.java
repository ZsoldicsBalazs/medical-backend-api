package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.blz.medical.auth.AuthenticationResponse;
import ro.blz.medical.auth.UserAuthenticationRequest;
import ro.blz.medical.config.JwtService;
import ro.blz.medical.domain.User;
import ro.blz.medical.dtos.mapper.DoctorDTOMapperFunc;
import ro.blz.medical.dtos.mapper.SecretaryDTOMapper;
import ro.blz.medical.repository.DoctorRepository;
import ro.blz.medical.repository.PatientRepository;
import ro.blz.medical.repository.SecretaryRepository;
import ro.blz.medical.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(UserAuthenticationRequest login) {
//        User user = userRepository.findByEmail(login.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        System.out.println("user: " + user);
        var user = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        System.out.println("DR AUth login getemail : " + login.getEmail());
        System.out.println("DR AUth login password : " + login.getPassword());
        var jwtToken = jwtService.generateToken((User) user.getPrincipal());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }
}
