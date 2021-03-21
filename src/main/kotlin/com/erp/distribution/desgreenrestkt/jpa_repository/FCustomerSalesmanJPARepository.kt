package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerSalesman
import org.springframework.data.jpa.repository.JpaRepository

interface FCustomerSalesmanJPARepository : JpaRepository<FCustomerSalesman, Int> {
//    override fun findById(id: Int): FCustomerSalesman? // List<FCustomerSalesman> findByKode1(String kode1);
    // @Query("SELECT u FROM FArea u WHERE u.kode1 LIKE ?1 and u.description LIKE ?2")
    // List<FCustomerSalesman>  findAll(String kode1, String description);
}