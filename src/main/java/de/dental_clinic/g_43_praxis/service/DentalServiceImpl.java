package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceNotFoundException;
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
    public List<DentalServiceDto> getActiveDentalServices() {
        List<DentalService> activeDentalServices = dentalServiceRepository.findByIsActiveTrue();
        return activeDentalServices.stream()
                .map(dentalServiceMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DentalServiceDto> getAllDentalServices() {
        return dentalServiceRepository.findAll()
                .stream()
                .map(dentalServiceMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DentalServiceDto getDentalServiceById(Long id) {
        validateId(id);
        DentalService dentalService = dentalServiceRepository.findById(id)
                .orElseThrow(() -> new DentalServiceNotFoundException("DentalService with ID " + id + " not found"));
//        System.out.println("Mapping to DTO: " + dentalServiceMappingService.mapEntityToDto(dentalService));
        return dentalServiceMappingService.mapEntityToDto(dentalService);
    }

    @Override
    public DentalServiceDto addDentalService(@Valid DentalServiceDto dentalServiceDto) {
        validateDentalServiceDto(dentalServiceDto);
        if (dentalServiceRepository.existsByTitleEnContainingIgnoreCase(dentalServiceDto.getTitleEn())) {
            throw new DoctorAlreadyExistsException("DentalService with name " + dentalServiceDto.getTitleEn() + " already exists");
        }

        DentalService dentalService = dentalServiceMappingService.mapDtoToEntity(dentalServiceDto);
        DentalService savedDentalService = dentalServiceRepository.save(dentalService);
        return dentalServiceMappingService.mapEntityToDto(savedDentalService);
    }

    @Override
    public DentalServiceDto updateDentalService(Long id, @Valid DentalServiceDto dentalServiceDto) {
        validateId(id);
        validateDentalServiceDto(dentalServiceDto);
        DentalService dentalService = dentalServiceRepository.findById(id)
                .orElseThrow(() -> new DentalServiceNotFoundException("DentalService with ID " + id + " not found"));

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

    @Override
    public void deleteDentalServiceById(Long id) {
        validateId(id);
        if (!dentalServiceRepository.existsById(id)) {
            throw new DentalServiceNotFoundException("DentalService with ID " + id + " not found");
        }
        dentalServiceRepository.deleteById(id);
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be a positive number.");
        }
    }

    private void validateDentalServiceDto(DentalServiceDto dentalServiceDto) {
        if (dentalServiceDto == null) {
            throw new IllegalArgumentException("DentalServiceDto cannot be null.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getTitleEn())) {
            throw new IllegalArgumentException("TitleEn cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getTitleDe())) {
            throw new IllegalArgumentException("TitleDe cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getTitleRu())) {
            throw new IllegalArgumentException("TitleRu cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getDescriptionEn())) {
            throw new IllegalArgumentException("DescriptionEn cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getDescriptionDe())) {
            throw new IllegalArgumentException("DescriptionDe cannot be null or empty.");
        }
        if (!StringUtils.hasText(dentalServiceDto.getDescriptionRu())) {
            throw new IllegalArgumentException("DescriptionRu cannot be null or empty.");
        }
    }
}
