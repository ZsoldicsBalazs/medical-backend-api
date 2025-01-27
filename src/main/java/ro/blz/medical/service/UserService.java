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

//          TODO: IF we have multiple repositories
//    private final DoctorRepository doctorRepository;
//    private final PatientRepository patientRepository;
//    private final AdminRepository adminRepository;
//
//    public UserService(DoctorRepository doctorRepository, PatientRepository patientRepository, AdminRepository adminRepository) {
//        this.doctorRepository = doctorRepository;
//        this.patientRepository = patientRepository;
//        this.adminRepository = adminRepository;
//    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doctorRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }// TODO: IF WE HAVE MULTIPLE REPOSITORIES REPLACE WITH THIS RETURN

//        return doctorRepository.findByUsername(username)
//                .or(() -> patientRepository.findByUsername(username))
//                .or(() -> adminRepository.findByUsername(username));
//    }

}
