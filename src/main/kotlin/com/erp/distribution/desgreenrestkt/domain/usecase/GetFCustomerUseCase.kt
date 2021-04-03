package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FCustomer
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FCustomerRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FCustomerRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFCustomerUseCase @Autowired constructor(
    val fCustomerRepo: FCustomerRepo
) {

    fun findById(id: Int): FCustomer {
        return fCustomerRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FCustomerRes {
        return fCustomerRepo.findById(id).toDomain().toResponse()
    }
    fun findAll(): List<FCustomer> {
        return fCustomerRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FCustomerRes> {
        return fCustomerRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findByKode1(kode1: String): List<FCustomer> {
        return  fCustomerRepo.findByCustno(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FCustomerRes> {
        return  fCustomerRepo.findByCustno(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(kode1: String, description: String): List<FCustomer> {
        return  fCustomerRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FCustomerRes> {
        return  fCustomerRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FCustomer> {
        return  fCustomerRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FCustomerRes> {
        return  fCustomerRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FCustomer> {
        return  fCustomerRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FCustomerRes> {
        return  fCustomerRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fCustomer: FCustomer): FCustomer {
        return fCustomerRepo.save(fCustomer.toEntity()).toDomain()
    }
    fun saveAll(listFCustomer: List<FCustomer>): List<FCustomer> {
        return fCustomerRepo.saveAll(listFCustomer.map { it.toEntity() }).map {
            it.toDomain()
        }
    }

    fun delete(fCustomer: FCustomer) {
        return fCustomerRepo.delete(fCustomer.toEntity())
    }
    fun deleteInBatch(listFCustomer: List<FCustomer>) {
        return fCustomerRepo.deleteInBatch(listFCustomer.map { it.toEntity() })
    }


}