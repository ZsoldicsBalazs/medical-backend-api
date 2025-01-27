package ro.blz.medical.dtos;

import jakarta.persistence.Column;
import ro.blz.medical.domain.Role;

public record DoctorDTO(

         String firstName,
         String lastName,
         String phone,
         String email,
         String department,
         Role role
) {
}
