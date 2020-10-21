package com.example.xml.service;

import com.example.xml.model.dto.CityDto;
import org.springframework.web.multipart.MultipartFile;

public interface ApplicationService {
    CityDto save(MultipartFile mfile, String path);
}
