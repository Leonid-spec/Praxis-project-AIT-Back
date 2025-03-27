package de.dental_clinic.g_43_praxis.service.interfaces;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;

import java.util.List;

public interface DentalServiceService {

    List<DentalServiceDto> getActiveDentalServices();

//    List<DentalServiceDto> getUnActiveDentalServices();

    List<DentalServiceDto> getAllDentalServices();

    DentalServiceDto getDentalServiceById(Long id);

    DentalServiceDto addDentalService(DentalServiceDto dentalServiceDto);

    DentalServiceDto updateDentalService(Long id, DentalServiceDto dentalServiceDto);

    void deleteDentalServiceById(Long id);
}
