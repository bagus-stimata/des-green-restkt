package com.erp.distribution.desgreenrestkt.domain.usecase

import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.domain.model.FDistributionChannel
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FDistributionChannelRepo
import com.erp.distribution.desgreenrestkt.presentation.model.FDistributionChannelRes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFDistributionChannelUseCase @Autowired constructor(
    val fDistributionChannelRepo: FDistributionChannelRepo
) {

    fun findById(id: Int): FDistributionChannel {
        return fDistributionChannelRepo.findById(id).toDomain()
    }
    fun findByIdRes(id: Int): FDistributionChannelRes {
        return fDistributionChannelRepo.findById(id).toDomain().toResponse()
    }
    fun findByKode1(kode1: String): List<FDistributionChannel> {
        return  fDistributionChannelRepo.findByKode1(kode1).map {
            it.toDomain()
        }
    }
    fun findByKode1Res(kode1: String): List<FDistributionChannelRes> {
        return  fDistributionChannelRepo.findByKode1(kode1).map {
            it.toDomain().toResponse()
        }
    }

    fun findAll(): List<FDistributionChannel> {
        return fDistributionChannelRepo.findAll().map {
            it.toDomain()
        }
    }
    fun findAllRes(): List<FDistributionChannelRes> {
        return fDistributionChannelRepo.findAll().map {
            it.toDomain().toResponse()
        }
    }
    fun findAll(kode1: String, description: String): List<FDistributionChannel> {
        return  fDistributionChannelRepo.findAll(kode1, description).map {
            it.toDomain()
        }
    }
    fun findAllRes(kode1: String, description: String): List<FDistributionChannelRes> {
        return  fDistributionChannelRepo.findAll(kode1, description).map {
            it.toDomain().toResponse()
        }
    }

    fun findByDivision(fdivisionBean: Int): List<FDistributionChannel> {
        return  fDistributionChannelRepo.findByDivision(fdivisionBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionRes(fdivisionBean: Int): List<FDistributionChannelRes> {
        return  fDistributionChannelRepo.findByDivision(fdivisionBean).map {
            it.toDomain().toResponse()
        }
    }
    fun findByDivisionAndShareToCompany(fdivisionBean: Int, fcompanyBean: Int): List<FDistributionChannel> {
        return  fDistributionChannelRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain()
        }
    }
    fun findByDivisionAndShareToCompanyRes(fdivisionBean: Int, fcompanyBean: Int): List<FDistributionChannelRes> {
        return  fDistributionChannelRepo.findByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).map {
            it.toDomain().toResponse()
        }
    }

    fun save(fDistributionChannel: FDistributionChannel): FDistributionChannel {
        return fDistributionChannelRepo.save(fDistributionChannel.toEntity()).toDomain()
    }
    fun saveAll(listFDistributionChannel: List<FDistributionChannel>): List<FDistributionChannel> {
        return fDistributionChannelRepo.saveAll(listFDistributionChannel.map { it.toEntity() })!!.map {
            it.toDomain()
        }
    }

    fun delete(fDistributionChannel: FDistributionChannel) {
        return fDistributionChannelRepo.delete(fDistributionChannel.toEntity())
    }
    fun deleteInBatch(listFDistributionChannel: List<FDistributionChannel>) {
        return fDistributionChannelRepo.deleteInBatch(listFDistributionChannel.map { it.toEntity() })
    }


}