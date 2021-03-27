package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.*
import com.erp.distribution.desgreenrestkt.presentation.model.FPromotionRulesdValidCustsRes
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

internal fun FPromotionRulesdValidCusts.toEntity(): FPromotionRulesdValidCustsEntity {
    return FPromotionRulesdValidCustsEntity(
        id = id,
        noUrut = noUrut,

        fpromotionRuleshEntityBean = FPromotionRuleshEntity(fpromotionRuleshEntityBean.id),

        validCustomerGroupEntityBean = validCustomerGroupEntityBean?.let {  FCustomerGroupEntity(validCustomerGroupEntityBean!!.id) },
        validDistributionChannelEntityBean = validDistributionChannelEntityBean?.let {  FDistributionChannelEntity(validDistributionChannelEntityBean!!.id) },
        validAreaEntityBean = validAreaEntityBean?.let {  FAreaEntity(validAreaEntityBean!!.id) },
        validCustomerEntityBean = validCustomerEntityBean?.let {  FCustomerEntity(validCustomerEntityBean!!.id) },

        )
}
internal fun FPromotionRulesdValidCusts.toResponse(): FPromotionRulesdValidCustsRes {
    return FPromotionRulesdValidCustsRes(
        id = id,
        noUrut = noUrut,

//        fpromotionRuleshEntityBean = FPromotionRuleshRes(fpromotionRuleshEntityBean.id),
//
//        validCustomerGroupEntityBean = validCustomerGroupEntityBean?.let {  FCustomerGroupRes(validCustomerGroupEntityBean!!.id) },
//        validDistributionChannelEntityBean = validDistributionChannelEntityBean?.let {  FDistributionChannelRes(validDistributionChannelEntityBean!!.id) },
//        validAreaEntityBean = validAreaEntityBean?.let {  FAreaRes(validAreaEntityBean!!.id) },
//        validCustomerEntityBean = validCustomerEntityBean?.let {  FCustomerRes(validCustomerEntityBean!!.id) },

        )
}
