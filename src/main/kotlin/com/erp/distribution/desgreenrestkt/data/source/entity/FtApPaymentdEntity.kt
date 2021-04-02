package com.erp.distribution.desgreenrestkt.data.source.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
@Entity
@Table(name =  "ftappaymentd" )
data class FtApPaymentdEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_detil",
        allocationSize = 20,
        initialValue = 912345668
    )
    @Column(name =  "ID" )
    var id: Long =0L,

    @Column(name =  "NO_URUT" , length = 4)
    var noUrut :Int =0,

    @Column(name =  "CASH_AMOUNTPAY" )
    var cashAmountPay  :Double =0.0,

    @Column(name =  "UANG_MUKA_AMOUNTPAY" )
    var uangMukaAmountPay  :Double =0.0,

    @Column(name =  "RETUR_AMOUNTPAY" )
    var returAmountPay  :Double =0.0,

    @Column(name =  "PROMOTION_AMOUNTPAY" )
    var promotionAmountPay  :Double =0.0, //Jika ada potongan lain semacam DCV maka langsung saja dimasukkan Potongan

    @Column(name =  "GIRO_AMOUNTPAY" )
    var giroAmountPay  :Double =0.0,

    @Column(name =  "TRANSFER_AMOUNTPAY" )
    var transferAmountPay  :Double =0.0,

    @Column(name =  "KELEBIHAN_BAYARAMOUNT" )
    var kelebihanBayarAmount  :Double =0.0,

    //	@Column(name= SUBTOTAL_PAY )
    //	private Double subtotalPay=0.0;
    @Column(name =  "POTONGANAMOUNT" )
    var potonganAmount  :Double =0.0,

    @Column(name =  "POTONGAN_NOTES" )
    var potonganNotes :String ="",

    //	@ManyToOne
    //	@JoinColumn(name= ftApPaymenthBean , referencedColumnName= refno )
    //	private FtApPaymenth ftApPaymenthBean;
    @Column(name =  "ftApPaymenthBean" , nullable = false)
    var ftApPaymenthBean: Long =0,

    //	@ManyToOne
    //	@JoinColumn(name= ftPurchasehBean , referencedColumnName= refno )
    //	private FtPurchaseh ftPurchasehBean;
    @Column(name =  "ftPurchasehBean" , nullable = false)
    var ftPurchasehBean: Long =0,

    /*
	 * Dibayarkan untuk Faktur atau Potong retur
	 */
    //	@ManyToOne
    //	@JoinColumn(name= returBean , referencedColumnName= refno )
    //	private FtPurchaseh returBean;
    @Column(name =  "returBean" )
    var returBean: Long =0,

    //	@ManyToOne
    //	@JoinColumn(name= ftransferBean , referencedColumnName= ID , nullable=true)
    //	private FGiro ftransferBean;
    @Column(name =  "ftransferBean" )
    var ftransferBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fgiroBean , referencedColumnName= ID , nullable=true)
    //	private FGiro fgiroBean;
    @Column(name =  "fgiroBean" )
    var fgiroBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= accAccountPotonganBean , referencedColumnName= ID , nullable=true)
    //	private AccAccount accAccountPotonganBean; // Hutang(D) pada Biaya Potongan -> Hutang berkurang di Debet dan Biaya Potongan bertambah di Kredit
    @Column(name =  "accAccountPotonganBean" )
    var accAccountPotonganBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fpromotionRuleshBean , referencedColumnName= ID )
    //	private FPromotionRulesh fpromotionRuleshBean; // Hutang(D) pada pada Piutang(K) -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
    @Column(name =  "fpromotionRuleshBean" )
    var fpromotionRuleshBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fuangMukaBean , referencedColumnName= ID )
    //	private FUangMuka fuangMukaBean; // Hutang(D) pada pada Uang Muka -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
    @Column(name =  "fuangMukaBean" )
    var fuangMukaBean :Int =0

): Serializable