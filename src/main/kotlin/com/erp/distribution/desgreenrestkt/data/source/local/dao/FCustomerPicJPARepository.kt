package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerPicEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FCustomerPicJPARepository : JpaRepository<FCustomerPicEntity, Int> {
//    override fun findById(id: Int): FCustomerPicEntity?
    fun findByTitle(title: String?): List<FCustomerPicEntity>

    @Query("SELECT u FROM FCustomerPicEntity u WHERE u.title LIKE :title")
    fun findAll(title: String?): List<FCustomerPicEntity>

    @Query("SELECT u FROM FCustomerPicEntity u WHERE u.fcustomerBean = :fcustomerBean")
    fun findAllByParentId(fcustomerBean: Int): List<FCustomerPicEntity>
}