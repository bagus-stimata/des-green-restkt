package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.repository.FMaterialRepoImpl
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FMaterialJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.toDomain
import com.erp.distribution.desgreenrestkt.security_config.SecurityUtils
import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUser
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.data.source.local.dao_security.FUsersJPARepository
import com.erp.distribution.desgreenrestkt.domain.model.toResponse
import com.erp.distribution.desgreenrestkt.domain.repository.FMaterialRepo
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.desgreenrestkt.presentation.model.FMaterialRes
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors
import javax.annotation.PostConstruct

@RestController
class FMaterialRestController @Autowired constructor(
    val getFMaterialUseCase: GetFMaterialUseCase
) {

    @Autowired
    lateinit var fUsersJPARepository: FUsersJPARepository

    @Autowired
    lateinit var fMaterialJPARepository: FMaterialJPARepository

//    @Autowired
//    FDivisionJPARepository fDivisionJPARepository;

    @Autowired
    lateinit var securityUtils: SecurityUtils
    lateinit var activeUser: FUser

    @PostConstruct
    fun init() {
        activeUser = fUsersJPARepository.findById(securityUtils.loginUser!!.id).get()
    }

    @RequestMapping(value = ["/rest/getFMaterialById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialById(@PathVariable("id") id: Int): FMaterialRes {
//        return fMaterialJPARepository.findById(id).get()
        return getFMaterialUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterial"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialEntity: List<FMaterialRes>
        get() = getFMaterialUseCase.findAllRes()

    @RequestMapping(value = ["/rest/getAllFMaterialPage"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllMaterialWithPageable(@RequestParam("page") page: Int, @RequestParam("size") size: Int): List<FMaterialRes> {
        val pageable: Pageable = PageRequest.of(page, size)
        return fMaterialJPARepository.findAll(pageable).content.stream().filter { x: FMaterialEntity -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList()).map {
            it.toDomain().toResponse()
        }
    }

    @RequestMapping(value = ["/rest/getAllFMaterialByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FMaterialRes> {
        activeUser = fUsersJPARepository.findById(securityUtils.loginUser!!.id).get()
        println("aktif user: " + activeUser.username + " >> " + activeUser.fUserVendors)
        return if (activeUser.fUserVendors.size > 0) {
            val listVendorsBean: MutableList<Int> = ArrayList()
            for (fUserVendorBean in activeUser.fUserVendors ) {
                listVendorsBean.add(fUserVendorBean.fvendorBean)
            }
            getFMaterialUseCase.findAllByDivisionAndListVendorRes(fdivisionBean, listVendorsBean).stream().filter { x: FMaterialRes -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        } else {
            getFMaterialUseCase.findAllByDivisionRes(fdivisionBean).stream().filter { x: FMaterialRes -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        }
    }

    @RequestMapping(value = ["/rest/getAllFMaterialByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialRes> {
//        for (FMaterial fMaterialBean: fMaterialJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)) {
//            System.out.println(">> " + fMaterialBean.getPcode() + " >> " + fMaterialBean.getPname() + " >> " + fMaterialBean.isStatusActive());
//        }
        activeUser = fUsersJPARepository.findById(securityUtils.loginUser!!.id).get()

//        System.out.println("aktif user: " + activeUser.getUsername() + " >> " + activeUser.getfUserVendors());
        return if (activeUser.fUserVendors.size > 0) {
            val listVendorsBean: MutableList<Int> = ArrayList()
            for (fUserVendorBean in activeUser.fUserVendors) {
                listVendorsBean.add(fUserVendorBean.fvendorBean)
            }
            getFMaterialUseCase.findAllByDivisionAndShareToCompanyAndListVendorRes(fdivisionBean, fcompanyBean, listVendorsBean).stream().filter { x: FMaterialRes -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        } else {
            getFMaterialUseCase.findAllByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean).stream().filter { x: FMaterialRes -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        }
    }

    @RequestMapping(value = ["/rest/getAllFMaterialByDivisionPage/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int, @RequestParam("page") page: Int, @RequestParam("size") size: Int): List<FMaterialRes> {
        val pageable: Pageable = PageRequest.of(page, size)
//        return fMaterialJPARepository.findAllByDivision(fdivisionBean, pageable).content
        return getFMaterialUseCase.findAllByDivisionRes(fdivisionBean, pageable).content
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterial"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterial(@RequestBody fMaterialEntityNew: FMaterialEntity): FMaterialEntity {
        fMaterialEntityNew.id = 0 //Memastikan ID adalah Nol
        return fMaterialJPARepository.save(fMaterialEntityNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterial/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialInfo(@PathVariable("id") id: Int, @RequestBody fMaterialEntityUpdated: FMaterialEntity?): FMaterialEntity {
        val fMaterial = fMaterialJPARepository.findById(id).orElse(FMaterialEntity())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialEntityUpdated != null) {
            fMaterialEntityUpdated.id = fMaterial.id
            if (fMaterial.fdivisionBean >0) fMaterialEntityUpdated.fdivisionBean = fMaterial.fdivisionBean
            if (fMaterial.fdistributionChannelBean >0) fMaterialEntityUpdated.fdistributionChannelBean = fMaterial.fdistributionChannelBean
            if (fMaterial.fmaterialGroup3Bean >0) fMaterialEntityUpdated.fmaterialGroup3Bean = fMaterial.fmaterialGroup3Bean
            if (fMaterial.fmaterialSalesBrandBean >0) fMaterialEntityUpdated.fmaterialSalesBrandBean = fMaterial.fmaterialSalesBrandBean
            fMaterial.fvendorBean?.let {
                if (fMaterial.fvendorBean == null) fMaterialEntityUpdated.fvendorBean = fMaterial.fvendorBean
            }
            fMaterial.fwarehouseBean_Utm?.let {
                if (fMaterial.fwarehouseBean_Utm!! >0) fMaterialEntityUpdated.fwarehouseBean_Utm = fMaterial.fwarehouseBean_Utm
            }

            fMaterialJPARepository.save(fMaterialEntityUpdated)
            return fMaterialEntityUpdated
        }
        return fMaterial
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterial/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterial(@PathVariable("id") id: Int): FMaterialEntity? {
        val fMaterial = fMaterialJPARepository.findById(id).orElse(FMaterialEntity())
        if (fMaterial != null) {
            fMaterialJPARepository.delete(fMaterial)
        }
        return fMaterial
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialRestController::class.java)
    }
}