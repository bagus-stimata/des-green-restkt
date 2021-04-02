package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.*
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name ="fpromotionrulesd_validcusts")
data class FPromotionRulesdValidCustsEntity (
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

    @Column(name ="NOURUT", length = 4)
    var noUrut :Int =0,

    @ManyToOne
    @JoinColumn(name ="fpromotionRuleshBean", referencedColumnName = "ID")
    var fpromotionRuleshEntityBean: FPromotionRuleshEntity = FPromotionRuleshEntity(),

    /*
	 * Customer Classfication = null berarti semua/All
	 */
    @ManyToOne
    @JoinColumn(name ="validCustomerGroupBean", referencedColumnName = "ID")
    var validCustomerGroupEntityBean: FCustomerGroupEntity? = FCustomerGroupEntity(),

    @ManyToOne
    @JoinColumn(name ="validDistributionChannelBean", referencedColumnName ="ID")
    var validDistributionChannelEntityBean: FDistributionChannelEntity? = FDistributionChannelEntity(),

    @ManyToOne
    @JoinColumn(name ="validAreaBean", referencedColumnName ="ID")
    var validAreaEntityBean: FAreaEntity? = FAreaEntity(),

    @ManyToOne
    @JoinColumn(name ="validCustomerBean", referencedColumnName ="ID")
    var validCustomerEntityBean: FCustomerEntity? = FCustomerEntity()


): Serializable

internal fun FPromotionRulesdValidCustsEntity.toDomain(): FPromotionRulesdValidCusts {
    return FPromotionRulesdValidCusts(
        id = id,
        noUrut = noUrut,

        fpromotionRuleshEntityBean = FPromotionRulesh(fpromotionRuleshEntityBean.id),

        validCustomerGroupEntityBean = validCustomerGroupEntityBean?.let {  FCustomerGroup(validCustomerGroupEntityBean!!.id)},
        validDistributionChannelEntityBean = validDistributionChannelEntityBean?.let {  FDistributionChannel(validDistributionChannelEntityBean!!.id) },
        validAreaEntityBean = validAreaEntityBean?.let {  FArea(validAreaEntityBean!!.id) },
        validCustomerEntityBean = validCustomerEntityBean?.let {  FCustomer(validCustomerEntityBean!!.id)},

    )
}
