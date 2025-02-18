package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Secretary;
import ro.blz.medical.dtos.SecretaryDTO;
import ro.blz.medical.repository.SecretaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecretaryService {
    private final SecretaryRepository secretaryRepository;


    public List<Secretary> getAllSecretary (){
        return secretaryRepository.findAll();
    }
    public Secretary getSecretaryById(Long id){
        return secretaryRepository.findById(id).orElseThrow(()-> new RuntimeException("Secretary not found"));
    }


}
