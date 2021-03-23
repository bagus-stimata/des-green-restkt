package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import java.util.*
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fmaterial_sales_brand")
data class FMaterialSalesBrand (
    @Id
    @Column(name ="ID", length = 9)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name ="SOURCE_ID")
    var sourceId :Int = 0,

    @Column(name ="KODE1", length = 10)
    var kode1 :String ="",

    @Column(name = "KODE2", length = 20)
    var kode2 :String ="",

    @Column(name ="DESCRIPTION", length = 100)
    var description :String ="",

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fdivisionBean :Int =0,, referencedColumnName= :Int =0,ID :Int =0,)
    //	private FDivision fdivisionBean;
    @Column(name = "fdivisionBean", nullable = false)
    var fdivisionBean   :Int = 0,

    @Column(name ="STATUS_ACTIVE")
    var statusActive  :Boolean =true,

    /*
	 * Diskon Margin Barang: disc2 & Disc2Plus
	 */
    @Column(name =  "DISC2_MARGIN")
    var disc2Margin  :Double =0.0,

    @Column(name ="DISC1PLUS_MARGIN")
    var disc1PlusMargin  :Double =0.0,

    @Column(name ="CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name ="MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name ="MODIFIED_BY", length = 20)
    var modifiedBy :String = "" //User ID

): Serializable