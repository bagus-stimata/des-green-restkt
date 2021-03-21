/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "sysvar")
class Sysvar : Serializable {
    @Id
    @Column(name = "ID", length = 35)
    var id: String? = ""

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID")
    var sourceID = ""

    @JsonIgnore
    @Column(name = "NOMOR_URUT")
    var nomorUrut = 0

    @JsonIgnore
    @Column(name = "VISIBLE")
    var isVisible = true

    @Column(name = "GROUPSYSVAR", length = 30)
    var groupSysvar = ""

    //** End Tools
    @Column(name = "DESKRIPSI", length = 300)
    var deskripsi = ""

    @Column(name = "NOTES", length = 300)
    var notes = ""

    @Column(name = "TIPEDATA", length = 10)
    var tipeData = ""

    @Column(name = "LENGHTDATA")
    var lenghtData = 0

    @Column(name = "PREFIX", length = 15)
    var prefix = ""

    @JsonIgnore
    @Column(name = "SUFFIX", length = 15)
    var suffix = ""

    @Column(name = "NILAISTRING1", length = 300)
    var nilaiString1 = ""

    @Column(name = "NILAISTRING2", length = 300)
    var nilaiString2 = ""

    @Column(name = "NILAIBOL1")
    var nilaiBol1 = false

    @Column(name = "NILAIBOL2")
    var nilaiBol2 = false

    @Column(name = "NILAIINT1")
    var nilaiInt1 = 0

    @Column(name = "NILAIINT2")
    var nilaiInt2 = 0

    @Column(name = "NILAIDOUBLE1")
    var nilaiDouble1 = 0.0

    @Column(name = "NILAIDOUBLE2")
    var nilaiDouble2 = 0.0

    @Temporal(TemporalType.DATE)
    @Column(name = "NILAIDATE1")
    var nilaiDate1: Date? = null

    @Temporal(TemporalType.DATE)
    @Column(name = "NILAIDATE2")
    var nilaiDate2: Date? = null

    @Temporal(TemporalType.DATE) //date bisa time juga kok
    @Column(name = "NILAITIME1")
    var nilaiTime1: Date? = null

    @Temporal(TemporalType.DATE) //date bisa time juga kok
    @Column(name = "NILAITIME2")
    var nilaiTime2: Date? = null

    /*
     * DIPAKAI UNTUK 
     * Level 1= Level Aplikasi
     * Level 2= Level Company
     * Level 3= Level Division
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fcompanyBean", referencedColumnName = "ID")
    var fcompanyBean: FCompany? = null

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
    var fdivisionBean: FDivision? = null

    @JsonIgnore
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @JsonIgnore
    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @JsonIgnore
    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID



    companion object {
        const val serialversionuid = 1L
    }

}