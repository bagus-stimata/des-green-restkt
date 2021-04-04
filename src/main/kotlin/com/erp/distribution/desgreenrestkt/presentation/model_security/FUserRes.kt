package com.erp.distribution.desgreenrestkt.presentation.model_security

import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserEntity
import com.erp.distribution.desgreenrestkt.domain.model_security.FUser
import java.io.Serializable
import java.util.*
import javax.persistence.*

class FUserRes (
    var id :Int =0,

    @Column(unique = true)
    var email :String ="",
    @Column(unique = true)
    var username :String ="",
    var password :String ="",
    var passwordConfirm :String ="",
    var fullName :String ="",

    var phone :String ="",

    var notes :String ="",

    var fuserRoles: List<FUserRolesRes> = listOf(),

    var fUserVendorEntities: List<FUserVendorsRes> = listOf(),

    var tempRoles: MutableList<String> = mutableListOf(),


    var tempVendors: List<Int> = listOf(),

    var locked :Boolean = false,

    /**
     * Tambahan for DES Setting
     */
    var fsalesmanBean: Int? = 0,
    var fdivisionBean: Int?  = 0,
    var fwarehouseBean: Int?  = 0,

    var tempInt1 :Int =0,

    var tempBol1 :Boolean =false,
    var tempBol2 :Boolean =false,
    var created :Date =Date(),
    var lastModified :Date =Date(),
    var modifiedBy :String =""


): Serializable

internal fun FUserRes.toDomain(): FUser {
    return FUser(
        id = id,
        email = email,
        username = username,
        password = password,
        passwordConfirm = passwordConfirm,
        fullName = fullName,
        phone = phone,
        notes = notes,

//        fuserRoles =  fuserRoles,
//        fUserVendorEntities =  fUserVendorEntities,
//        tempRoles =  tempRoles,
//        tempVendors =  tempVendors,

        locked =  locked,
        fsalesmanBean =  fsalesmanBean,
        fdivisionBean = fdivisionBean,
        fwarehouseBean = fwarehouseBean,

        tempInt1 = tempInt1,
        tempBol2 = tempBol2,

        created = created,
        lastModified = lastModified,
        modifiedBy = modifiedBy

    )
}

