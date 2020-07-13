package de.nikem.hibernate.collection.in.data;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ContractEntity {

    @Id
    private String contractNumber;
    @Column
    private String customerNumber;

    @ManyToMany
    @JoinTable(
            name = "CONTRACT_PARTNER",
            joinColumns = @JoinColumn(name = "customerNumber"),
            inverseJoinColumns = @JoinColumn(name = "partnerNumber")
    )
    private Set<PartnerEntity> partners;

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Set<PartnerEntity> getPartners() {
        return partners;
    }

    public void setPartners(Set<PartnerEntity> partners) {
        this.partners = partners;
    }
}
