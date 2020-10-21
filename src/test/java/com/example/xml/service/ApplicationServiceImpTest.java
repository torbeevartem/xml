package com.example.xml.service;

import com.example.xml.XmlApplication;
import com.example.xml.exceptions.MapperException;
import com.example.xml.model.dto.CityDto;
import com.example.xml.model.entity.City;
import com.example.xml.repository.CityRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = XmlApplication.class)
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
class ApplicationServiceImpTest {

    @Autowired
    private ApplicationService service;
    @Autowired
    private CityRepository repository;

    @Test
    void convert_xml_file_to_entity() {
        // given
        MultipartFile mFile = new MockMultipartFile("city.xml", "<city><country>Russia</country><name>Moscow</name></city>".getBytes());

        // when
        String PATH = "src/test/resources/sources/";
        CityDto save = service.save(mFile, PATH + mFile.getName());

        // then
        City byId = repository.findById(1L).orElse(null);
        assert byId != null;
        assertAll(
                () -> assertEquals("Russia", byId.getCountry()),
                () -> assertEquals("Moscow", byId.getName())
        );
    }

    @Test
    void convert_xml_file_to_entity_file_is_null() {
        // given
        MultipartFile mFile = new MockMultipartFile("city.xml", "".getBytes());

        // when
        String PATH = "src/test/resources/sources/";
        NullPointerException exception =
                assertThrows(NullPointerException.class, () ->
                        service.save(mFile, PATH + mFile.getName())
                );

        // then
        assertEquals("mfile is Null", exception.getMessage());
    }

    @Test
    void convert_xml_file_to_entity_file_not_xml() {
        // given
        MultipartFile mFile = new MockMultipartFile("city.xml", "Hello".getBytes());

        // when
        String PATH = "src/test/resources/sources/";
        MapperException exception =
                assertThrows(MapperException.class, () ->
                        service.save(mFile, PATH + mFile.getName())
                );

        // then
        assertEquals("impossible to read file", exception.getMessage());
    }
}