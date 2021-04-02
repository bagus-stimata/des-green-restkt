package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.security_config.SecurityUtils
import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUser
import com.erp.distribution.desgreenrestkt.data.source.local.dao_security.FUsersJPARepository
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
    lateinit var securityUtils: SecurityUtils
    lateinit var activeUser: FUser

    @PostConstruct
    fun init() {
        activeUser = fUsersJPARepository.findById(securityUtils.loginUser!!.id).get()
    }

    @RequestMapping(value = ["/rest/getFMaterialById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialById(@PathVariable("id") id: Int): FMaterialRes {
//        return getFMaterialUseCase.findById(id).get()
        return getFMaterialUseCase.findByIdRes(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterial"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterialEntity: List<FMaterialRes>
        get() = getFMaterialUseCase.findAllRes()

//    @RequestMapping(value = ["/rest/getAllFMaterialPage"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun getAllMaterialWithPageable(@RequestParam("page") page: Int, @RequestParam("size") size: Int): List<FMaterialRes> {
//        val pageable: Pageable = PageRequest.of(page, size)
//        return getFMaterialUseCase.findAll(pageable).content.stream().filter { x: FMaterialRes -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList()).map {
//            it.toDomain().toResponse()
//        }
//    }

    @RequestMapping(value = ["/rest/getAllFMaterialByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FMaterialRes> {
        activeUser = fUsersJPARepository.findById(securityUtils.loginUser!!.id).get()

        val domain: FUser = fUsersJPARepository.findById(activeUser.id).get()

        println("aktif user: " + activeUser.username + " >> " + activeUser.fUserVendors + " >> " + domain.fUserVendors)
        return if (activeUser.fUserVendors.size > 0) {
            val listVendorsBean: MutableList<Int> = ArrayList()
            for (fUserVendorBean in activeUser.fUserVendors ) {
                listVendorsBean.add(fUserVendorBean.fvendorBean)
            }
            getFMaterialUseCase.findByDivisionAndListVendorRes(fdivisionBean, listVendorsBean).stream().filter { x: FMaterialRes -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        } else {
            getFMaterialUseCase.findByDivisionRes(fdivisionBean).stream().filter { x: FMaterialRes -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        }
    }

    @RequestMapping(value = ["/rest/getAllFMaterialByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterialRes> {
//        for (FMaterial fMaterialBean: getFMaterialUseCase.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)) {
//            System.out.println(">> " + fMaterialBean.getPcode() + " >> " + fMaterialBean.getPname() + " >> " + fMaterialBean.isStatusActive());
//        }
        activeUser = fUsersJPARepository.findById(securityUtils.loginUser!!.id).get()
        val domain: FUser = fUsersJPARepository.findById(activeUser.id).get()

        System.out.println("#result aktif user: " + activeUser.username + " >> " + activeUser.fUserVendors + " >> " + domain.fUserVendors);

        return if (activeUser.fUserVendors.size > 0) {
            val listVendorsBean: MutableList<Int> = ArrayList()
            for (fUserVendorBean in activeUser.fUserVendors) {
                listVendorsBean.add(fUserVendorBean.fvendorBean)
            }
            getFMaterialUseCase.findAllByDivisionAndShareToCompanyAndListVendorRes(fdivisionBean, fcompanyBean, listVendorsBean).stream().filter { x: FMaterialRes -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        } else {
            getFMaterialUseCase.findByDivisionAndShareToCompanyRes(fdivisionBean, fcompanyBean).stream().filter { x: FMaterialRes -> x.statusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        }
//        return listOf()
    }

    @RequestMapping(value = ["/rest/getAllFMaterialByDivisionPage/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int, @RequestParam("page") page: Int, @RequestParam("size") size: Int): List<FMaterialRes> {
        val pageable: Pageable = PageRequest.of(page, size)
        return getFMaterialUseCase.findByDivisionRes(fdivisionBean, pageable).content
    }

//    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
//    @RequestMapping(value = ["/rest/createFMaterial"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    fun createFMaterial(@RequestBody fMaterialEntityNew: FMaterialRes): FMaterialRes {
//        fMaterialEntityNew.id = 0 //Memastikan ID adalah Nol
//        return getFMaterialUseCase.save(fMaterialEntityNew.toDomain())
//    }
//
//    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
//    @RequestMapping(value = ["/rest/updateFMaterial/{id}"], method = [RequestMethod.PUT])
//    fun updateFMaterialInfo(@PathVariable("id") id: Int, @RequestBody fMaterialEntityUpdated: FMaterialRes?): FMaterialRes {
//        val fMaterial = getFMaterialUseCase.findById(id).orElse(FMaterialRes())
//        //Tidak Meng Update Parent: Hanya Info Saja
//        if (fMaterialEntityUpdated != null) {
//            fMaterialEntityUpdated.id = fMaterial.id
//            if (fMaterial.fdivisionBean >0) fMaterialEntityUpdated.fdivisionBean = fMaterial.fdivisionBean
//            if (fMaterial.fdistributionChannelBean >0) fMaterialEntityUpdated.fdistributionChannelBean = fMaterial.fdistributionChannelBean
//            if (fMaterial.fmaterialGroup3EntityBean.id >0) fMaterialEntityUpdated.fmaterialGroup3EntityBean = fMaterial.fmaterialGroup3EntityBean
//            if (fMaterial.fmaterialSalesBrandEntityBean.id >0) fMaterialEntityUpdated.fmaterialSalesBrandEntityBean = fMaterial.fmaterialSalesBrandEntityBean
//            fMaterial.fvendorBean?.let {
//                if (fMaterial.fvendorBean == null) fMaterialEntityUpdated.fvendorBean = fMaterial.fvendorBean
//            }
//            fMaterial.fwarehouseBean_Utm?.let {
//                if (fMaterial.fwarehouseBean_Utm!! >0) fMaterialEntityUpdated.fwarehouseBean_Utm = fMaterial.fwarehouseBean_Utm
//            }
//
//            getFMaterialUseCase.save(fMaterialEntityUpdated)
//            return fMaterialEntityUpdated
//        }
//        return fMaterial
//    }

//    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
//    @RequestMapping(value = ["/rest/deleteFMaterial/{id}"], method = [RequestMethod.DELETE])
//    fun deleteFMaterial(@PathVariable("id") id: Int): FMaterialRes? {
//        val fMaterial = getFMaterialUseCase.findById(id).orElse(FMaterialRes())
//        if (fMaterial != null) {
//            getFMaterialUseCase.delete(fMaterial)
//        }
//        return fMaterial
//    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialRestController::class.java)
    }
}