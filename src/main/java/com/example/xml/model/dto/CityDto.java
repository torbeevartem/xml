package com.example.xml.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@JacksonXmlRootElement(localName = "city")
public class CityDto {
    @JacksonXmlProperty(localName = "country")
    private String country;
    @JacksonXmlProperty(localName = "name")
    private String name;

    public CityDto(String country, String name) {
        this.country = country;
        this.name = name;
    }

    public CityDto() {
    }
}
