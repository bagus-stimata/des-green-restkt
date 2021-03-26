package com.erp.distribution.desgreenrestkt.domain.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

/**
 * The persistent class for the BANK database table.
 *
 */
@JacksonXmlRootElement
data class FParamDiskonItem(
    var id: Int = 0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    @Column(name ="SOURCE_ID")
    var sourceId: Int = 0,

    @Column(name = "NOREK", length = 15)
    var noRek :String ="",

    @Column(name ="DESCRIPTION", length = 300)
    var description :String ="",

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fdivisionBean :Int =0,, referencedColumnName= :Int =0,ID :Int =0,)
    //	private FDivision fdivisionBean;
    //	@Column(name= :Int =0,fdivisionBean :Int =0,, nullable = false)
    //	private Integer fdivisionBean :Int =0,;
    //Value
    var nominal1  :Double =0.0,
    var diskon1  :Double =0.0,
    var diskon1plus  :Double =0.0,
    var nominal2  :Double =0.0,
    var diskon2  :Double =0.0,
    var diskon2plus  :Double =0.0,
    var nominal3  :Double =0.0,
    var diskon3  :Double =0.0,
    var diskon3plus  :Double =0.0,
    var nominal4  :Double =0.0,
    var diskon4  :Double =0.0,
    var diskon4plus  :Double =0.0,
    var nominal5  :Double =0.0,
    var diskon5  :Double =0.0,
    var diskon5plus  :Double =0.0,

    //Qty In PCS
    var qtyLebihDari1 :Int =0,
    var diskonFromQty1  :Double =0.0,
    var diskonFromQty1plus  :Double =0.0,
    var qtyLebihDari2 :Int =0,
    var diskonFromQty2  :Double =0.0,
    var diskonFromQty2plus  :Double =0.0,
    var qtyLebihDari3 :Int =0,
    var diskonFromQty3  :Double =0.0,
    var diskonFromQty3plus  :Double =0.0,
    var qtyLebihDari4 :Int =0,
    var diskonFromQty4  :Double =0.0,
    var diskonFromQty4plus  :Double =0.0,
    var qtyLebihDari5 :Int =0,
    var diskonFromQty5  :Double =0.0,
    var diskonFromQty5plus  :Double =0.0,
    var isAllvendor  :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fvendorBean :Int =0,, referencedColumnName= :Int =0,ID :Int =0,)
    //	private FVendor fvendorBean;
    @Column(name = "fvendorBean")
    var fvendorBean :Int =0,
    var allsubgrup  :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fcustomerGroupBean :Int =0,, referencedColumnName= :Int =0,ID :Int =0,)
    //	private FCustomerGroup fcustomerGroupBean;
    @Column(name ="fcustomerGroupBean")
    var fcustomerGroupBean :Int =0,
    var allproductgrup  :Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,fmaterialGroup3Bean :Int =0,, referencedColumnName= :Int =0,ID :Int =0,)
    //	private FMaterialGroup3	fmaterialGroup3Bean;
    @Column(name ="fmaterialGroup3Bean")
    var fmaterialGroup3Bean :Int =0,
    var alltunaikredit  :Boolean =false,

    @Column(length = 5)
    var tunaikredit :String ="",
    var statusActive  :Boolean =false

    ): Serializable