package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.FtPriceAltdItems
import com.erp.distribution.desgreenrestkt.domain.model.FtPriceAlth
import com.erp.distribution.desgreenrestkt.presentation.model.FtPriceAltdItemsRes
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name =  "ftpricealtd_items" )
data class FtPriceAltdItemsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_detil",
        allocationSize = 60,
        initialValue = 912345668
    )
    @Column(name =  "ID" , length = 9)
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
    var ftPriceAlthBean : FtPriceAlthEntity =  FtPriceAlthEntity(),
//    @Column(name =  "ftPriceAlthBean" , nullable = false)
//    var ftPriceAlthBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fmaterialBean , referencedColumnName= ID )
    //	private FMaterial fmaterialBean;
    @Column(name =  "fmaterialBean" , nullable = false)
    var fmaterialBean :Int =0

): Serializable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FtPriceAltdItemsEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

internal fun FtPriceAltdItemsEntity.toDomain(): FtPriceAltdItems {
    return FtPriceAltdItems(
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

        ftPriceAlthBean = ftPriceAlthBean?.let { FtPriceAlth((ftPriceAlthBean.id)) },
        fmaterialBean = fmaterialBean

    )
}
internal fun FtPriceAltdItems.toResponse(): FtPriceAltdItemsRes {
    return FtPriceAltdItemsRes(
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

        ftPriceAlthBean = ftPriceAlthBean.id,
        fmaterialBean = fmaterialBean

    )
}


