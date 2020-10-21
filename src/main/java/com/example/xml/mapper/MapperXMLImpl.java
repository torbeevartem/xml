package com.example.xml.mapper;

import com.example.xml.exceptions.MapperException;
import com.example.xml.model.dto.CityDto;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;

@Service
public class MapperXMLImpl implements MapperXML {

    /*
     * превращает файл в dto
     * */
    @Override
    public CityDto mapperDto(File file) {
        CityDto dto;
        XMLStreamReader stream = null;
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            stream = factory.createXMLStreamReader(new FileInputStream(file));
            XmlMapper mapper = new XmlMapper();
            stream.next(); // to point to <root>
            dto = mapper.readValue(stream, CityDto.class);
        } catch (XMLStreamException | IOException err) {
            throw new MapperException("impossible to read file", err);
        } finally {
            try {
                assert stream != null;
                stream.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    /*
     * записываем файл на диск
     * */
    @Override
    public File multipartToFile(MultipartFile mfile, String path) {
        if (mfile.isEmpty()) {
            throw new NullPointerException("mfile is Null");
        }
        File file = new File(path);
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(mfile.getBytes());
        } catch (IOException e) {
            throw new MapperException("impossible to write file to disk ", e);
        }
        return file;
    }
}
