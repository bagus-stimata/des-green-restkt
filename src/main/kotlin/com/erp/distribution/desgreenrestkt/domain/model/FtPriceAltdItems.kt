package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.data.source.entity.FDistributionChannelEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAltdItemsEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FtPriceAlthEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
data class FtPriceAltdItems (
    var id :Int =0,

    @JsonIgnore
    @Column(name =  "NO_URUT" , length = 4)
    var noUrut :Int =0,

    @JsonIgnore
    @Column(name =  "PPRICE" )
    var pprice  :Double =0.0,

    @JsonIgnore
    @Column(name =  "PPRICE_AFTERPPN" )
    var ppriceAfterPpn: Double = 0.0,

    @JsonIgnore
    @Column(name =  "PPRICE2" )
    var pprice2  :Double =0.0,

    @JsonIgnore
    @Column(name =  "PPRICE2_AFTERPPN" )
    var pprice2AfterPpn  :Double =0.0,

    @Column(name =  "SPRICE" )
    var sprice  :Double =0.0,

    @Column(name =  "SPRICE_AFTERPPN" )
    var spriceAfterPpn: Double = 0.0,

    @Column(name =  "SPRICE2" )
    var sprice2  :Double =0.0,

    @Column(name =  "SPRICE2_AFTERPPN" )
    var sprice2AfterPpn  :Double =0.0,

    @ManyToOne
    @JoinColumn(name= "ftPriceAlthBean" , referencedColumnName= "ID" )
    var ftPriceAlthBean : FtPriceAlth =  FtPriceAlth(),
//    @Column(name =  "ftPriceAlthBean" , nullable = false)
//    var ftPriceAlthBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fmaterialBean , referencedColumnName= ID )
    //	private FMaterial fmaterialBean;
    @Column(name =  "fmaterialBean" , nullable = false)
    var fmaterialBean :Int =0

): Serializable

internal fun FtPriceAltdItems.toEntity(): FtPriceAltdItemsEntity {
    return FtPriceAltdItemsEntity(
        id = id,
        noUrut = noUrut,

        pprice = pprice,
        ppriceAfterPpn = ppriceAfterPpn,
        pprice2 = pprice2,
        pprice2AfterPpn = pprice2AfterPpn,
        sprice = sprice,
        spriceAfterPpn = spriceAfterPpn,
        sprice2 = sprice2,
        sprice2AfterPpn = sprice2AfterPpn,

        ftPriceAlthBean = ftPriceAlthBean?.let { FtPriceAlthEntity((ftPriceAlthBean.id)) },
        fmaterialBean = fmaterialBean

    )
}
