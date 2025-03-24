package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceNotFoundException;
import de.dental_clinic.g_43_praxis.repository.DentalServiceRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.DentalServiceService;
import de.dental_clinic.g_43_praxis.service.mapping.DentalServiceMappingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DentalServiceImpl implements DentalServiceService {

    private final DentalServiceRepository dentalServiceRepository;
    private final DentalServiceMappingService dentalServiceMappingService;

    @Autowired
    public DentalServiceImpl(DentalServiceRepository dentalServiceRepository, DentalServiceMappingService dentalServiceMappingService) {
        this.dentalServiceRepository = dentalServiceRepository;
        this.dentalServiceMappingService = dentalServiceMappingService;
    }

    @Override
    public List<DentalServiceDto> getActiveDentalServices() {
        List<DentalService> activeDentalServices = dentalServiceRepository.findByIsActiveTrue();
        return activeDentalServices.stream()
                .map(dentalServiceMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DentalServiceDto> getUnActiveDentalServices() {
        List<DentalService> activeDentalServices = dentalServiceRepository.findByIsActiveFalse();
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
        DentalService dentalService = dentalServiceRepository.findById(id)
                .orElseThrow(() -> new DentalServiceNotFoundException("DentalService with ID " + id + " not found"));
        return dentalServiceMappingService.mapEntityToDto(dentalService);
    }

    @Override
    public DentalServiceDto addDentalService(@Valid DentalServiceDto dentalServiceDto) {

//        Используем валидацию в Dto сущности вместо всех проверок ниже
//        if (dentalServiceDto.getTitleEn() == null || dentalServiceDto.getTitleEn().length() < 3) {
//            throw new DentalServiceValidationException("Title in English must be at least 3 characters long");
//        }
//        if (dentalServiceDto.getTitleDe() == null || dentalServiceDto.getTitleDe().length() < 3) {
//            throw new DentalServiceValidationException("Title in German must be at least 3 characters long");
//        }
//        if (dentalServiceDto.getTitleRu() == null || dentalServiceDto.getTitleRu().length() < 3) {
//            throw new DentalServiceValidationException("Title in Russian must be at least 3 characters long");
//        }
//
//        if(dentalServiceRepository.existsByTitleEnContainingIgnoreCase(dentalServiceDto.getTitleEn())) {
//            throw new DentalServiceAlreadyExistsException("DentalService with title " + dentalServiceDto.getTitleEn() + " already exists");
//        }
//
//        if (dentalServiceDto.getDescriptionEn() == null || dentalServiceDto.getDescriptionEn().isEmpty()) {
//            throw new DentalServiceValidationException("Description in English cannot be empty");
//        }
//
//        if (dentalServiceDto.getDescriptionDe() == null || dentalServiceDto.getDescriptionDe().isEmpty()) {
//            throw new DentalServiceValidationException("Description in German cannot be empty");
//        }
//
//        if (dentalServiceDto.getDescriptionRu() == null || dentalServiceDto.getDescriptionRu().isEmpty()) {
//            throw new DentalServiceValidationException("Description in Russian cannot be empty");
//        }
//
//        if (dentalServiceDto.getTopImage() == null || dentalServiceDto.getTopImage().isEmpty()) {
//            throw new DentalServiceValidationException("Top image is required");
//        }

        DentalService dentalService = dentalServiceMappingService.mapDtoToEntity(dentalServiceDto);
        DentalService savedDentalService = dentalServiceRepository.save(dentalService);

        return dentalServiceMappingService.mapEntityToDto(savedDentalService);
    }

    @Override
    public DentalServiceDto updateDentalService(Long id, DentalServiceDto dentalServiceDto) {
        DentalService dentalService = dentalServiceRepository.findById(id)
                .orElseThrow(() -> new DentalServiceNotFoundException("DentalService with ID " + id + " not found"));

        dentalService.setId(id);
        dentalService.setTitleDe(dentalServiceDto.getTitleDe());
        dentalService.setTitleEn(dentalServiceDto.getTitleEn());
        dentalService.setTitleRu(dentalServiceDto.getTitleRu());
        dentalService.setDescriptionDe(dentalServiceDto.getDescriptionDe());
        dentalService.setDescriptionEn(dentalServiceDto.getDescriptionEn());
        dentalService.setDescriptionRu(dentalServiceDto.getDescriptionRu());
        dentalService.setTopImage(dentalServiceDto.getTopImage());
        dentalService.setIsActive(dentalServiceDto.getIsActive());

        DentalService updateDentalService = dentalServiceRepository.save(dentalService);
        return dentalServiceMappingService.mapEntityToDto(updateDentalService);
    }

    @Override
    public void deleteDentalServiceById(Long id) {
        DentalService dentalService = dentalServiceRepository.findById(id)
                .orElseThrow(() -> new DentalServiceNotFoundException("DentalService with ID " + id + " not found"));
        dentalServiceRepository.deleteById(id);
    }
}
