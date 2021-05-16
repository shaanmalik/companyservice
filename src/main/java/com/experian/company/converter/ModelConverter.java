package com.experian.company.converter;

import com.experian.company.model.CompanyDto;
import com.experian.company.model.CompanyEntity;

public class ModelConverter {

    private ModelConverter() {}

    public static CompanyEntity convertToEntity(CompanyDto companyDto) {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setMsg_id((long) companyDto.getMsg_id());
        companyEntity.setCompany_name(companyDto.getCompany_name());
        companyEntity.setDirectors_count(companyDto.getDirectors_count());
        companyEntity.setRegistration_date(companyDto.getRegistration_date());
        companyEntity.setScore(companyDto.getScore());
        companyEntity.setLast_updated(companyDto.getLast_updated());
        return companyEntity;
    }

    public static CompanyDto convertToDto(CompanyEntity companyEntity) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setMsg_id(companyEntity.getMsg_id().intValue());
        companyDto.setCompany_name(companyEntity.getCompany_name());
        companyDto.setDirectors_count(companyEntity.getDirectors_count());
        companyDto.setRegistration_date(companyEntity.getRegistration_date());
        companyDto.setScore(companyEntity.getScore());
        companyDto.setLast_updated(companyEntity.getLast_updated());
        return companyDto;

    }

}
