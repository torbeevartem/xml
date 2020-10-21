package com.example.xml.mapper;

import com.example.xml.model.dto.CityDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface MapperXML {
    CityDto mapperDto(File file);
    File multipartToFile(MultipartFile mfile, String path);
}
