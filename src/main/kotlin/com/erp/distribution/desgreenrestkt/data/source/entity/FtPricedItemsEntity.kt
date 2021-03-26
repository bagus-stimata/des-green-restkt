package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name =  "ftpriced_items" )
data class FtPricedItemsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name =  "ID" )
    private val id: Long =0L,

    @Column(name =  "NO_URUT" , length = 5)
    private val noUrut :Int =0,

    @Column(name =  "PPRICE" )
    private val pprice  :Double =0.0,

    @Column(name =  "PPRICE_AFTERPPN" )
    private val ppriceAfterPpn  :Double =0.0,

    @Column(name =  "OLD_PPRICE" )
    private val oldPprice  :Double =0.0,

    @Column(name =  "SPRICE" )
    private val sprice  :Double =0.0,

    @Column(name =  "SPRICE_AFTERPPN" )
    private val spriceAfterPpn  :Double =0.0,

    @Column(name =  "OLD_SPRICE" )
    private val oldSprice  :Double =0.0,

    @Column(name =  "OLD_SPRICE_AFTERPPN" )
    private val oldSpriceAfterPpn  :Double =0.0,

    @Column(name =  "PPRICE2" )
    private val pprice2  :Double =0.0,

    @Column(name =  "PPRICE2_AFTERPPN" )
    private val pprice2AfterPpn  :Double =0.0,

    @Column(name =  "OLD_PPRICE2" )
    private val oldPprice2  :Double =0.0,

    @Column(name =  "SPRICE2" )
    private val sprice2  :Double =0.0,

    @Column(name =  "SPRICE2_AFTERPPN" )
    private val sprice2AfterPpn: Double = 0.0,

    @Column(name =  "OLD_SPRICE2" )
    private val oldSprice2  :Double =0.0,

    @Column(name =  "OLD_SPRICE2_AFTERPPN" )
    private val oldSprice2AfterPpn  :Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name= ftPricehBean , referencedColumnName= refno , insertable=true, updatable=true)
    //	private FtPriceh  ftPricehBean;
    @Column(name =  "ftPricehBean" , nullable = false)
    private val ftPricehBean: Long =0L,

    //	@ManyToOne
    //	@JoinColumn(name= fmaterialBean , referencedColumnName= ID , insertable=true, updatable=true)
    //	private FMaterial fmaterialBean;
    @Column(name =  "fmaterialBean" , nullable = false)
    private val fmaterialBean :Int =0
): Serializable