package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fpromotionrulesd_validproducts")
class FPromotionRulesdValidProducts : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 9)
    var id = 0

    @Column(name = "NOURUT", length = 4)
    var noUrut = 0

    @ManyToOne
    @JoinColumn(name = "fpromotionRuleshBean", referencedColumnName = "ID")
    var fpromotionRuleshBean: FPromotionRulesh? = null

    /*
	 * Customer Classfication = null berarti semua/All
	 */
    @ManyToOne
    @JoinColumn(name = "validVendorBean", referencedColumnName = "ID")
    var validVendorBean: FVendor? = null

    @Column(name = "VALID_VENDOR_ACCUMULATION")
    var isValidVendorAccumulation = false

    @ManyToOne
    @JoinColumn(name = "validMaterialSalesBrandBean", referencedColumnName = "ID")
    var validMaterialSalesBrandBean: FMaterialSalesBrand? = null

    @Column(name = "VALID_SALESBRAND_ACCUMULATION")
    var isValidSalesBrandAccumulation = false

    @ManyToOne
    @JoinColumn(name = "validMaterialGroup2Bean", referencedColumnName = "ID")
    var validMaterialGroup2Bean: FMaterialGroup2? = null

    @Column(name = "VALID_MATERIALGROUP2_ACCUMULATION")
    var isValidMaterialGroup2Accumulation = false

    @ManyToOne
    @JoinColumn(name = "validMaterialGroup3Bean", referencedColumnName = "ID")
    var validMaterialGroup3Bean: FMaterialGroup3? = null

    @Column(name = "VALID_MATERIALGROUP3_ACCUMULATION")
    var isValidMaterialGroup3Accumulation = false

    @ManyToOne
    @JoinColumn(name = "validMaterialBean", referencedColumnName = "ID")
    var validMaterialBean: FMaterial? = null

    override fun toString(): String {
        return "" + id + ""
    }


}