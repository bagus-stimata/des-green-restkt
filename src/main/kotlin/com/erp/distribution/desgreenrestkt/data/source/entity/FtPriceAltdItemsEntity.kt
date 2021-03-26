package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name =  "ftpricealtd_items" )
data class FtPriceAltdItemsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    //	@ManyToOne
    //	@JoinColumn(name= ftPriceAlthBean , referencedColumnName= ID )
    //	private FtPriceAlth  ftPriceAlthBean;
    @Column(name =  "ftPriceAlthBean" , nullable = false)
    var ftPriceAlthBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fmaterialBean , referencedColumnName= ID )
    //	private FMaterial fmaterialBean;
    @Column(name =  "fmaterialBean" , nullable = false)
    var fmaterialBean :Int =0

): Serializable