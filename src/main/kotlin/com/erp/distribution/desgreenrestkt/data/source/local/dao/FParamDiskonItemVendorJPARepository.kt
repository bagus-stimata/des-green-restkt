package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FParamDiskonItemVendorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FParamDiskonItemVendorJPARepository : JpaRepository<FParamDiskonItemVendorEntity, Int> {
//    override fun findById(id: Int): FParamDiskonItemVendorEntity
    fun findByDescription(description: String): List<FParamDiskonItemVendorEntity>

    @Query("SELECT u FROM FParamDiskonItemVendorEntity u WHERE u.description LIKE :description")
    fun findAll(description: String): List<FParamDiskonItemVendorEntity> //    @Query("SELECT u FROM FParamDiskonItemVendorEntity u WHERE u.fdivisionBean = :fdivisionBean" )
    //    List<FParamDiskonItemVendorEntity>  findAllByDivision(int fdivisionBean);
}