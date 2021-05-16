package com.experian.company.service;

import com.experian.company.model.CompanyEntity;
import com.experian.company.repository.CompanyRepository;
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
