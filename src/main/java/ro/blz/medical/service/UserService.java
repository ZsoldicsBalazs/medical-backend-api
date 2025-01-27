package ro.blz.medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.BaseEntity;
import ro.blz.medical.repository.DoctorRepository;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {


    @Autowired
    private DoctorRepository doctorRepository;

    public Optional<? extends BaseEntity<?>> findByUsername(String username) {
        return doctorRepository.findByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doctorRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
