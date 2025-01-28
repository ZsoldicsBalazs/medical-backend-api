package ro.blz.medical.dtos;

import lombok.Builder;
import ro.blz.medical.domain.Role;

@Builder
public record PatientDTO (String username, Role role, String email, String phone, String firstName, String lastName){}
