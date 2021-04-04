package com.erp.distribution.desgreenrestkt.presentation.rest_security

import com.erp.distribution.desgreenrestkt.security_config.PassEncoding
import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.toDomain
import com.erp.distribution.desgreenrestkt.data.source.local.dao_security.FUsersJPARepository
import com.erp.distribution.desgreenrestkt.domain.model_security.toResponse
import com.erp.distribution.desgreenrestkt.presentation.model_security.FUserRes
import com.erp.distribution.desgreenrestkt.security_config.SecurityUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class FUserRestController @Autowired constructor(
    val fUsersJPARepository: FUsersJPARepository,
    val securityUtils: SecurityUtils
) {
//    @Autowired
//    lateinit var fUserJPARepository: FUsersJPARepository

    @RequestMapping(value = ["/rest/getFUserById2/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFUserById(@PathVariable("id") id: Int): FUserEntity {
        return fUsersJPARepository.findById(id).get()
    }

    @RequestMapping(value = ["/rest/getFUserByUsername/{username}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFUserByUsername(@PathVariable("username") username: String): FUserRes {

//        println("#result Masuk sini 1 >> ${username}")

        var fUser =FUserEntity()
        var fUserB =FUserEntity()
        try{
            fUser = fUsersJPARepository.findByUsername(username)
        }catch (e: Exception){
        }

        return fUser.toDomain().toResponse()
    }

    @RequestMapping(value = ["/rest/getFUserByUsernamePassword/{username}/{password}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFUserByUsernamePassword(@PathVariable("username") username: String, @PathVariable("password") password: String): FUserRes {
        val encodedPasword = PassEncoding.instance!!.passwordEncoder.encode(password.trim { it <= ' ' })
        val findUser = fUsersJPARepository.findByUsername(username)
//        println("Pasword: " + username + " >> " + encodedPasword + " >> " + findUser!!.password)
//        var returnUser = FUser()
//        if (findUser != null) {
//            if (findUser.password == encodedPasword) {
//                returnUser = findUser
//            }
//        }
//        return returnUser
        if (findUser.password == encodedPasword) {
            return findUser.toDomain().toResponse()
        }else {
            return FUserRes()
        }
    }

    @RequestMapping(value = ["/rest/getFUserByEmail/{username}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFUserByEmail(@PathVariable("email") email: String): FUserRes {
        return fUsersJPARepository.findByEmail(email).toDomain().toResponse()
    }

    /**
     * THIS BELOW IS DANGEROUS IF OPEN
     * @return
     */
    @get:RequestMapping(value = ["/rest/getAllFUser"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allUserEntity: List<FUserRes>
        get() =//        return new ArrayList<>();
            fUsersJPARepository.findAll().map {
                it.toDomain().toResponse()
            }
    //    @RequestMapping(value = "/rest/createFUser", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    //    public FUser createFUser(@RequestBody FUser fUserNew) {
    //        FUser updatedDomain = new FUser();
    //        try {
    //            updatedDomain = fUserJPARepository.save(fUserNew);
    //        } catch (Exception e) {}
    //        return updatedDomain;
    //    }
    //    @RequestMapping(value = "/rest/updateFUserInfo/{id}", method = RequestMethod.PUT )
    //    public FUser updateFUserInfo(@PathVariable("id") Integer id, @RequestBody FUser fUserUpdated){
    //        FUser fUser = fUserJPARepository.findById(id).orElse(new FUser());
    //        //Tidak Meng Update Parent: Hanya Info Saja
    //        FUser updatedDomain = new FUser();
    //        if (fUserUpdated !=null) {
    //            fUserUpdated.setId(fUser.getId());
    //           try {
    //                updatedDomain = fUserJPARepository.save(fUserUpdated);
    //            } catch (Exception e) {}
    //            return updatedDomain;
    //        }
    //
    //        return fUser;
    //    }
    //
    //    @RequestMapping(value = "/rest/deleteFUser/{id}", method = RequestMethod.DELETE )
    //    public FUser deleteFUser(@PathVariable("id") Integer id){
    //        FUser fUser = fUserJPARepository.findById(id).orElse(new FUser());
    //        if (fUser !=null) {
    //            fUserJPARepository.delete(fUser);
    //        }
    //        return fUser;
    //    }
    /**
     * A. Pendaftaran User
     * 1. Create From User Sesuai dengan Form yang isikan
     * 2. Generate Random Link Aktivasi (dan mengirimkan lagi username dan password yang diisikan)
     * 3. Send Email dengan Membuat Link Aktifasi
     *
     * B. Aktifasi User
     * 1. Meng Aktifkan Link Aktifasi menjadi Unlock
     */
    @RequestMapping(value = ["/rest/pendaftaran/{id}"], method = [RequestMethod.POST])
    fun pendaftaranFUser(@PathVariable("id") id: Int): FUserRes {
        val fUserEntity: FUserEntity? = fUsersJPARepository.findById(id).get()
//        if (fUser != null) {
//            fUserJPARepository.delete(fUser)
//        }
        fUserEntity?.let {
            fUsersJPARepository.delete(fUserEntity)
        }
        return fUserEntity!!.toDomain().toResponse()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FUserRestController::class.java)
    }
}