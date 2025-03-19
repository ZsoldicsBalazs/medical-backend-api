package ro.blz.medical.dtos;

public record SecretaryDTO(long id,String firstName, String lastName,String email,String phoneNumber) implements BaseDTO {
}
