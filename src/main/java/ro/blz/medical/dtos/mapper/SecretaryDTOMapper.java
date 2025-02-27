package ro.blz.medical.dtos.mapper;

import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Secretary;
import ro.blz.medical.dtos.SecretaryDTO;

import java.util.function.Function;

@Service
public class SecretaryDTOMapper implements Function<Secretary,SecretaryDTO> {

    @Override
    public SecretaryDTO apply(Secretary secretary) {
        return new SecretaryDTO(
                secretary.getID(),
                secretary.getFirstName(),
                secretary.getLastName(),
                secretary.getEmail(),
                secretary.getPhoneNumber()
        );

    }
}
