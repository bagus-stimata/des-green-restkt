package com.erp.distribution.desgreenrestkt.domain.model.aux

import java.util.*
import javax.persistence.*

class ZLapPrestasiKerja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "GRUP1")
    var grup1: String? = null

    @Column(name = "GRUP2")
    var grup2: String? = null

    @Column(name = "GRUP3")
    var grup3: String? = null

    @Column(name = "HARI")
    var hari: String? = null

    @Temporal(TemporalType.DATE)
    var tanggal: Date? = null

    //OUTLET ACTIVE
    @Column(name = "JUMLAHTOKO")
    var jumlahToko: Int? = null

    @Column(name = "JUMLAHNOTA")
    var jumlahNota: Int? = null

    @Column(name = "EFECTIVECALL")
    var efectiveCall: Int? = null

    @Column(name = "SKUSOLD")
    var skuSold: Int? = null

    @Column(name = "LINES")
    var lines: Int? = null

    // SKU / EC
    @Column(name = "RATARATA")
    var rataRata: Double? = null

    @Column(name = "RATARATA2")
    var rataRata2: Double? = null

    @Column(name = "TOTALBEFOREDISCBEFOREPPN")
    var totalBeforediscBeforeppn: Double? = null

    @Column(name = "DISCPERBARANG")
    var discPerbarang: Double? = null

    @Column(name = "DISCNOTA")
    var discNota: Double? = null

    @Column(name = "RETURDPP")
    var returdpp: Double? = null

    @Column(name = "RETURPPN")
    var returppn: Double? = null

    @Column(name = "DPP")
    var dpp: Double? = null

    @Column(name = "PPN")
    var ppn: Double? = null

    @Column(name = "TOTALAFTERDISCAFTERPPN")
    var totalAfterdiscAfterppn: Double? = null

    @Column(name = "STRING1")
    var string1: String? = null

    @Column(name = "STRING2")
    var string2: String? = null

    @Column(name = "INTEGER1")
    var integer1: Int? = null

    @Column(name = "INTEGER2")
    var integer2: Int? = null

    @Column(name = "DOUBLE1")
    var double1: Double? = null

    @Column(name = "DOUBLE2")
    var double2: Double? = null

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE1")
    var date1: Date? = null

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE2")
    var date2: Date? = null

    override fun toString(): String {
        return "" + id + ""
    }


}