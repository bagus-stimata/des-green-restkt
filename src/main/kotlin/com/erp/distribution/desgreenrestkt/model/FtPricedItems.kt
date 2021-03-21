package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftpriced_items")
class FtPricedItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private val id: Long = 0

    @Column(name = "NO_URUT", length = 5)
    private val noUrut = 0

    @Column(name = "PPRICE")
    private val pprice = 0.0

    @Column(name = "PPRICE_AFTERPPN")
    private val ppriceAfterPpn = 0.0

    @Column(name = "OLD_PPRICE")
    private val oldPprice = 0.0

    @Column(name = "SPRICE")
    private val sprice = 0.0

    @Column(name = "SPRICE_AFTERPPN")
    private val spriceAfterPpn = 0.0

    @Column(name = "OLD_SPRICE")
    private val oldSprice = 0.0

    @Column(name = "OLD_SPRICE_AFTERPPN")
    private val oldSpriceAfterPpn = 0.0

    @Column(name = "PPRICE2")
    private val pprice2 = 0.0

    @Column(name = "PPRICE2_AFTERPPN")
    private val pprice2AfterPpn = 0.0

    @Column(name = "OLD_PPRICE2")
    private val oldPprice2 = 0.0

    @Column(name = "SPRICE2")
    private val sprice2 = 0.0

    @Column(name = "SPRICE2_AFTERPPN")
    private val sprice2AfterPpn: Double? = null

    @Column(name = "OLD_SPRICE2")
    private val oldSprice2 = 0.0

    @Column(name = "OLD_SPRICE2_AFTERPPN")
    private val oldSprice2AfterPpn = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftPricehBean", referencedColumnName="refno", insertable=true, updatable=true)
    //	private FtPriceh  ftPricehBean;
    @Column(name = "ftPricehBean", nullable = false)
    private val ftPricehBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID", insertable=true, updatable=true)
    //	private FMaterial fmaterialBean;
    @Column(name = "fmaterialBean", nullable = false)
    private val fmaterialBean = 0
}