package com.erp.distribution.desgreenrestkt.domain.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
data class FPromotionRulesdValidCusts (
    var id :Int =0,

    @Column(name ="NOURUT", length = 4)
    var noUrut :Int =0,

    @ManyToOne
    @JoinColumn(name ="fpromotionRuleshBean", referencedColumnName = "ID")
    var fpromotionRuleshEntityBean: FPromotionRulesh = FPromotionRulesh(),

    /*
	 * Customer Classfication = null berarti semua/All
	 */
    @ManyToOne
    @JoinColumn(name ="validCustomerGroupBean", referencedColumnName = "ID")
    var validCustomerGroupEntityBean: FCustomerGroup? = FCustomerGroup(),

    @ManyToOne
    @JoinColumn(name ="validDistributionChannelBean", referencedColumnName ="ID")
    var validDistributionChannelEntityBean: FDistributionChannel? = FDistributionChannel(),

    @ManyToOne
    @JoinColumn(name ="validAreaBean", referencedColumnName ="ID")
    var validAreaEntityBean: FArea? = FArea(),

    @ManyToOne
    @JoinColumn(name ="validCustomerBean", referencedColumnName ="ID")
    var validCustomerEntityBean: FCustomer? = FCustomer()


): Serializable