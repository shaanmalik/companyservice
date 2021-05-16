package com.experian.companyservice.service;

import com.experian.companyservice.model.CompanyDto;
import com.experian.companyservice.model.CompanyEntity;
import com.experian.companyservice.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void save(CompanyEntity companyEntity) {
        companyRepository.save(companyEntity);
    }

    public List<CompanyEntity> getCompanies() {
        return companyRepository.findAll();
    }
}
