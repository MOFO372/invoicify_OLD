package com.libertymutual.goforcode.invoicify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.invoicify.models.FlatFeeBillingRecord;

@Repository
public interface FlatFeeBillingRecordRepository extends JpaRepository<FlatFeeBillingRecord, Long>{

}
