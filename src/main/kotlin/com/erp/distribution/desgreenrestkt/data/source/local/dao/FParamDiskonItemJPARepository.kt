package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FParamDiskonItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FParamDiskonItemJPARepository : JpaRepository<FParamDiskonItemEntity, Int> {
//    override fun findById(id: Int): FParamDiskonItemEntity
    fun findByNoRek(noRek: String): List<FParamDiskonItemEntity>

    @Query("SELECT u FROM FParamDiskonItemEntity u WHERE u.noRek LIKE :noRek and u.description LIKE :description")
    fun findAll(noRek: String, description: String): List<FParamDiskonItemEntity> //    @Query("SELECT u FROM FParamDiskonItemEntity u WHERE u.fdivisionBean = :fdivisionBean" )
    //    List<FParamDiskonItemEntity>  findAllByDivision(int fdivisionBean);
}