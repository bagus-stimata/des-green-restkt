package com.erp.distribution.desgreenrestkt.domain.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
data class FtSalesdPromoTpruCb (
    private val refnoPromo: Long =0L,

    @Column(name = "NO_URUT", length = 4)
    private val noUrut  :Int =0,

    //	@ManyToOne
    //	@JoinColumns({@JoinColumn(name="ftSalesdRefno", referencedColumnName="refno"),
    //		@JoinColumn(name="ftSalesdId", referencedColumnName="ID"),
    //		@JoinColumn(name="ftSalesdFreegood", referencedColumnName="freeGood")})
    //	@ManyToOne
    //	@JoinColumn(name="ftSalesdFreegood", referencedColumnName="ID")
    //	private FtSalesdItems ftSalesdBean;
    @Column(name = "ftSalesdFreegood", nullable =false)
    private val ftSalesdFreegood: Long =0L,

    //	@ManyToOne
    //	@JoinColumn(name="fPromoBean", referencedColumnName="ID")
    //	private FPromotionRulesh fPromoBean;
    @Column(name = "fPromoBean", nullable =false)
    private val fPromoBean  :Int =0,

    @Column(name = "TPRU_CASHBACK")
    private val tpruCashback  :Double =0.0

): Serializable