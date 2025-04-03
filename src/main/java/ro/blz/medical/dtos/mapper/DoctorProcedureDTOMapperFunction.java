package ro.blz.medical.dtos.mapper;

import org.springframework.stereotype.Service;
import ro.blz.medical.domain.DoctorProcedure;
import ro.blz.medical.dtos.DoctorProcedureDTO;

import java.util.function.Function;

@Service
public class DoctorProcedureDTOMapperFunction implements Function<DoctorProcedure, DoctorProcedureDTO> {

    @Override
    public DoctorProcedureDTO apply(DoctorProcedure doctorProcedure) {
      return new DoctorProcedureDTO(doctorProcedure.getID(),doctorProcedure.getDoctor().getID(),doctorProcedure.getProcedure().getID(),doctorProcedure.getPrice(),doctorProcedure.getProcedure().getDescription());
    }
}
