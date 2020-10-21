package com.example.xml.service;

import com.example.xml.mapper.MapperXML;
import com.example.xml.model.dto.CityDto;
import com.example.xml.model.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Service
public class ApplicationServiceImp implements ApplicationService {

    private final MapperXML mapperXML;
    private final CityService service;

    @Autowired
    public ApplicationServiceImp(MapperXML mapperXML, CityService service) {
        this.mapperXML = mapperXML;
        this.service = service;
    }

    @Override
    public CityDto save(MultipartFile mfile, String path) {
        /*
        * сохранения файла на диск
        * */
        File file = mapperXML.multipartToFile(mfile, path);
        /*
        * превращаем в dto
        * */
        CityDto dto = mapperXML.mapperDto(file);
        /*
        * сохраняем
        * */
        City city = service.save(service.toCity(dto));
        /*
        * возвращаем dto
        * */
        return service.toCityDto(city);
    }
}
