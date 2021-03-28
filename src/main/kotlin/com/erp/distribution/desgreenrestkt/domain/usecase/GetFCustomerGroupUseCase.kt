package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FCustomerGroup
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FCustomerGroupRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FCustomerGroupRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFCustomerGroupUseCase @Autowired constructor(
    val fCustomerGroupRepo: FCustomerGroupRepo
) {

    fun findById(id: Int): FCustomerGroup {
        return fCustomerGroupRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FCustomerGroupRes {
        return fCustomerGroupRepo.findById(id).toDomain().toResponse()
    }
    fun findAll(): List<FCustomerGroup> {
        return fCustomerGroupRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FCustomerGroupRes> {
        return fCustomerGroupRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findByKode1(kode1: String): List<FCustomerGroup> {
        return  fCustomerGroupRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FCustomerGroupRes> {
        return  fCustomerGroupRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(kode1: String, description: String): List<FCustomerGroup> {
        return  fCustomerGroupRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FCustomerGroupRes> {
        return  fCustomerGroupRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FCustomerGroup> {
        return  fCustomerGroupRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FCustomerGroupRes> {
        return  fCustomerGroupRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerGroup> {
        return  fCustomerGroupRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerGroupRes> {
        return  fCustomerGroupRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fCustomerGroup: FCustomerGroup): FCustomerGroup {
        return fCustomerGroupRepo.save(fCustomerGroup.toEntity()).toDomain()
    }
    fun saveAll(listFCustomerGroup: List<FCustomerGroup>): List<FCustomerGroup> {
        return fCustomerGroupRepo.saveAll(listFCustomerGroup.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fCustomerGroup: FCustomerGroup) {
        return fCustomerGroupRepo.delete(fCustomerGroup.toEntity())
    }
    fun deleteInBatch(listFCustomerGroup: List<FCustomerGroup>) {
        return fCustomerGroupRepo.deleteInBatch(listFCustomerGroup.map { it.toEntity() })
    }


}