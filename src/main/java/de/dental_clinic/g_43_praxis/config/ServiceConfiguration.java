package de.dental_clinic.g_43_praxis.config;


import de.dental_clinic.g_43_praxis.domain.dto.AppointmentDto;
import de.dental_clinic.g_43_praxis.domain.dto.ImageDto;
import de.dental_clinic.g_43_praxis.domain.entity.Appointment;
import de.dental_clinic.g_43_praxis.domain.entity.Image;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceConfiguration {
    //private final OrderRepository orderRepository;
    //private final AddressRepository addressRepository;
/*
    public ServiceConfiguration(OrderRepository orderRepository, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
    }
*/

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // Настройка, чтобы игнорировать несовпадающие поля
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT) // Можно использовать STANDARD или LOOSE
                .setSkipNullEnabled(true); // Игнорировать null-поля


        /***************Appointment*************/
        // Создаем маппинг для Appointment -> AppointmentDto
        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentDto>() {
            @Override
            protected void configure() {
                // маппинг полей с разными именами
                map(source.getService().getId(), destination.getDentalServiceId());
            }
        });

        /***************Doctor*************/
        /*
        // Создаем маппинг для Doctor -> DoctorDto
        modelMapper.addMappings(new PropertyMap<Doctor, DoctorDto>() {
            protected void configure() {
                map(source.getId(), destination.getId());
                using(new Converter<Set<Product>, Set<Long>>() {
                    public Set<Long> convert(MappingContext<Set<Product>, Set<Long>> context) {
                        Set<Long> productIds = new HashSet<>();
                        for (Product product : context.getSource()) {
                            productIds.add(product.getId());
                        }
                        return productIds;
                    }
                }).map(source.getProducts(), destination.getProductid());
            }
        });
        //*/



        /***************Image*************/
        // Создаем маппинг для Image -> ImageDto

        modelMapper.addMappings(new PropertyMap<Image, ImageDto>() {
            @Override
            protected void configure() {
                map(source.getDoctor().getId(), destination.getDoctorId());
                map(source.getDentalService().getId(), destination.getDentalServiceId());
            }
        });





        /***************UserAccount************/
/*
        modelMapper.addMappings(new PropertyMap<UserAccount, UserDto>() {
            protected void configure() {
                using(new Converter<RoleEnum, Long>() {
                    public Long convert(MappingContext<RoleEnum, Long> context) {
                        return Long.valueOf((long) context.getSource().ordinal());
                    }
                }).map(source.getRole(), destination.getRole_Id());
            }
        });

        modelMapper.addMappings(new PropertyMap<UserAccount, UserDto>() {
            @Override
            protected void configure() {
                skip(destination.getPassword());
            }
        });

        modelMapper.addMappings(new PropertyMap<UserDto, UserAccount>() {
            protected void configure() {
                using(new Converter<Long, RoleEnum>() {
                    public RoleEnum convert(MappingContext<Long, RoleEnum> context) {
                        try {
                            return RoleEnum.values()[context.getSource().intValue()];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new FailedDependencyException("error: invalid role specified")  ;
                        }
                    }
                }).map(source.getRole_Id(), destination.getRole());
            }
        });
        //skip(destination.getAddresses());
        //skip(destination.getRole());







        Converter<String, String> trimAndNormalizeSpaces = new Converter<>() {
            @Override
            public String convert(MappingContext<String, String> context) {
                if (context.getSource() == null) {
                    return null;
                }
                return context.getSource().replaceAll("\\s+", " ").trim();
            }
        };

        // Добавляем конвертер глобально
        modelMapper.addConverter(trimAndNormalizeSpaces, String.class, String.class);


 */

        return modelMapper;
    }
}