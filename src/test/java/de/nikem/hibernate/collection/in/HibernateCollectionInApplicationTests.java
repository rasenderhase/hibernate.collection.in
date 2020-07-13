package de.nikem.hibernate.collection.in;

import de.nikem.hibernate.collection.in.data.ContractEntity;
import de.nikem.hibernate.collection.in.data.ContractRepository;
import de.nikem.hibernate.collection.in.data.PartnerEntity;
import de.nikem.hibernate.collection.in.data.PartnerRepository;
import de.nikem.hibernate.collection.in.service.ContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class HibernateCollectionInApplicationTests {

	@Autowired
	private ContractService contractService;
	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private PartnerRepository partnerRepository;

	@BeforeEach
	void setUp() {
		var p1 = new PartnerEntity();
		p1.setFirstName("Andy");
		p1.setLastName("Knees");
		p1.setPartnerNumber("4711");
		partnerRepository.save(p1);
		var p2 = new PartnerEntity();
		p2.setFirstName("Tom");
		p2.setLastName("Knees");
		p2.setPartnerNumber("4712");
		partnerRepository.save(p2);

		Set<PartnerEntity> partners = new LinkedHashSet<>();
		partners.add(p1);
		partners.add(p2);

		var c1 = new ContractEntity();
		c1.setContractNumber("1");
		c1.setPartners(partners);
		contractRepository.save(c1);

		var c2 = new ContractEntity();
		c2.setContractNumber("2");
		c2.setPartners(partners);
		contractRepository.save(c2);
	}

	@Test
	void testFindContracts() {
		Set<String> contractNumbers = Set.of("1", "2");
		final var contracts = contractService.findContracts(contractNumbers);
		assertThat(contracts, hasSize(2));
		final var optionalContract = contracts.stream()
				.filter(c -> c.getContractNumber().equals("1"))
				.findAny();

		assertThat(optionalContract, hasProperty("present", is(true)));
		final var contract = optionalContract.orElseThrow();
		assertThat(contract, hasProperty("partners", hasSize(2)));

		final var optionalPartner = contract.getPartners().stream()
				.filter(p -> p.getPartnerNumber().equals("4711"))
				.findAny();

		assertThat(optionalPartner, hasProperty("present", is(true)));
		final var partner = optionalPartner.orElseThrow();
		assertThat(partner, hasProperty("firstName", is("Andy")));

	}

}
