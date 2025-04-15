package de.dental_clinic.g_43_praxis.service.interfaces;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DentalServiceService {

    List<DentalServiceDto> getActiveDentalServices();

    List<DentalServiceDto> getAllDentalServices();

    DentalServiceDto getDentalServiceById(Long id);

    DentalServiceDto addDentalService(DentalServiceDto dentalServiceDto);

    DentalServiceDto updateDentalService(DentalServiceDto dentalServiceDto);

    DentalServiceDto deleteDentalServiceById(Long id);

//    void deleteDentalServiceById(Long id);
}
