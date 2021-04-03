package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesCallPlandItemsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface FSalesCallPlandItemsJPARepository : JpaRepository<FSalesCallPlandItemsEntity, Long> {

    @Query("SELECT u FROM FSalesCallPlandItemsEntity u WHERE u.fsalesCallPlanhBean.id = :fsalesCallPlanhBean ")
    fun findAllByParent(fsalesCallPlanhBean: Long): List<FSalesCallPlandItemsEntity>

    @Query("SELECT u FROM FSalesCallPlandItemsEntity u WHERE u.fsalesCallPlanhBean IN :listFsalesCallPlanh ")
    fun findAllByListParent(listFsalesCallPlanh: List<Long>): List<FSalesCallPlandItemsEntity>
}