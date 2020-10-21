package com.example.xml.service;

import com.example.xml.model.dto.CityDto;
import com.example.xml.model.entity.City;

public interface CityService {
    City save(City city);
    City toCity(CityDto dto);
    CityDto toCityDto(City dto);
}
