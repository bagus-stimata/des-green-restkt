package com.erp.distribution.desgreenrestkt

import com.erp.distribution.desgreenrestkt.data.source.entity.*
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumSalesType
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumTipeWarehouse
import com.erp.distribution.desgreenrestkt.security_config.PassEncoding.Companion.instance
import com.erp.distribution.desgreenrestkt.security_config.SecurityUtils
import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserRolesEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserVendorsEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role
import com.erp.distribution.desgreenrestkt.data.source.entity_security.Role.allRoles
import com.erp.distribution.desgreenrestkt.data.source.local.dao_security.FUserRolesJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao_security.FUserVendorsJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao_security.FUsersJPARepository
import com.erp.distribution.desgreenrestkt.domain.model.*
import com.erp.distribution.desgreenrestkt.domain.model.toEntity
import com.erp.distribution.desgreenrestkt.domain.usecase.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.util.*
import java.util.stream.Collectors
import javax.annotation.PostConstruct

/**
 * The TodoController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
@ComponentScan
class UsersController @Autowired constructor(
    val getFCompanyUseCase: GetFCompanyUseCase,
    val getFDivisionUseCase: GetFDivisionUseCase,
    val getFSalesmanUseCase: GetFSalesmanUseCase,
    val getFVendorUseCase: GetFVendorUseCase,
    val getFWarehouseUseCase: GetFWarehouseUseCase,
    val fUsersJPARepository: FUsersJPARepository,
    val fUserRolesJPARepository: FUserRolesJPARepository,
    val fUserVendorsJPARepository: FUserVendorsJPARepository,
    val securityUtils: SecurityUtils
) {
//    @Autowired
//    lateinit var fUsersJPARepository: FUsersJPARepository
//
//    @Autowired
//    lateinit var fUserRolesJPARepository: FUserRolesJPARepository
//
//    @Autowired
//    lateinit var fUserVendorsJPARepository: FUserVendorsJPARepository
//
//    @Autowired
//    lateinit var securityUtils: SecurityUtils

    var listFDivision: List<FDivision> = ArrayList()
    var listFWarehouse: List<FWarehouse> = ArrayList()
    var listFSalesman: List<FSalesman> = ArrayList()
    var listFVendor: List<FVendor> = ArrayList()

    @PostConstruct
    fun init() {
        activeUserEntity = fUsersJPARepository.findById(securityUtils.loginUserEntity!!.id).get()
        if (activeUserEntity != null) activeDivision = getFDivisionUseCase.findById(activeUserEntity?.fdivisionBean!!)
        if (activeDivision != null) {
            activeCompany = getFCompanyUseCase.findById(activeDivision.fcompanyBean)
        }
        if (activeCompany != null) {
            listFDivision = getFDivisionUseCase.findByCompany(activeCompany!!.id).stream().filter { x: FDivision -> x.statusActive == true && x.kode1 != "" && x.description != "" }.collect(Collectors.toList())
            listFWarehouse = getFWarehouseUseCase.findByCompany(activeCompany!!.id).stream().filter { x: FWarehouse -> x.statusActive == true && x.kode1 != "" && x.description != "" && x.tipeWarehouse == EnumTipeWarehouse.GS }.collect(Collectors.toList())
            listFSalesman = getFSalesmanUseCase.findByCompany(activeCompany!!.id)
                    .stream().filter { x: FSalesman -> x.statusActive == true && x.spcode != "" && x.spname != "" && (x.salesType == EnumSalesType.TO || x.salesType == EnumSalesType.C || x.salesType == EnumSalesType.C) }.collect(Collectors.toList())
            listFVendor = getFVendorUseCase.findByCompany(activeCompany!!.id).stream().filter { x: FVendor -> x.statusActive == true && x.vcode != "" && x.vname != "" }.collect(Collectors.toList())
        } else {
            listFDivision = getFDivisionUseCase.findAll()
            listFWarehouse = getFWarehouseUseCase.findAll()
            listFSalesman = getFSalesmanUseCase.findAll()
            listFVendor = getFVendorUseCase.findAll()
        }
    }

    @get:ModelAttribute("allStringRoles")
    val all_RolesMenu: List<String?>
        get() {
            val list: MutableList<String?> = ArrayList()
            for (myRoleID in allRoles) {
                list.add(myRoleID)
            }
            return list
        }

    //Karena berhubungan dengan FUser yang mengambil data dari Entity
    @ModelAttribute("listFVendor")
    fun listFVendor(): List<FVendorEntity> {
        return listFVendor.map {
            it.toEntity()
        }
    }

    @ModelAttribute("listFWarehouse")
    fun listFWarehouse(): List<FWarehouse> {
        return listFWarehouse
    }

    @ModelAttribute("listFSalesman")
    fun listFSalesman(): List<FSalesman> {
        return listFSalesman
    }

    @ModelAttribute("listFDivision")
    fun listFDivision(): List<FDivision> {
        return listFDivision
    }

    lateinit var activeUserEntity: FUserEntity
    lateinit var activeCompany: FCompany
    lateinit var activeDivision: FDivision
    var listDivisionInCompany: List<FDivision> = ArrayList()

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/admin/users")
    fun listIndex(viewModel: Model): String {


        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        val mapDivision: MutableMap<Int, String> = HashMap()
        for (divisionBean in listFDivision()) {
            mapDivision[divisionBean.id] = divisionBean.kode1 + " - " + divisionBean.description
        }
        viewModel.addAttribute("mapDivision", mapDivision)
        val mapSalesman: MutableMap<Int, String> = HashMap()
        for (salesmanBean in listFSalesman()) {
            try {
                mapSalesman[salesmanBean.id] = salesmanBean.spcode + " - " + salesmanBean.spname
            } catch (ex: Exception) {
            }
        }
        viewModel.addAttribute("mapSalesman", mapSalesman)
        val mapWarehouse: MutableMap<Int, String> = HashMap()
        for (warehouseBean in listFWarehouse()) {
            mapWarehouse[warehouseBean.id] = warehouseBean.kode1 + " - " + warehouseBean.description
        }
        viewModel.addAttribute("mapWarehouse", mapWarehouse)
        val newDomain = FUserEntity()
        viewModel.addAttribute("newDomain", newDomain)
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", fUsersJPARepository.findAll())
        logger.info("# Form Task")
        return "users/user_list"
    }

    // -->   @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/admin/users_form/save_process"], method = [RequestMethod.POST])
    fun saveProcess(@ModelAttribute("domain") domain: FUserEntity, redirectAttributes: RedirectAttributes): String {
        if (domain.tempInt1 == 0) {
            val newDomain = FUserEntity()
            newDomain.tempInt1 = domain.tempInt1 //0.New Form, 1.Edit Form, 3.Delete

            // domainUpdate.setUsername("bagus");
            newDomain.username = domain.username
            // domainUpdate.setEmail("bagus.stimata@gmail.com emailnya");
            newDomain.email = domain.email
            newDomain.locked = domain.locked
            newDomain.fdivisionBean = domain.fdivisionBean
            newDomain.fsalesmanBean = domain.fsalesmanBean
            newDomain.fwarehouseBean = domain.fwarehouseBean
            newDomain.fullName = domain.fullName
            newDomain.phone = domain.phone
            newDomain.notes = domain.notes
            if (domain.password == "") {
                domain.password = "Welcome1"
                domain.passwordConfirm = "Welcome1"
            }
            //            newDomain.setPassword(PassEncoding.getInstance().passwordEncoder.encode(domain.getPassword().trim() ));
            if (domain.passwordConfirm.trim { it <= ' ' } != "") {
                if (domain.password.trim { it <= ' ' } == domain.passwordConfirm.trim { it <= ' ' }) {
                    newDomain.notes = "#!*(%$" + domain.passwordConfirm.trim { it <= ' ' } + "12345xzy"
                    newDomain.password = instance!!.passwordEncoder.encode(domain.passwordConfirm.trim { it <= ' ' })
                } else {
                }
            } else {
            }


            //Sementara otomatis
            val listFUserLRoleEntities: MutableList<FUserRolesEntity> = ArrayList()
            val userRole1 = FUserRolesEntity()
            userRole1.fuserBean = newDomain
            userRole1.roleID = Role.USER
            val userRole2 = FUserRolesEntity()
            userRole2.fuserBean = newDomain
            userRole2.roleID = Role.ADMIN
            listFUserLRoleEntities.add(userRole1)
            listFUserLRoleEntities.add(userRole2)
            newDomain.fuserRoles = listFUserLRoleEntities

            //New Tidak memerlukan
            // if (newDomain.getFuserRoles().size()>0)
            // userRolesRepository.deleteAll(newDomain.getFuserRoles());

//            newDomain.fuserRoles = null
            newDomain.fuserRoles = mutableListOf()
            val listUserRoleEntities: MutableList<FUserRolesEntity> = ArrayList()
            for (str in domain.tempRoles) {
                for (roleID in allRoles) {
                    if (roleID == str) {
                        val newUserRole = FUserRolesEntity()
                        newUserRole.roleID = roleID
                        newUserRole.fuserBean = newDomain
                        listUserRoleEntities.add(newUserRole)
                        break
                    }
                }
            }
            newDomain.passwordConfirm = ""
            if (fUsersJPARepository.save<FUserEntity>(newDomain).id >0 ) {
//                if (fUserRolesJPARepository.save<FUserRoles?>(userRole1) != null &&
//                        fUserRolesJPARepository.save<FUserRoles?>(userRole2) != null) {
//                    newDomain.tempInt1 = 1
//                    fUserRolesJPARepository.saveAll(listUserRoles)
//                    redirectAttributes.addFlashAttribute("saveUser", "success")
//                }
                fUserRolesJPARepository.save<FUserRolesEntity?>(userRole1)
                fUserRolesJPARepository.save<FUserRolesEntity?>(userRole2)
                newDomain.tempInt1 = 1
                fUserRolesJPARepository.saveAll(listUserRoleEntities)
                redirectAttributes.addFlashAttribute("saveUser", "success")

            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail")
            }
            return "redirect:/admin/users/edit_form/" + newDomain.id
        } else if (domain.tempInt1 == 1) {
            val domainUpdate: FUserEntity = fUsersJPARepository.findById(domain.id).get()
            // if (domainUpdate !=null) {
            domainUpdate.tempInt1 = domain.tempInt1 //0.New Form, 1.Edit Form, 3.Delete
            domainUpdate.tempInt1 = 1

            // domainUpdate.setUsername("bagus");
            domainUpdate.username = domain.username
            // domainUpdate.setEmail("bagus.stimata@gmail.com emailnya");
            domainUpdate.email = domain.email
            domainUpdate.notes = domain.notes
            domainUpdate.locked = domain.locked
            domainUpdate.fdivisionBean = domain.fdivisionBean
            domainUpdate.fsalesmanBean = domain.fsalesmanBean
            domainUpdate.fwarehouseBean = domain.fwarehouseBean


//            System.out.println("Hello password  " + domain.getPasswordConfirm());
            if (domain.passwordConfirm.trim { it <= ' ' } != "") {
                if (domain.password.trim { it <= ' ' } == domain.passwordConfirm.trim { it <= ' ' }) {
//                        System.out.println("Hello password 1 " + domain.getPassword());
                    domainUpdate.notes = "#!*(%$" + domain.passwordConfirm.trim { it <= ' ' } + "12345xzy"
                    domainUpdate.password = instance!!.passwordEncoder.encode(domain.passwordConfirm.trim { it <= ' ' })
                } else {
//                        System.out.println("Hello password 2 " + domain.getPassword());
                }
            } else {
//                    System.out.println("Hello password 3 " + domain.getPassword());
            }
            domainUpdate.fullName = domain.fullName
            domainUpdate.phone = domain.phone
            if (domainUpdate.fuserRoles!!.size > 0) fUserRolesJPARepository.deleteAll(domainUpdate.fuserRoles!!)
//            domainUpdate.fuserRoles = null
            domainUpdate.fuserRoles =  mutableListOf()
            val listUserRoleEntities: MutableList<FUserRolesEntity> = ArrayList()
            for (str in domain.tempRoles) {
                for (roleID in allRoles) {
                    if (roleID == str) {
                        val newUserRole = FUserRolesEntity()
                        newUserRole.roleID = roleID
                        newUserRole.fuserBean = domainUpdate
                        listUserRoleEntities.add(newUserRole)
                        break
                    }
                }
            }
            domainUpdate.passwordConfirm = ""
            fUsersJPARepository.save(domainUpdate)
            fUserRolesJPARepository.saveAll(listUserRoleEntities)
            redirectAttributes.addFlashAttribute("saveUser", "success")
            return "redirect:/admin/users/edit_form/" + domain.id
            // return "redirect:/" ;
            // }
        } else if (domain.tempInt1 == 5) {
            val domainUpdate: FUserEntity = fUsersJPARepository.findById(domain.id).get()
            domainUpdate.tempInt1 = domain.tempInt1 //0.New Form, 1.Edit Form, 3.Delete, 5.EditVendor
            domainUpdate.tempInt1 = 5
            if (domainUpdate.fUserVendorEntities!!.size > 0) {
                fUserVendorsJPARepository.deleteAll(domainUpdate.fUserVendorEntities!!)
//                println("Cek Uer Vendor Before: " + domainUpdate.fUserVendors)
            }
            domainUpdate.fUserVendorEntities = listOf()
            /**
             * Gini aja ternyata bisa
             */
            val listUserVendorEntities: MutableList<FUserVendorsEntity?> = ArrayList()
            for (tempBean in domain.tempVendors) {
                val newUserVendor = FUserVendorsEntity()
                newUserVendor.fvendorBean = tempBean
                newUserVendor.fuserBean = domainUpdate
                listUserVendorEntities.add(newUserVendor)
                if (tempBean > 0) {
                    fUserVendorsJPARepository.save(newUserVendor)
                    //                    System.out.println("AA: " + newUserVendor.getFuserBean().getId() + " >> " + newUserVendor.getFvendorBean());
                }
            }


