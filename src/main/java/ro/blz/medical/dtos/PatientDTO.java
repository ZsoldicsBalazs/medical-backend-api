package ro.blz.medical.dtos;

import lombok.Builder;

@Builder
public record PatientDTO (String CNP,String username, String email, String phone, String firstName, String lastName){}
