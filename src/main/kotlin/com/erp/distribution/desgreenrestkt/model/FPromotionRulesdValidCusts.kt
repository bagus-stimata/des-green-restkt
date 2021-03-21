package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "fpromotionrulesd_validcusts")
class FPromotionRulesdValidCusts : Serializable {
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
    @JoinColumn(name = "validCustomerGroupBean", referencedColumnName = "ID")
    var validCustomerGroupBean: FCustomerGroup? = null

    @ManyToOne
    @JoinColumn(name = "validDistributionChannelBean", referencedColumnName = "ID")
    var validDistributionChannelBean: FDistributionChannel? = null

    @ManyToOne
    @JoinColumn(name = "validAreaBean", referencedColumnName = "ID")
    var validAreaBean: FArea? = null

    @ManyToOne
    @JoinColumn(name = "validCustomerBean", referencedColumnName = "ID")
    var validCustomerBean: FCustomer? = null

    override fun toString(): String {
        return "" + id + ""
    }


}