# Hibernate many-to-many collection demo project

![Java CI with Gradle](https://github.com/rasenderhase/hibernate.collection.in/workflows/Java%20CI%20with%20Gradle/badge.svg)

In the past I had some trouble with a many-to-many collection mapping
that I was selecting using the `in` statement.

This is a demo project to prove that the thing is working.

However, a similar use case with XML mapping threw `found shared references to a collection` error:

```
<set name="partners" inverse="true" lazy="true" fetch="select" table="CONTRACT_PARTNER">
    <key property-ref="customerNumber">
        <column name="CUSTOMER_NUMBER" not-null="true" />
    </key>
    <many-to-many entity-name="PartnerEntity">
        <column name="PARTNER_NUMBER" not-null="true" />
    </many-to-many>
</set>
```

This is the query:
```
select distinct c 
from ContractEntity c 
left join fetch c.partners p 
where c.contractNumber in :contractNumbers
```