//            System.out.println("Temp Vendor bos: " + domain.getTempVendors());
            /**
             * Dan tidak perlu ini
             */
//            domainUpdate.setfUserVendors(listUserVendors);
//            fUsersJPARepository.save(domainUpdate);
//            fUserVendorsJPARepository.saveAll(listUserVendors);
            redirectAttributes.addFlashAttribute("saveUser", "success")
            return "redirect:/admin/users/edit_form_vendor/" + domain.id
        }
        return "redirect:/admin/users"
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = ["/admin/users/{operation}/{id}"], method = [RequestMethod.GET])
    fun toFormOperation(@PathVariable("operation") operation: String,
                        @PathVariable("id") id: Int, redirectAttributes: RedirectAttributes,
                        model: Model): String {
        logger.info("/users/operation: {} ", operation)
        /**
         * Karena ketika cuma di init maka tidak bisa refresh otomatis
         * Ditaruh di sini supaya refresh terus
         */
        if (operation != "delete") {
            if (activeCompany != null) {
                listFDivision = getFDivisionUseCase.findByCompany(activeCompany!!.id).stream().filter { x: FDivision -> x.statusActive == true && x.kode1 != "" && x.description != "" }.collect(Collectors.toList())
                listFWarehouse = getFWarehouseUseCase.findByCompany(activeCompany!!.id).stream().filter { x: FWarehouse -> x.statusActive == true && x.kode1 != "" && x.description != "" && x.tipeWarehouse == EnumTipeWarehouse.GS }.collect(Collectors.toList())
                listFSalesman = getFSalesmanUseCase.findByCompany(activeCompany!!.id)
                        .stream().filter { x: FSalesman -> x.statusActive == true && x.spcode != "" && x.spname != "" && (x.salesType == EnumSalesType.TO || x.salesType == EnumSalesType.C || x.salesType == EnumSalesType.C) }.collect(Collectors.toList())
                listFVendor = getFVendorUseCase.findByCompany(activeCompany!!.id).stream().filter { x: FVendor -> x.statusActive == true && x.vcode != "" && x.vname != "" }.collect(Collectors.toList())
            } else {
                listFDivision = getFDivisionUseCase.findAll()
                listFWarehouse = getFWarehouseUseCase.findAll()
                listFSalesman = getFSalesmanUseCase.findAll()
                listFVendor = getFVendorUseCase.findAll()
            }
        }
        if (operation == "delete") {
            try {
                val domainToDelete: FUserEntity = fUsersJPARepository.findById(id).get()
                fUserRolesJPARepository.deleteAll(domainToDelete.fuserRoles!!)
                fUsersJPARepository.delete(domainToDelete)
                redirectAttributes.addFlashAttribute("msg", "del")
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently")
            } catch (e: Exception) {
                redirectAttributes.addFlashAttribute("msg", "del_fail")
                redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later")
                e.printStackTrace()
            }
        } else if (operation == "new_form") {
            val newDomain = FUserEntity()
            newDomain.tempInt1 = 0 //0.New Form, 1.Edit Form, 3.Delete
            model.addAttribute("domain", newDomain)
            return "users/user_form"
        } else if (operation == "edit_form") {
            val domain: FUserEntity = fUsersJPARepository.findById(id).get()
            if (domain.id >0) {
                domain.tempInt1 = 1 //0.New Form, 1.Edit Form, 3.Delete
                val tempRoles: MutableList<String> = ArrayList()
                for (opdUserRole in domain.fuserRoles!!) {
                    opdUserRole.roleID?.let { tempRoles.add(it) }
                }

                // FUserRoles role1 = new FUserRoles();
                // role1.setFuserBean(domain); role1.setRoleID(Role.ADMIN);
                // tempRoles.add(Role.ADMIN);
                domain.tempRoles = tempRoles


//                List<FWarehouse> listFWarehouse = fWarehouseJPARepository.findAll();
//                model.addAttribute("listFWarehouse", listFWarehouse);
                model.addAttribute("domain", domain)
                return "users/user_form"
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound")
            }
        } else if (operation == "edit_form_vendor") {
            val domain: FUserEntity = fUsersJPARepository.findById(id).get()
//            println("aktif user: " + domain.id + " >> " + domain.username + " >> " + domain.fUserVendors + " >> " + domain.fUserVendors)

            if (domain.id >0) {

                domain.tempInt1 = 5 //0.New Form, 1.Edit Form, 3.Delete , 5.Edit Vendors
                val tempVendors: MutableList<Int> = ArrayList()
                for (opdUserRole in domain.fUserVendorEntities!!) {
                    tempVendors.add(opdUserRole.fvendorBean)
                }
                domain.tempVendors = tempVendors

                model.addAttribute("domain", domain)
                return "users/user_form_vendor"
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound")
            }
        }
        return "redirect:/admin/users"
    }

    @RequestMapping(value = ["/createSuperUser"], method = [RequestMethod.GET])
    fun createSuperUser(): String {
        val newDomain = FUserEntity()
        newDomain.username = "des.jatim1@gmail.com"
        newDomain.email = "des.jatim1@gmail.com"
        newDomain.password = instance!!.passwordEncoder.encode("Welcome1")

        //Sementara otomatis
        val listFUserRoleEntities: MutableList<FUserRolesEntity> = ArrayList()
        val userRole1 = FUserRolesEntity()
        userRole1.fuserBean = newDomain
        userRole1.roleID = Role.USER
        val userRole2 = FUserRolesEntity()
        userRole2.fuserBean = newDomain
        userRole2.roleID = Role.ADMIN
        listFUserRoleEntities.add(userRole1)
        listFUserRoleEntities.add(userRole2)
        newDomain.fuserRoles = listFUserRoleEntities
        try {
            fUsersJPARepository.save<FUserEntity?>(newDomain)
            fUserRolesJPARepository.saveAll(listFUserRoleEntities)
        } catch (ex: Exception) {
            return "redirect:/login?error"
        }
        return "redirect:/login"
        // return "/login-form/login";
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UsersController::class.java)
    }
}