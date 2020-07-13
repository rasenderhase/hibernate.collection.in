package de.nikem.hibernate.collection.in.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends CrudRepository<PartnerEntity, String> {
}
