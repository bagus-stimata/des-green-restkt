package com.erp.distribution.desgreenrestkt.model_acc_cb

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "accjournald_items")
class AccJournaldItems : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private val id: Long = 0

    @Column(name = "NOURUT", length = 4)
    private val noUrut = 0

    @Column(name = "DESCRIPTION", length = 200)
    private val description = ""

    @Column(name = "AMOUNT_DEBIT")
    private val amountDebit = 0.0

    @Column(name = "AMOUNT_CREDIT")
    private val amountCredit = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="accJournalhBean", referencedColumnName="refno")
    //    private AccJournalh accJournalhBean;
    @Column(name = "accJournalhBean", nullable = false)
    private val accJournalhBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //    private AccAccount accAccountBean;
    @Column(name = "accAccountBean", nullable = false)
    private val accAccountBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID")
    //    private AccCostCenter accCostCenterBean;
    @Column(name = "accCostCenterBean", nullable = false)
    private val accCostCenterBean = 0

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 1L
    }
}