package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftpricealtd_items")
class FtPriceAltdItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 9)
    var id = 0

    @JsonIgnore
    @Column(name = "NO_URUT", length = 4)
    var noUrut = 0

    @JsonIgnore
    @Column(name = "PPRICE")
    var pprice = 0.0

    @JsonIgnore
    @Column(name = "PPRICE_AFTERPPN")
    var ppriceAfterPpn: Double? = null

    @JsonIgnore
    @Column(name = "PPRICE2")
    var pprice2 = 0.0

    @JsonIgnore
    @Column(name = "PPRICE2_AFTERPPN")
    var pprice2AfterPpn = 0.0

    @Column(name = "SPRICE")
    var sprice = 0.0

    @Column(name = "SPRICE_AFTERPPN")
    var spriceAfterPpn: Double? = null

    @Column(name = "SPRICE2")
    var sprice2 = 0.0

    @Column(name = "SPRICE2_AFTERPPN")
    var sprice2AfterPpn = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID")
    //	private FtPriceAlth  ftPriceAlthBean;
    @Column(name = "ftPriceAlthBean", nullable = false)
    var ftPriceAlthBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    var fmaterialBean = 0

}