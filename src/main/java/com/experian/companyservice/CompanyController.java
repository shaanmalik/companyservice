package com.experian.companyservice;

import com.experian.companyservice.model.CompanyDto;
import com.experian.companyservice.model.CompanyEntity;
import com.experian.companyservice.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {

        this.companyService = companyService;
    }

    @GetMapping("/")
    List<CompanyDto> getCompanies() {
        return companyService.getCompanies().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/")
    CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
        companyService.save(convertToEntity(companyDto));
        return companyDto;
    }

    private CompanyEntity convertToEntity(CompanyDto companyDto) {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setMsg_id((long) companyDto.getMsg_id());
        companyEntity.setCompany_name(companyDto.getCompany_name());
        companyEntity.setDirectors_count(companyDto.getDirectors_count());
        companyEntity.setRegistration_date(companyDto.getRegistration_date());
        companyEntity.setScore(companyDto.getScore());
        return companyEntity;
    }

    private CompanyDto convertToDto(CompanyEntity companyEntity) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setMsg_id(companyEntity.getMsg_id().intValue());
        companyDto.setCompany_name(companyEntity.getCompany_name());
        companyDto.setDirectors_count(companyEntity.getDirectors_count());
        companyDto.setRegistration_date(companyEntity.getRegistration_date());
        companyDto.setScore(companyEntity.getScore());
        return companyDto;

    }

}

