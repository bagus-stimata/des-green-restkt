package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fmaterial_group3")
data class FMaterialGroup3Entity (
    @Id
    @Column(name = "ID", length = 9)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id  :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID", length = 9)
    var sourceId  :Int =0,

    @Column(name = "KODE1", length = 10)
    var kode1 :String ="",

    @Column(name = "KODE2", length = 20)
    var kode2 :String ="",

    @Column(name = "DESCRIPTION", length = 100)
    var description :String ="",

    @Transient
    var tempInt1  :Int =0,

    @Transient
    var tempInt2  :Int =0,

    @Transient
    var tempInt3  :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup2Bean", referencedColumnName="ID")
    //	private FMaterialGroup2 fmaterialGroup2Bean;
    @Column(name = "fmaterialGroup2Bean", nullable = false)
    var fmaterialGroup2Bean  :Int =0,

    @Column(name = "STATUS_ACTIVE")
    var statusActive  :Boolean =true,

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy :String ="" //User ID

): Serializable{
    constructor(theId: Int): this(id = theId)
}