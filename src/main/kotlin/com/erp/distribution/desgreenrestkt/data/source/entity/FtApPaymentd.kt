package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name = "ftappaymentd")
class FtApPaymentd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long = 0

    @Column(name = "NO_URUT", length = 4)
    var noUrut = 0

    @Column(name = "CASH_AMOUNTPAY")
    var cashAmountPay = 0.0

    @Column(name = "UANG_MUKA_AMOUNTPAY")
    var uangMukaAmountPay = 0.0

    @Column(name = "RETUR_AMOUNTPAY")
    var returAmountPay = 0.0

    @Column(name = "PROMOTION_AMOUNTPAY")
    var promotionAmountPay = 0.0 //Jika ada potongan lain semacam DCV maka langsung saja dimasukkan Potongan

    @Column(name = "GIRO_AMOUNTPAY")
    var giroAmountPay = 0.0

    @Column(name = "TRANSFER_AMOUNTPAY")
    var transferAmountPay = 0.0

    @Column(name = "KELEBIHAN_BAYARAMOUNT")
    var kelebihanBayarAmount = 0.0

    //	@Column(name="SUBTOTAL_PAY")
    //	private Double subtotalPay=0.0;
    @Column(name = "POTONGANAMOUNT")
    var potonganAmount = 0.0

    @Column(name = "POTONGAN_NOTES")
    var potonganNotes = ""

    //	@ManyToOne
    //	@JoinColumn(name="ftApPaymenthBean", referencedColumnName="refno")
    //	private FtApPaymenth ftApPaymenthBean;
    @Column(name = "ftApPaymenthBean", nullable = false)
    var ftApPaymenthBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
    //	private FtPurchaseh ftPurchasehBean;
    @Column(name = "ftPurchasehBean", nullable = false)
    var ftPurchasehBean: Long = 0

    /*
	 * Dibayarkan untuk Faktur atau Potong retur
	 */
    //	@ManyToOne
    //	@JoinColumn(name="returBean", referencedColumnName="refno")
    //	private FtPurchaseh returBean;
    @Column(name = "returBean")
    var returBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftransferBean", referencedColumnName="ID", nullable=true)
    //	private FGiro ftransferBean;
    @Column(name = "ftransferBean")
    var ftransferBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    @Column(name = "fgiroBean")
    var fgiroBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountPotonganBean", referencedColumnName="ID", nullable=true)
    //	private AccAccount accAccountPotonganBean; // Hutang(D) pada Biaya Potongan -> Hutang berkurang di Debet dan Biaya Potongan bertambah di Kredit
    @Column(name = "accAccountPotonganBean")
    var accAccountPotonganBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fpromotionRuleshBean", referencedColumnName="ID")
    //	private FPromotionRulesh fpromotionRuleshBean; // Hutang(D) pada pada Piutang(K) -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
    @Column(name = "fpromotionRuleshBean")
    var fpromotionRuleshBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fuangMukaBean", referencedColumnName="ID")
    //	private FUangMuka fuangMukaBean; // Hutang(D) pada pada Uang Muka -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
    @Column(name = "fuangMukaBean")
    var fuangMukaBean = 0

}