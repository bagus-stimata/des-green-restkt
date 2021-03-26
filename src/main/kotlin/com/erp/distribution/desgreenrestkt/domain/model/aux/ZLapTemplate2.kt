package com.erp.distribution.desgreenrestkt.domain.model.aux

import java.util.*
import javax.persistence.Column
import javax.persistence.Temporal
import javax.persistence.TemporalType

data class ZLapTemplate2 (
    var  id: Long? = null,
    var  longID: Long = 0,
    var  intID : Int = 0,
    var  noUrut : Int = 0,

    @Column(name = "GRUP1")
    var  grup1: String? = "",

    @Column(name = "GRUP2")
    var  grup2: String? = "",

    @Column(name = "GRUP3")
    var  grup3: String? = "",

    @Column(name = "GRUP4")
    var  grup4: String? = "",

    @Column(name = "GRUP5")
    var  grup5: String? = "",

    @Column(name = "STRING1")
    var  string1: String? = "",

    @Column(name = "STRING2")
    var  string2: String? = "",

    @Column(name = "STRING3")
    var  string3: String? = "",

    @Column(name = "STRING4")
    var  string4: String? = "",

    @Column(name = "STRING5")
    var  string5: String? = "",

    @Column(name = "STRING6")
    var  string6: String? = "",

    @Column(name = "STRING7")
    var  string7: String? = "",

    @Column(name = "STRING8")
    var  string8: String? = "",

    @Column(name = "STRING9")
    var  string9: String? = "",

    @Column(name = "STRING10")
    var  string10: String? = "",

    @Column(name = "STRING11")
    var  string11: String? = "",

    @Column(name = "STRING12")
    var  string12: String? = "",

    @Column(name = "STRING13")
    var  string13: String? = "",

    @Column(name = "STRING14")
    var  string14: String? = "",

    @Column(name = "STRING15")
    var  string15: String? = "",

    @Column(name = "STRING16")
    var  string16: String? = "",

    @Column(name = "STRING17")
    var  string17: String? = "",

    @Column(name = "STRING18")
    var  string18: String? = "",

    @Column(name = "STRING19")
    var  string19: String? = "",

    @Column(name = "STRING20")
    var  string20: String? = "",

    @Column(name = "STRING21")
    var  string21: String? = "",

    @Column(name = "STRING22")
    var  string22: String? = "",

    //DISC NOTA
    @Column(name = "STRING23")
    var  string23: String? = "",

    /*
	 * TAMBAHAN BARU
	 */
    var  string24: String? = "",
    var  string25: String? = "",
    var  string26: String? = "",
    var  string27: String? = "",
    var  string28: String? = "",
    var  string29: String? = "",
    var  string30: String? = "",

    @Column(name = "INTEGER1")
    var  int1 : Int = 0,

    @Column(name = "INTEGER2")
    var  int2 : Int = 0,

    @Column(name = "INTEGER3")
    var  int3 : Int = 0,

    @Column(name = "INTEGER4")
    var  int4 : Int = 0,

    @Column(name = "INTEGER5")
    var  int5 : Int = 0,

    @Column(name = "INTEGER6")
    var  int6 : Int = 0,

    @Column(name = "INTEGER7")
    var  int7 : Int = 0,

    @Column(name = "INTEGER8")
    var  int8 : Int = 0,

    @Column(name = "INTEGER9")
    var  int9 : Int = 0,

    @Column(name = "INTEGER10")
    var  int10 : Int = 0,
    var  int11 : Int = 0,
    var  int12 : Int = 0,
    var  int13 : Int = 0,
    var  int14 : Int = 0,
    var  int15 : Int = 0,
    var  int16 : Int = 0,
    var  int17 : Int = 0,
    var  int18 : Int = 0,
    var  int19 : Int = 0,
    var  int20 : Int = 0,

    @Column(name = "DOUBLE1")
    var  double1 : Double =0.0,

    @Column(name = "DOUBLE2")
    var  double2 : Double =0.0,

    @Column(name = "DOUBLE3")
    var  double3 : Double =0.0,

    @Column(name = "DOUBLE4")
    var  double4 : Double =0.0,

    @Column(name = "DOUBLE5")
    var  double5 : Double =0.0,

    @Column(name = "DOUBLE6")
    var  double6 : Double =0.0,

    @Column(name = "DOUBLE7")
    var  double7 : Double =0.0,

    @Column(name = "DOUBLE8")
    var  double8 : Double =0.0,

    @Column(name = "DOUBLE9")
    var  double9 : Double =0.0,

    @Column(name = "DOUBLE10")
    var  double10 : Double =0.0,

    @Column(name = "DOUBLE11")
    var  double11 : Double =0.0,

    @Column(name = "DOUBLE12")
    var  double12 : Double =0.0,

    @Column(name = "DOUBLE13")
    var  double13 : Double =0.0,

    @Column(name = "DOUBLE14")
    var  double14 : Double =0.0,

    @Column(name = "DOUBLE15")
    var  double15 : Double =0.0,

    @Column(name = "DOUBLE16")
    var  double16 : Double =0.0,

    @Column(name = "DOUBLE17")
    var  double17 : Double =0.0,

    @Column(name = "DOUBLE18")
    var  double18 : Double =0.0,

    @Column(name = "DOUBLE19")
    var  double19 : Double =0.0,

    @Column(name = "DOUBLE20")
    var  double20 : Double =0.0,

    @Column(name = "DOUBLE21")
    var  double21 : Double =0.0,
    var  double22 : Double =0.0,
    var  double23 : Double =0.0,
    var  double24 : Double =0.0,
    var  double25 : Double =0.0,
    var  double26 : Double =0.0,
    var  double27 : Double =0.0,
    var  double28 : Double =0.0,
    var  double29 : Double =0.0,
    var  double30 : Double =0.0,

    //PINDAHAN DARI INT
    var  double31 : Double =0.0,
    var  double32 : Double =0.0,
    var  double33 : Double =0.0,
    var  double34 : Double =0.0,
    var  double35 : Double =0.0,
    var  double36 : Double =0.0,
    var  double37 : Double =0.0,
    var  double38 : Double =0.0,
    var  double39 : Double =0.0,
    var  double40 : Double =0.0,
    var  double41 : Double =0.0,
    var  double42 : Double =0.0,
    var  double43 : Double =0.0,
    var  double44 : Double =0.0,
    var  double45 : Double =0.0,
    var  double46 : Double =0.0,
    var  double47 : Double =0.0,
    var  double48 : Double =0.0,

    @Column(name = "DATE1")
    @Temporal(TemporalType.DATE)
    var  date1: Date? = Date(),

    @Column(name = "DATE2")
    @Temporal(TemporalType.DATE)
    var  date2: Date? = Date(),

    @Column(name = "DATE3")
    @Temporal(TemporalType.DATE)
    var  date3: Date? = Date(),

    @Column(name = "DATE4")
    @Temporal(TemporalType.DATE)
    var  date4: Date? = Date(),

    @Column(name = "DATE5")
    @Temporal(TemporalType.DATE)
    var  date5: Date? = Date(),
    var  isBol1 : Boolean = false,
    var  isBol2 : Boolean = false,
    var  isBol3 : Boolean = false,
    var  isBol4 : Boolean = false,
    var  isBol5 : Boolean = false

)