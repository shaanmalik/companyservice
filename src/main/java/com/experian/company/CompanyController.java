package com.experian.company;

import com.experian.company.converter.ModelConverter;
import com.experian.company.model.CompanyDto;
import com.experian.company.model.CompanyEntity;
import com.experian.company.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.experian.company.converter.ModelConverter.convertToEntity;

@RestController
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    List<CompanyDto> getCompanies() {
        return companyService.getCompanies().stream()
                .map(ModelConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
        companyService.save(convertToEntity(companyDto));
        return companyDto;
    }



}

