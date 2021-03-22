package com.erp.distribution.desgreenrestkt.data.source.local.dao

import com.erp.distribution.desgreenrestkt.data.source.entity.FCustomerPic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FCustomerPicJPARepository : JpaRepository<FCustomerPic, Int> {
//    override fun findById(id: Int): FCustomerPic?
    fun findByTitle(title: String?): List<FCustomerPic>

    @Query("SELECT u FROM FCustomerPic u WHERE u.title LIKE :title")
    fun findAll(title: String?): List<FCustomerPic>

    @Query("SELECT u FROM FCustomerPic u WHERE u.fcustomerBean = :fcustomerBean")
    fun findAllByParentId(fcustomerBean: Int): List<FCustomerPic>
}