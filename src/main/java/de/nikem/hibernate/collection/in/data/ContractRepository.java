package de.nikem.hibernate.collection.in.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ContractRepository extends CrudRepository<ContractEntity, String> {

    @Query("select distinct c " +
            "from ContractEntity c " +
            "left join fetch c.partners p " +
            "where c.contractNumber in :contractNumbers")
    List<ContractEntity> findContracts(Set<String> contractNumbers);
}
