package com.erp.distribution.desgreenrestkt.domain.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.persistence.*

@JacksonXmlRootElement
data class FtArPaymentd(
    var id: Long =0L,

    @Column(name =  "NO_URUT" , length = 4)
    var noUrut :Int =0,

    @Column(name =  "CASH_AMOUNTPAY" )
    var cashAmountPay  :Double =0.0,

    @Column(name =  "UANG_MUKA_AMOUNTPAY" )
    var uangMukaAmountPay  :Double =0.0,

    @Column(name =  "RETUR_AMOUNTPAY" )
    var returAmountPay  :Double =0.0,

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

    /*
	 * DIPAKAI NOTE SECARA UMUM DALAM SATU TRANSAKSI
	 */
    @Column(name =  "POTONGAN_NOTES" )
    var potonganNotes :String ="",

    //	@ManyToOne
    //	@JoinColumn(name= ftArPaymenthBean , referencedColumnName= refno )
    //	private FtArPaymenth ftArPaymenthBean;
    @Column(name =  "ftArPaymenthBean" , nullable = false)
    var ftArPaymenthBean: Long =0L,

    /*
	 * Dibayarkan untuk Faktur atau Potong retur
	 */
    //	@ManyToOne
    //	@JoinColumn(name= ftSaleshBean , referencedColumnName= refno )
    //	private FtSalesh ftSaleshBean;
    @Column(name =  "ftSaleshBean" , nullable = false)
    var ftSaleshBean: Long =0L,

    //	@ManyToOne
    //	@JoinColumn(name= returBean , referencedColumnName= refno )
    //	private FtSalesh returBean;
    @Column(name =  "returBean" )
    var returBean: Long =0L,

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
    //	private AccAccount accAccountPotonganBean;
    @Column(name =  "accAccountPotonganBean" )
    var accAccountPotonganBean :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name= fuangMukaBean , referencedColumnName= ID )
    //	private FUangMuka fuangMukaBean; // Hutang(D) pada pada Piutang(K) -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
    @Column(name =  "fuangMukaBean" )
    var fuangMukaBean :Int =0

): Serializable