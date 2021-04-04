package com.erp.distribution.desgreenrestkt.domain.model_security

import com.erp.distribution.desgreenrestkt.data.source.entity.FSalesmanEntity
import com.erp.distribution.desgreenrestkt.data.source.entity_security.FUserEntity
import com.erp.distribution.desgreenrestkt.presentation.model_security.FUserRes
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.util.*
import javax.persistence.*

class FUser (
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

    var fuserRoles: List<FUserRoles> = listOf(),

    var fUserVendorEntities: List<FUserVendors> = listOf(),

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

internal fun FUser.toEntity(): FUserEntity {
    return FUserEntity(
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

internal fun FUser.toResponse(): FUserRes {
    return FUserRes(
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


