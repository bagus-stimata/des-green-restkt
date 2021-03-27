/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.SysvarEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class Sysvar (
    var id: String ="",

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID")
    var sourceId :String ="",

    @JsonIgnore
    @Column(name = "NOMOR_URUT")
    var nomorUrut  :Int =0,

    @JsonIgnore
    @Column(name = "VISIBLE")
    var visible  :Boolean =true,

    @Column(name = "GROUPSYSVAR", length = 30)
    var groupSysvar :String ="",

    //** End Tools
    @Column(name = "DESKRIPSI", length = 300)
    var deskripsi :String ="",

    @Column(name = "NOTES", length = 300)
    var notes :String ="",

    @Column(name = "TIPEDATA", length = 10)
    var tipeData :String ="",

    @Column(name = "LENGHTDATA")
    var lenghtData  :Int =0,

    @Column(name = "PREFIX", length = 15)
    var prefix :String ="",

    @JsonIgnore
    @Column(name = "SUFFIX", length = 15)
    var suffix :String ="",

    @Column(name = "NILAISTRING1", length = 300)
    var nilaiString1 :String ="",

    @Column(name = "NILAISTRING2", length = 300)
    var nilaiString2 :String ="",

    @Column(name = "NILAIBOL1")
    var nilaiBol1  :Boolean =false,

    @Column(name = "NILAIBOL2")
    var nilaiBol2  :Boolean =false,

    @Column(name = "NILAIINT1")
    var nilaiInt1  :Int =0,

    @Column(name = "NILAIINT2")
    var nilaiInt2  :Int =0,

    @Column(name = "NILAIDOUBLE1")
    var nilaiDouble1  :Double =0.0,

    @Column(name = "NILAIDOUBLE2")
    var nilaiDouble2  :Double =0.0,

    @Temporal(TemporalType.DATE)
    @Column(name = "NILAIDATE1")
    var nilaiDate1: Date? = Date(),

    @Temporal(TemporalType.DATE)
    @Column(name = "NILAIDATE2")
    var nilaiDate2: Date? = Date(),

    @Temporal(TemporalType.DATE) //date bisa time juga kok
    @Column(name = "NILAITIME1")
    var nilaiTime1: Date? = Date(),

    @Temporal(TemporalType.DATE) //date bisa time juga kok
    @Column(name = "NILAITIME2")
    var nilaiTime2: Date? = Date(),

    /*
     * DIPAKAI UNTUK 
     * Level 1= Level Aplikasi
     * Level 2= Level Company
     * Level 3= Level Division
     */
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "fcompanyBean", referencedColumnName = "ID")
//    var fcompanyBean: FCompany? = FCompany(),
    var fcompanyBean: Int? = 0,

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
//    var fdivisionBean: FDivision? = FDivision(),
    var fdivisionBean: Int? = 0,

    @JsonIgnore
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @JsonIgnore
    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @JsonIgnore
    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User ID



): Serializable


internal fun Sysvar.toEntity(): SysvarEntity {
    return SysvarEntity(
        id = id,
        sourceId = sourceId,

        nomorUrut = nomorUrut,
        visible = visible,
        groupSysvar = groupSysvar,
        deskripsi = deskripsi,
        notes = notes,
        tipeData = tipeData,
        lenghtData = lenghtData,
        prefix = prefix,
        suffix = suffix,

        nilaiString1 = nilaiString1,
        nilaiString2 = nilaiString2,
        nilaiBol1 = nilaiBol1,
        nilaiBol2 = nilaiBol2,
        nilaiInt1 = nilaiInt1,
        nilaiInt2 = nilaiInt2,
        nilaiDouble1 = nilaiDouble1,
        nilaiDouble2 = nilaiDouble2,

        nilaiDate1 = nilaiDate1,
        nilaiDate2 = nilaiDate2,
        nilaiTime1 = nilaiTime1,
        nilaiTime2 = nilaiTime2,

        fcompanyBean = fcompanyBean,
        fdivisionBean = fdivisionBean,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}
