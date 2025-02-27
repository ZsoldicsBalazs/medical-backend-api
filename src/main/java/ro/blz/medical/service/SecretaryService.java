package ro.blz.medical.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Secretary;
import ro.blz.medical.dtos.SecretaryDTO;
import ro.blz.medical.dtos.mapper.SecretaryDTOMapper;
import ro.blz.medical.exceptions.EntityNotFoundException;
import ro.blz.medical.repository.SecretaryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecretaryService {
    private final SecretaryRepository secretaryRepository;
    private final SecretaryDTOMapper secretaryDTOMapper;


    public List<SecretaryDTO> getAllSecretary (){
        return secretaryRepository.findAll().stream().map(secretaryDTOMapper).collect(Collectors.toList());
    }
    public SecretaryDTO getSecretaryById(Long id){
        var secretary = secretaryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Secretary not found"));
        return secretaryDTOMapper.apply(secretary);
    }
    @Transactional
    public SecretaryDTO updateSecretary(SecretaryDTO secretaryDTO){
        var secretaryToBeUpdated = secretaryRepository.findById(secretaryDTO.id()).orElseThrow(()-> new EntityNotFoundException("Secretary not found"));
        secretaryToBeUpdated.setEmail(secretaryDTO.email());
        secretaryToBeUpdated.setFirstName(secretaryDTO.firstName());
        secretaryToBeUpdated.setLastName(secretaryDTO.lastName());
        secretaryToBeUpdated.setPhoneNumber(secretaryDTO.phoneNumber());
        return secretaryDTOMapper.apply(secretaryToBeUpdated);
    }

}
