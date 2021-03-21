package com.erp.distribution.desgreenrestkt.jpa_repository

import com.erp.distribution.desgreenrestkt.data.source.entity.FParamDiskonItemVendor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FParamDiskonItemVendorJPARepository : JpaRepository<FParamDiskonItemVendor, Int> {
//    override fun findById(id: Int): FParamDiskonItemVendor
    fun findByDescription(description: String): List<FParamDiskonItemVendor>

    @Query("SELECT u FROM FParamDiskonItemVendor u WHERE u.description LIKE :description")
    fun findAll(description: String): List<FParamDiskonItemVendor> //    @Query("SELECT u FROM FParamDiskonItemVendor u WHERE u.fdivisionBean = :fdivisionBean" )
    //    List<FParamDiskonItemVendor>  findAllByDivision(int fdivisionBean);
}