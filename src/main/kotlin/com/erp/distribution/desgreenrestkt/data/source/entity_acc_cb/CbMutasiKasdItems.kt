package com.erp.distribution.desgreenrestkt.data.source.entity_acc_cb

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "cb_mutasikasd_items")
class CbMutasiKasdItems : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private val id: Long = 0

    @Column(name = "NOURUT", length = 4)
    private val noUrut = 0

    @Column(name = "DESCRIPTION", length = 200)
    private val description = ""

    //	@ManyToOne
    //	@JoinColumn(name="cbMutasiKashBean", referencedColumnName="refno", nullable=false)
    //    private CbMutasiKash cbMutasiKashBean;
    @Column(name = "cbMutasiKashBean", nullable = false)
    private val cbMutasiKashBean: Long = 0

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

    @Column(name = "AMOUNT")
    private val amount = 0.0

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 1L
    }
}