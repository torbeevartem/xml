package com.example.xml.controller;

import com.example.xml.model.dto.CityDto;
import com.example.xml.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/api/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public class FileController {

    final ApplicationService service;

    @Autowired
    public FileController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<CityDto> uploadFile(@RequestParam("file") MultipartFile file) {
        String PATH = "src/main/resources/sources/";
        CityDto dto = service.save(file, PATH + file.getOriginalFilename());
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }
}
