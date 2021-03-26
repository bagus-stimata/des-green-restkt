package com.erp.distribution.desgreenrestkt.domain.model.aux

import java.util.*
import javax.persistence.*

class ZLapTemplate1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var iD: Long = 0
    var noUrut = 0
    var grup1 = ""
    var grup2 = ""
    var grup3 = ""
    var string1 = ""
    var string2 = ""
    var string3 = ""
    var string4 = ""
    var string5 = ""
    var string6 = ""
    var string7 = ""
    var string8 = ""
    var string9 = ""
    var string10 = ""
    var int1 = 0
    var int2 = 0
    var int3 = 0
    var int4 = 0
    var int5 = 0
    var double1 = 0.0
    var double2 = 0.0
    var double3 = 0.0
    var double4 = 0.0
    var double5 = 0.0

    @Column(name = "DATE1")
    @Temporal(TemporalType.DATE)
    var date1: Date? = null

    @Column(name = "DATE2")
    @Temporal(TemporalType.DATE)
    var date2: Date? = null

    @Column(name = "DATE3")
    @Temporal(TemporalType.DATE)
    var date3: Date? = null


    override fun toString(): String {
        return "" + iD + ""
    }
}