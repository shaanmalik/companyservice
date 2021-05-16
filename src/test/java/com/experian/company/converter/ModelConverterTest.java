package com.experian.company.converter;

import com.experian.company.model.CompanyDto;
import com.experian.company.model.CompanyEntity;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ModelConverterTest {

    @Test
    public void convertToEntity() {

        OffsetDateTime date = OffsetDateTime.of(2000, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC);
        CompanyDto companyDto = new CompanyDto();
        companyDto.setMsg_id(1);
        companyDto.setRegistration_date(date);
        companyDto.setCompany_name("company name");
        companyDto.setDirectors_count(5);
        companyDto.setLast_updated(date);
        companyDto.setScore(4.2f);

        CompanyEntity companyEntity = ModelConverter.convertToEntity(companyDto);

        Assertions.assertThat(companyEntity.getCompany_name()).isEqualTo("company name");
        Assertions.assertThat(companyEntity.getMsg_id()).isEqualTo(1);
        Assertions.assertThat(companyEntity.getDirectors_count()).isEqualTo(5);
        Assertions.assertThat(companyEntity.getScore()).isEqualTo(4.2f);
        Assertions.assertThat(companyEntity.getRegistration_date()).isEqualTo(date);
        Assertions.assertThat(companyEntity.getLast_updated()).isEqualTo(date);
    }

    @Test
    public void convertToDto() {

        OffsetDateTime date = OffsetDateTime.of(2000, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC);
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setMsg_id(1L);
        companyEntity.setRegistration_date(date);
        companyEntity.setCompany_name("company name");
        companyEntity.setDirectors_count(5);
        companyEntity.setLast_updated(date);
        companyEntity.setScore(4.2f);

        CompanyDto companyDto = ModelConverter.convertToDto(companyEntity);

        Assertions.assertThat(companyDto.getCompany_name()).isEqualTo("company name");
        Assertions.assertThat(companyDto.getMsg_id()).isEqualTo(1);
        Assertions.assertThat(companyDto.getDirectors_count()).isEqualTo(5);
        Assertions.assertThat(companyDto.getScore()).isEqualTo(4.2f);
        Assertions.assertThat(companyDto.getRegistration_date()).isEqualTo(date);
        Assertions.assertThat(companyDto.getLast_updated()).isEqualTo(date);
    }
}