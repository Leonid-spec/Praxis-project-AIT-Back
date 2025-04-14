package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceAlreadyExistsException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceNotFoundException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceValidationException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DoctorAlreadyExistsException;
import de.dental_clinic.g_43_praxis.repository.DentalServiceRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.DentalServiceService;
import de.dental_clinic.g_43_praxis.service.mapping.DentalServiceMappingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DentalServiceImpl implements DentalServiceService {

    private final DentalServiceRepository dentalServiceRepository;
    private final DentalServiceMappingService dentalServiceMappingService;

    @Override
    @Transactional(readOnly = true)
    public List<DentalServiceDto> getActiveDentalServices() {
        List<DentalService> activeDentalServices = dentalServiceRepository.findByIsActiveTrue();
        return activeDentalServices.stream()
                .map(dentalServiceMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DentalServiceDto> getAllDentalServices() {
        return dentalServiceRepository.findAll()
                .stream()
                .map(dentalServiceMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DentalServiceDto getDentalServiceById(Long id) {
        validateId(id);
        DentalService dentalService = dentalServiceRepository.findById(id)
                .orElseThrow(() -> new DentalServiceNotFoundException("DentalService with ID " + id + " not found."));
        return dentalServiceMappingService.mapEntityToDto(dentalService);
    }

    @Override
    public DentalServiceDto addDentalService(@Valid DentalServiceDto dentalServiceDto) {
        validateDentalServiceDto(dentalServiceDto);

        /*
        if (dentalServiceRepository.existsByTitleEnContainingIgnoreCase(dentalServiceDto.getTitleEn())) {
            throw new DentalServiceValidationException("DentalService with name " + dentalServiceDto.getTitleEn() + " already exists.");
        }
         */

        DentalService dentalService = dentalServiceMappingService.mapDtoToEntity(dentalServiceDto);
        dentalService.setId(null);
        DentalService savedDentalService = dentalServiceRepository.save(dentalService);
        return dentalServiceMappingService.mapEntityToDto(savedDentalService);
    }

    @Override
    @Transactional
    public DentalServiceDto updateDentalService(@Valid DentalServiceDto dentalServiceDto) {
        Long id = dentalServiceDto.getId();
        validateId(id);
        validateDentalServiceDto(dentalServiceDto);

        DentalService dentalService = dentalServiceRepository.findById(id)
                .orElseThrow(() -> new DentalServiceNotFoundException("DentalService with ID " + id + " not found."));

        /*
        if (dentalServiceRepository.existsByTitleEnIgnoreCaseAndIdNot(dentalServiceDto.getTitleEn(), id)) {
            throw new DentalServiceValidationException("DentalService with name " + dentalServiceDto.getTitleEn() + " already exists.");
        }
         */

        dentalService.setTitleDe(dentalServiceDto.getTitleDe());
        dentalService.setTitleEn(dentalServiceDto.getTitleEn());
        dentalService.setTitleRu(dentalServiceDto.getTitleRu());
        dentalService.setDescriptionDe(dentalServiceDto.getDescriptionDe());
        dentalService.setDescriptionEn(dentalServiceDto.getDescriptionEn());
        dentalService.setDescriptionRu(dentalServiceDto.getDescriptionRu());
        dentalService.setTopImage(dentalServiceDto.getTopImage());
        dentalService.setIsActive(dentalServiceDto.getIsActive());

        DentalService updatedDentalService = dentalServiceRepository.save(dentalService);

        return dentalServiceMappingService.mapEntityToDto(updatedDentalService);
    }

//    @Override
//    @Transactional
//    public void deleteDentalServiceById(Long id) {
//        validateId(id);
//        if (!dentalServiceRepository.existsById(id)) {
//            throw new DentalServiceNotFoundException("DentalService with ID " + id + " not found");
//        }
//        dentalServiceRepository.deleteById(id);
//    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new DentalServiceValidationException("Invalid ID: ID must be a positive number.");
        }
    }

    private void validateDentalServiceDto(DentalServiceDto dentalServiceDto) {
        if (dentalServiceDto == null) {
            throw new DentalServiceValidationException("Field for dentalServiceDto cannot be null.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getTitleEn())) {
            throw new DentalServiceValidationException("Field titleEn cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getTitleDe())) {
            throw new DentalServiceValidationException("Field titleDe cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getTitleRu())) {
            throw new DentalServiceValidationException("Field titleRu cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getDescriptionEn())) {
            throw new DentalServiceValidationException("Field descriptionEn cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getDescriptionDe())) {
            throw new DentalServiceValidationException("Field descriptionDe cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getDescriptionRu())) {
            throw new DentalServiceValidationException("Field descriptionRu cannot be null or empty.");
        }
    }
}
