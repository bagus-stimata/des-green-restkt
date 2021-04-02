package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.*
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name ="fpromotionrulesd_validproducts")
data class FPromotionRulesdValidProductsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_detil",
        allocationSize = 20,
        initialValue = 912345668
    )
    @Column(name ="ID")
    var id :Int =0,

    @Column(name ="NOURUT")
    var noUrut :Int =0,

    @ManyToOne
    @JoinColumn(name ="fpromotionRuleshBean", referencedColumnName ="ID")
    var fpromotionRuleshEntityBean: FPromotionRuleshEntity = FPromotionRuleshEntity(),

    /*
	 * Customer Classfication = null berarti semua/All
	 */
    @ManyToOne
    @JoinColumn(name ="validVendorBean", referencedColumnName ="ID")
    var validVendorEntityBean: FVendorEntity? = FVendorEntity(),

    @Column(name ="VALID_VENDOR_ACCUMULATION")
    var validVendorAccumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialSalesBrandBean", referencedColumnName ="ID")
    var validMaterialSalesBrandEntityBean: FMaterialSalesBrandEntity? = FMaterialSalesBrandEntity(),

    @Column(name ="VALID_SALESBRAND_ACCUMULATION")
    var validSalesBrandAccumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialGroup2Bean", referencedColumnName = "ID")
    var validMaterialGroup2EntityBean: FMaterialGroup2Entity? = FMaterialGroup2Entity(),

    @Column(name ="VALID_MATERIALGROUP2_ACCUMULATION")
    var validMaterialGroup2Accumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialGroup3Bean", referencedColumnName = "ID")
    var validMaterialGroup3EntityBean: FMaterialGroup3Entity? = FMaterialGroup3Entity(),

    @Column(name ="VALID_MATERIALGROUP3_ACCUMULATION")
    var validMaterialGroup3Accumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialBean", referencedColumnName ="ID")
    var validMaterialEntityBean: FMaterialEntity? = FMaterialEntity()


): Serializable

internal fun FPromotionRulesdValidProductsEntity.toDomain(): FPromotionRulesdValidProducts {
    return FPromotionRulesdValidProducts(
        id = id,
        noUrut = noUrut,

        fpromotionRuleshEntityBean = FPromotionRulesh(fpromotionRuleshEntityBean.id),

        validVendorEntityBean = validVendorEntityBean?.let {  FVendor(validVendorEntityBean!!.id) },
        validVendorAccumulation = validVendorAccumulation,

        validMaterialSalesBrandEntityBean = validMaterialSalesBrandEntityBean?.let {  FMaterialSalesBrand(validMaterialSalesBrandEntityBean!!.id) },
        validSalesBrandAccumulation = validSalesBrandAccumulation,

        validMaterialGroup2EntityBean = validMaterialGroup2EntityBean?.let {  FMaterialGroup2(validMaterialGroup2EntityBean!!.id) },
        validMaterialGroup2Accumulation = validMaterialGroup2Accumulation,

        validMaterialGroup3EntityBean = validMaterialGroup3EntityBean?.let {  FMaterialGroup3(validMaterialGroup3EntityBean!!.id) },
        validMaterialGroup3Accumulation = validMaterialGroup3Accumulation,

        validMaterialEntityBean = validMaterialEntityBean?.let {  FMaterial(validMaterialEntityBean!!.id) },

        )
}
