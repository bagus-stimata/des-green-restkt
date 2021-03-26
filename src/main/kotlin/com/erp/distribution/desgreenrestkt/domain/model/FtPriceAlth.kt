package com.erp.distribution.desgreenrestkt.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
data class FtPriceAlth (
    //** Tools: Jangan dihapus
    //** End Tools
    var id :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @JsonIgnore
    @Column(name =  "SOURCE_ID" , length = 9)
    var sourceId :Int =0,

    @Column(name =  "NO_REK" , length = 15)
    var noRek :String ="",

    @Column(name =  "DESCRIPTION" , length = 50)
    var description :String ="",

    //	@ManyToOne
    //	@JoinColumn(name= fdivisionBean , referencedColumnName= ID , nullable=false)
    //	private FDivision fdivisionBean;
    @Column(name =  "fdivisionBean" , nullable = false)
    var fdivisionBean :Int =0,

    @Temporal(TemporalType.DATE)
    @Column(name =  "TR_DATE" )
    var trDate :Date =Date(),

    /**
     * Pastikan yang ditarik pasti aktif
     */
    @JsonIgnore
    @Column(name =  "STATUS_ACTIVE" )
    var statusActive  :Boolean =true,

    @JsonIgnore
    @Column(name =  "CREATED" )
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name =  "MODIFIED" )
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @JsonIgnore
    @Column(name =  "MODIFIED_BY" , length = 20)
    var modifiedBy :String =""    //User ID

): Serializable