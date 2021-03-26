package com.erp.distribution.desgreenrestkt.domain.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

/**
 * The persistent class for the BANK database table.
 *
 */
@JacksonXmlRootElement
data class FParamDiskonItemVendor(
     val id: Long = 0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name = "SOURCE_ID")
     val sourceId: Long = 0,

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fdivisionBean :Int =0,, referencedColumnName= :Int =0,ID :Int =0,)
    //	 FDivision fdivisionBean;
    //	@Column(name= :Int =0,fdivisionBean :Int =0,, nullable = false)
    //	 Integer fdivisionBean = 0;
    @Column(name ="DESCRIPTION", length = 150)
     val description :String = "",

    //QTY DIMANFAATKAN UNTUK RUPIAH
    @Column(name = "QTYORRUPIAH")
     val qtyOrRupiah  :Boolean =false, //true = Qty
     val nominal1  :Double =0.0, //Nominal or Qty
     val diskon1  :Double =0.0,
     val diskon1plus  :Double =0.0,
     val nominal2  :Double =0.0,
     val diskon2  :Double =0.0,
     val diskon2plus  :Double =0.0,
     val nominal3  :Double =0.0,
     val diskon3  :Double =0.0,
     val diskon3plus  :Double =0.0,
     val nominal4  :Double =0.0,
     val diskon4  :Double =0.0,
     val diskon4plus  :Double =0.0,
     val nominal5  :Double =0.0,
     val diskon5  :Double =0.0,
     val diskon5plus  :Double =0.0,
     val allvendor  :Boolean =true,

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fvendorBean :Int =0,, referencedColumnName= :Int =0,ID :Int =0,)
    //	 FVendor fvendorBean;
    @Column(name ="fvendorBean")
     val fvendorBean :Int = 0,
     val allProductGroup  :Boolean =true,

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fmaterialGroup3Bean :Int =0,, referencedColumnName= :Int =0,ID :Int =0,)
    //	 FMaterialGroup3 fmaterialGroup3Bean;
    @Column(name ="fmaterialGroup3Bean")
     val fmaterialGroup3Bean :Int = 0,

    @Column(name = "STATUS_ACTIVE")
     val statusActive  :Boolean =true

): Serializable