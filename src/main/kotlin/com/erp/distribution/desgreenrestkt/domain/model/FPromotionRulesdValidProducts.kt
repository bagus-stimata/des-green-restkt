package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.*
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
data class FPromotionRulesdValidProducts (
    var id :Int =0,

    @Column(name ="NOURUT")
    var noUrut :Int =0,

    @ManyToOne
    @JoinColumn(name ="fpromotionRuleshBean", referencedColumnName ="ID")
    var fpromotionRuleshEntityBean: FPromotionRulesh = FPromotionRulesh(),

    /*
	 * Customer Classfication = null berarti semua/All
	 */
    @ManyToOne
    @JoinColumn(name ="validVendorBean", referencedColumnName ="ID")
    var validVendorEntityBean: FVendor? = FVendor(),

    @Column(name ="VALID_VENDOR_ACCUMULATION")
    var validVendorAccumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialSalesBrandBean", referencedColumnName ="ID")
    var validMaterialSalesBrandEntityBean: FMaterialSalesBrand? = FMaterialSalesBrand(),

    @Column(name ="VALID_SALESBRAND_ACCUMULATION")
    var validSalesBrandAccumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialGroup2Bean", referencedColumnName = "ID")
    var validMaterialGroup2EntityBean: FMaterialGroup2? = FMaterialGroup2(),

    @Column(name ="VALID_MATERIALGROUP2_ACCUMULATION")
    var validMaterialGroup2Accumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialGroup3Bean", referencedColumnName = "ID")
    var validMaterialGroup3EntityBean: FMaterialGroup3? = FMaterialGroup3(),

    @Column(name ="VALID_MATERIALGROUP3_ACCUMULATION")
    var validMaterialGroup3Accumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialBean", referencedColumnName ="ID")
    var validMaterialEntityBean: FMaterial? = FMaterial()


): Serializable


internal fun FPromotionRulesdValidProducts.toEntity(): FPromotionRulesdValidProductsEntity {
    return FPromotionRulesdValidProductsEntity(
        id = id,
        noUrut = noUrut,

        fpromotionRuleshEntityBean = FPromotionRuleshEntity(fpromotionRuleshEntityBean.id),

        validVendorEntityBean = validVendorEntityBean?.let {  FVendorEntity(validVendorEntityBean!!.id) },
        validVendorAccumulation = validVendorAccumulation,

        validMaterialSalesBrandEntityBean = validMaterialSalesBrandEntityBean?.let {  FMaterialSalesBrandEntity(validMaterialSalesBrandEntityBean!!.id) },
        validSalesBrandAccumulation = validSalesBrandAccumulation,

        validMaterialGroup2EntityBean = validMaterialGroup2EntityBean?.let {  FMaterialGroup2Entity(validMaterialGroup2EntityBean!!.id) },
        validMaterialGroup2Accumulation = validMaterialGroup2Accumulation,

        validMaterialGroup3EntityBean = validMaterialGroup3EntityBean?.let {  FMaterialGroup3Entity(validMaterialGroup3EntityBean!!.id) },
        validMaterialGroup3Accumulation = validMaterialGroup3Accumulation,

        validMaterialEntityBean = validMaterialEntityBean?.let {  FMaterialEntity(validMaterialEntityBean!!.id) },

        )
}
