package com.erp.distribution.desgreenrestkt.data.source.entity_acc_cb

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "acc_balance")
class AccBalanceEntity : Serializable {
    /*
	 * ID of Balance
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private val id: Long = 0

    //ACCOUNT: Dibuat syncron dengan peiode buku
    //	@ManyToOne
    //	@JoinColumn(name="accPeriodeBukuBean", referencedColumnName="ID")
    //	private AccPeriodeBuku accPeriodeBukuBean;
    @Column(name = "accPeriodeBukuBean", nullable = false)
    private val accPeriodeBukuBean = 0

    /*
	 * END: ID of Balance
	 */
    //PERIODE
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //	private AccAccount accAccountBean;
    @Column(name = "accAccountBean", nullable = false)
    private val accAccountBean = 0

    /*
	 * Cuma dipakai oleh 1 Account yaitu COGS
	 * dan ada Setiap Hari pada satu periode akuntansi
	 * Tidak visible: sulit untuk mengimplementasikan
	 */
    @Transient
    private val amountMutasiDebit = 0.0

    @Transient
    private val amountMutasiKredit = 0.0

    //SALDO AWAL: Dibuat Syncrond 
    @Column(name = "AMOUNT_BALANCE_AWAL")
    private val amountBalanceAwal = 0.0

    //SALDO AKHIR
    @Column(name = "AMOUNT_BALANCE")
    private val amountBalance = 0.0

    companion object {
        private const val serialVersionUID = 1L
    }
}