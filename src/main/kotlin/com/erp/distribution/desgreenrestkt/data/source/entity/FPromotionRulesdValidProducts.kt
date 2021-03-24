package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name ="fpromotionrulesd_validproducts")
data class FPromotionRulesdValidProducts (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="ID", length = 9)
    var id :Int =0,

    @Column(name ="NOURUT")
    var noUrut :Int =0,

    @ManyToOne
    @JoinColumn(name ="fpromotionRuleshBean", referencedColumnName ="ID")
    var fpromotionRuleshBean: FPromotionRulesh? = FPromotionRulesh(),

    /*
	 * Customer Classfication = null berarti semua/All
	 */
    @ManyToOne
    @JoinColumn(name ="validVendorBean", referencedColumnName ="ID")
    var validVendorBean: FVendor? = FVendor(),

    @Column(name ="VALID_VENDOR_ACCUMULATION")
    var validVendorAccumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialSalesBrandBean", referencedColumnName ="ID")
    var validMaterialSalesBrandBean: FMaterialSalesBrand? = FMaterialSalesBrand(),

    @Column(name ="VALID_SALESBRAND_ACCUMULATION")
    var validSalesBrandAccumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialGroup2Bean", referencedColumnName = "ID")
    var validMaterialGroup2Bean: FMaterialGroup2? = FMaterialGroup2(),

    @Column(name ="VALID_MATERIALGROUP2_ACCUMULATION")
    var validMaterialGroup2Accumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialGroup3Bean", referencedColumnName = "ID")
    var validMaterialGroup3Bean: FMaterialGroup3? = FMaterialGroup3(),

    @Column(name ="VALID_MATERIALGROUP3_ACCUMULATION")
    var validMaterialGroup3Accumulation  :Boolean =false,

    @ManyToOne
    @JoinColumn(name ="validMaterialBean", referencedColumnName ="ID")
    var validMaterialEntityBean: FMaterialEntity? = FMaterialEntity()


): Serializable