package com.example.xml.service;

import com.example.xml.model.dto.CityDto;
import com.example.xml.model.entity.City;
import com.example.xml.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    @Autowired
    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public City save(City city) {
        return repository.save(city);
    }

    @Override
    public City toCity(CityDto dto) {
        return City.builder()
                .country(dto.getCountry())
                .name(dto.getName())
                .build();
    }

    @Override
    public CityDto toCityDto(City dto) {
        return CityDto.builder()
                .name(dto.getName())
                .country(dto.getCountry())
                .build();
    }
}
