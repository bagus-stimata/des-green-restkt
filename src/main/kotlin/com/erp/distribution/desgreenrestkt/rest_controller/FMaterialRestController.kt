package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.jpa_repository.FMaterialJPARepository
import com.erp.distribution.desgreenrestkt.model.FMaterial
import com.erp.distribution.desgreenrestkt.security_config.SecurityUtils
import com.erp.distribution.desgreenrestkt.security_model.FUser
import com.erp.distribution.desgreenrestkt.security_model.Role
import com.erp.distribution.desgreenrestkt.security_repository.FUsersJPARepository
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
class FMaterialRestController {
    @Autowired
    private val fUsersJPARepository: FUsersJPARepository? = null

    @Autowired
    var fMaterialJPARepository: FMaterialJPARepository? = null

    //    @Autowired
    //    FDivisionJPARepository fDivisionJPARepository;
    @Autowired
    private val securityUtils: SecurityUtils? = null
    var activeUser: FUser? = null

    @PostConstruct
    fun init() {
        activeUser = fUsersJPARepository!!.findById(securityUtils!!.loginUser!!.id).get()
    }

    @RequestMapping(value = ["/rest/getFMaterialById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFMaterialById(@PathVariable("id") id: Int): FMaterial {
        return fMaterialJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFMaterial"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allMaterial: List<FMaterial>
        get() = fMaterialJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFMaterialPage"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllMaterialWithPageable(@RequestParam("page") page: Int, @RequestParam("size") size: Int): List<FMaterial> {
        val pageable: Pageable = PageRequest.of(page, size)
        return fMaterialJPARepository!!.findAll(pageable).content.stream().filter { x: FMaterial -> x.isStatusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
    }

    @RequestMapping(value = ["/rest/getAllFMaterialByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FMaterial> {
        activeUser = fUsersJPARepository!!.findById(securityUtils!!.loginUser!!.id).get()
        println("aktif user: " + activeUser!!.username + " >> " + activeUser!!.getfUserVendors())
        return if (activeUser!!.getfUserVendors()!!.size > 0) {
            val listVendorsBean: MutableList<Int> = ArrayList()
            for (fUserVendorBean in activeUser!!.getfUserVendors()!!) {
                listVendorsBean.add(fUserVendorBean.fvendorBean)
            }
            fMaterialJPARepository!!.findAllByDivisionAndListVendor(fdivisionBean, listVendorsBean).stream().filter { x: FMaterial -> x.isStatusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        } else {
            fMaterialJPARepository!!.findAllByDivision(fdivisionBean).stream().filter { x: FMaterial -> x.isStatusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        }
    }

    @RequestMapping(value = ["/rest/getAllFMaterialByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialByDivisionAndShareToCompany(@PathVariable("fdivisionBean") fdivisionBean: Int, @PathVariable("fcompanyBean") fcompanyBean: Int): List<FMaterial> {
//        for (FMaterial fMaterialBean: fMaterialJPARepository.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean)) {
//            System.out.println(">> " + fMaterialBean.getPcode() + " >> " + fMaterialBean.getPname() + " >> " + fMaterialBean.isStatusActive());
//        }
        activeUser = fUsersJPARepository!!.findById(securityUtils!!.loginUser!!.id).get()

//        System.out.println("aktif user: " + activeUser.getUsername() + " >> " + activeUser.getfUserVendors());
        return if (activeUser!!.getfUserVendors()!!.size > 0) {
            val listVendorsBean: MutableList<Int> = ArrayList()
            for (fUserVendorBean in activeUser!!.getfUserVendors()!!) {
                listVendorsBean.add(fUserVendorBean.fvendorBean)
            }
            fMaterialJPARepository!!.findAllByDivisionAndShareToCompanyAndListVendor(fdivisionBean, fcompanyBean, listVendorsBean).stream().filter { x: FMaterial -> x.isStatusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        } else {
            fMaterialJPARepository!!.findAllByDivisionAndShareToCompany(fdivisionBean, fcompanyBean).stream().filter { x: FMaterial -> x.isStatusActive == true && x.pcode != "" && x.pname != "" }.collect(Collectors.toList())
        }
    }

    @RequestMapping(value = ["/rest/getAllFMaterialByDivisionPage/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFMaterialByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int, @RequestParam("page") page: Int, @RequestParam("size") size: Int): List<FMaterial> {
        val pageable: Pageable = PageRequest.of(page, size)
        return fMaterialJPARepository!!.findAllByDivision(fdivisionBean, pageable).content
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/createFMaterial"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFMaterial(@RequestBody fMaterialNew: FMaterial): FMaterial {
        fMaterialNew.id = 0 //Memastikan ID adalah Nol
        return fMaterialJPARepository!!.save(fMaterialNew)
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/updateFMaterial/{id}"], method = [RequestMethod.PUT])
    fun updateFMaterialInfo(@PathVariable("id") id: Int, @RequestBody fMaterialUpdated: FMaterial?): FMaterial {
        val fMaterial = fMaterialJPARepository!!.findById(id).orElse(FMaterial())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fMaterialUpdated != null) {
            fMaterialUpdated.id = fMaterial.id
            if (fMaterial.fdivisionBean >0) fMaterialUpdated.fdivisionBean = fMaterial.fdivisionBean
            if (fMaterial.fdistributionChannelBean >0) fMaterialUpdated.fdistributionChannelBean = fMaterial.fdistributionChannelBean
            if (fMaterial.fmaterialGroup3Bean >0) fMaterialUpdated.fmaterialGroup3Bean = fMaterial.fmaterialGroup3Bean
            if (fMaterial.fmaterialSalesBrandBean >0) fMaterialUpdated.fmaterialSalesBrandBean = fMaterial.fmaterialSalesBrandBean
            if (fMaterial.fvendorBean == null) fMaterialUpdated.fvendorBean = fMaterial.fvendorBean
            if (fMaterial.fwarehouseBean_Utm >0) fMaterialUpdated.fwarehouseBean_Utm = fMaterial.fwarehouseBean_Utm
            fMaterialJPARepository!!.save(fMaterialUpdated)
            return fMaterialUpdated
        }
        return fMaterial
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/rest/deleteFMaterial/{id}"], method = [RequestMethod.DELETE])
    fun deleteFMaterial(@PathVariable("id") id: Int): FMaterial? {
        val fMaterial = fMaterialJPARepository!!.findById(id).orElse(FMaterial())
        if (fMaterial != null) {
            fMaterialJPARepository!!.delete(fMaterial)
        }
        return fMaterial
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FMaterialRestController::class.java)
    }
}