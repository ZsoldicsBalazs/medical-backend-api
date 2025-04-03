package ro.blz.medical.dtos;

public record DoctorProcedureDTO(
long id,
long doctor_id,
long procedure_id,
Double price,
String description
) {}
