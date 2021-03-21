package com.erp.distribution.desgreenrestkt.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftsalesdpromotprucb")
class FtSalesdPromoTpruCb : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REFNO_PROMO")
    private val refnoPromo: Long = 0

    @Column(name = "NO_URUT", length = 4)
    private val noUrut = 0

    //	@ManyToOne
    //	@JoinColumns({@JoinColumn(name="ftSalesdRefno", referencedColumnName="refno"),
    //		@JoinColumn(name="ftSalesdId", referencedColumnName="ID"),
    //		@JoinColumn(name="ftSalesdFreegood", referencedColumnName="freeGood")})
    //	@ManyToOne
    //	@JoinColumn(name="ftSalesdFreegood", referencedColumnName="ID")
    //	private FtSalesdItems ftSalesdBean;
    @Column(name = "ftSalesdFreegood", nullable = false)
    private val ftSalesdFreegood: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fPromoBean", referencedColumnName="ID")
    //	private FPromotionRulesh fPromoBean;
    @Column(name = "fPromoBean", nullable = false)
    private val fPromoBean = 0

    @Column(name = "TPRU_CASHBACK")
    private val tpruCashback = 0.0
}