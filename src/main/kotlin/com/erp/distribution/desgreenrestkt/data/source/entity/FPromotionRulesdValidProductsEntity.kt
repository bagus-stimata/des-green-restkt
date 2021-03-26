package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name ="fpromotionrulesd_validproducts")
data class FPromotionRulesdValidProductsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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