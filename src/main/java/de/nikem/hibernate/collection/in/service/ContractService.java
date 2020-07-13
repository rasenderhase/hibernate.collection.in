package de.nikem.hibernate.collection.in.service;

import de.nikem.hibernate.collection.in.data.ContractEntity;
import de.nikem.hibernate.collection.in.data.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<ContractEntity> findContracts(Set<String> contractNumbers) {
        return contractRepository.findContracts(contractNumbers);
    }
}
