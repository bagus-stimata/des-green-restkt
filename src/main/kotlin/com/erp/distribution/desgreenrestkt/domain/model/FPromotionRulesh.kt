package com.erp.distribution.desgreenrestkt.domain.model

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumPromoDiscFgMethod
import com.erp.distribution.desgreenrestkt.data.source.entity_acc_cb.AccAccount
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.XmlTransient
import kotlin.collections.HashSet

@JacksonXmlRootElement
data class FPromotionRulesh (
    //** End Tools
    var id :Int =0,

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2.
	 */
    @Column(name ="SOURCE_ID")
    var sourceId:Int =0,

    @Column(name ="KODE1" ,  length = 15)
    var kode1  :String ="",

    @Column(name ="KODE2" ,  length = 35)
    var kode2  :String ="",

    @Column(name ="DESCRIPTION" ,  length = 300)
    var description :String ="",

    @ManyToOne
    @JoinColumn(name ="fdivisionBean" ,  referencedColumnName ="ID")
    var fdivisionBean: FDivision? = FDivision(),

    @Temporal(TemporalType.DATE)
    @Column(name ="VALID_PERIODE_PERIODE_FROM")
    var validPeriodDateFrom :Date =Date(),

    @Temporal(TemporalType.DATE)
    @Column(name ="VALID_PERIODE_PERIODE_TO")
    var validPeriodDateTo :Date =Date(),

    @Column(name ="SHARED_TO_COMPANY")
    var sharedToCompany  :Boolean =false, //setting Shared to company khusus ditaruh disini, bukan di divisi

    @Column(name ="STATUS_AKTIF")
    var statusActive  :Boolean =true,

    //PEMBAYARAN
    @XmlTransient
    @OneToMany(mappedBy ="fpromotionRuleshBean" ,  fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var ftApPaymentdEntitySet : Set<FtApPaymentd> = HashSet<FtApPaymentd>(),

    //	@XmlTransient
    //	@OneToMany(mappedBy= :Int =0,fpromotionRuleshBean ,  fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    //	@Fetch(FetchMode.JOIN)
    //	private Set<FPromotionRulesdPayments> fpromotionRulesdPaymentSet; //Dibayar menggunakan Transfer
    /*
	 * AKAN DITAMBAHKAN
	 * di freegood ditambahkan jumlah case maximal
	 */
    //	FPromotionBudget fpromotionBudget;
    //	AccAccount accAccountBean;
    /*
	 * Account Mapping
	 * Account Biaya Diskon jika: Yang akan terbit saat akan membuat jurnal saat transaksi penjualan
	 */
    @ManyToOne
    @JoinColumn(name ="accAccountBean" ,  referencedColumnName ="ID")
    var accAccountBean : AccAccount? = AccAccount(),

    @ManyToOne
    @JoinColumn(name ="accAccount_CreditBean" ,  referencedColumnName ="ID")
    var accAccount_CreditBean : AccAccount? = AccAccount(),

    @Column(name ="CLAIM_PABRIK")
    var claimPabrik  :Boolean =false,

    //	@Column(name= :Int =0,GANTI_BARANG)
    //	private boolean gantiBarang=false;
    //	@ManyToOne
    //	@JoinColumn(name= :Int =0,accAccountDebitBean ,  referencedColumnName= :Int =0,ID)
    //	private AccAccount accAccountDebitBean; //Acount Debit jika Claimable ke Pabrik
    @Column(name ="TARGET_VALUE")
    var maxTargetValue  :Double =0.0, // 0 (nol) berarti tidak ada target sampai behenti

    @Column(name ="TARGET_QTY")
    var maxTargetQty :Int =0, // 0 (nol) berarti tidak ada target sampai berhenti

    @Column(name ="TOTAL_VALUE_APPLIED")
    var totalValueApplied  :Double =0.0,

    @Column(name ="TOTAL_QTY_APPLIED")
    var totalQtyApplied :Int =0,

    @Transient
    var totalQtyAppliedUom1234  :Int =0,

    @Column(name ="AMOUNT_PAY_RP")
    var amountPayRp  :Double =0.0, //Menghilangkan DCV

    /*
	 * Valid For Customer
	 */
//    @XmlTransient
//    @OneToMany(mappedBy ="fpromotionRuleshBean" ,  fetch = FetchType.LAZY)
    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fpromotionRulesdValidCustsEntitySet: Set<FPromotionRulesdValidCusts>? = HashSet<FPromotionRulesdValidCusts>(),

    /*
	 * VALID PRODUCT
	 */
    @Column(name ="VALID_PRODUCTS_ACCUMULATION")
    var validProductsAccumulation  :Boolean =false,

//    @XmlTransient
//    @OneToMany(mappedBy ="fpromotionRuleshBean" ,  fetch = FetchType.LAZY)
    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fpromotionRulesdValidProductsEntitySet: Set<FPromotionRulesdValidProducts> = HashSet<FPromotionRulesdValidProducts>(),

    @XmlTransient
    @OneToMany(mappedBy ="fpromoBean" ,  fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var ftSalesdPromoTprbEntitySet: Set<FtSalesdPromoTprb>? = HashSet<FtSalesdPromoTprb>(),

    @XmlTransient
    @OneToMany(mappedBy ="fPromoBean" ,  fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var ftSalesdPromoTpruDiscEntitySet: Set<FtSalesdPromoTpruDisc>? = HashSet<FtSalesdPromoTpruDisc>(),

    /*
	 * DISCOUNT METHOD: What Customers Gets
	 */
    @Column(name ="PROMO_DISC_METHOD" ,  length = 5)
    @Enumerated(EnumType.STRING)
    var promoDiscMethod: EnumPromoDiscFgMethod? = EnumPromoDiscFgMethod.UOM1,

    /*
	 * Minimum Pengambilan
	 */
    @Column(name ="DISC_MIN_QTY_OR_VALUE" ,  length = 9)
    var discMinQtyOrValue :Int =0,

    @Column(name ="DISC_MAX_QTY_OR_VALUE" ,  length = 9)
    var discMaxQtyOrValue :Int =0,

    /*
	 * KALAU DISC KAN PASTI KELIPATAN YA?
	 * INI BERARTI TIDAK PERLU:
	 * PERLU DIEVALUASI LAGI
	 */
    @Column(name ="DISC_KELIPATAN")
    var discKelipatan  :Boolean =true,

    @Column(name ="DISC_CASH_ONLY")
    var discCashOnly  :Boolean =false,

    @Column(name ="FOR_DISC_QTY_OR_VALUE_LEV1")
    var forDiscQtyOrValueLev1  :Double =0.0,

    @Column(name ="DISC1_GET_LEV1")
    var disc1GetLev1  :Double =0.0,

    @Column(name ="DISC1_GET_LEV1_VALUE") //PER KARTON/biggest
    var disc1GetLev1_Value  :Double =0.0,

    @Column(name ="DISC2_GET_LEV1")
    var disc2GetLev1  :Double =0.0,

    @Column(name ="DISC3_GET_LEV1")
    var disc3GetLev1  :Double =0.0,

    @Column(name ="DISC1PLUS_GET_LEV1")
    var disc1PlusGetLev1  :Double =0.0,

    @Column(name ="DISC2PLUS_GET_LEV1")
    var disc2PlusGetLev1  :Double =0.0,

    @Column(name ="FOR_DISC_QTY_OR_VALUE_LEV2")
    var forDiscQtyOrValueLev2  :Double =0.0,

    @Column(name ="DISC1_GET_LEV2")
    var disc1GetLev2  :Double =0.0,

    @Column(name ="DISC1_GET_LEV2_VALUE") //PER KARTON/biggest
    var disc1GetLev2_Value  :Double =0.0,

    @Column(name ="DISC2_GET_LEV2")
    var disc2GetLev2  :Double =0.0,

    @Column(name ="DISC3_GET_LEV2")
    var disc3GetLev2  :Double =0.0,

    @Column(name ="DISC1PLUS_GET_LEV2")
    var disc1PlusGetLev2  :Double =0.0,

    @Column(name ="DISC2PLUS_GET_LEV2")
    var disc2PlusGetLev2  :Double =0.0,

    @Column(name ="FOR_DISC_QTY_OR_VALUE_LEV3")
    var forDiscQtyOrValueLev3  :Double =0.0,

    @Column(name ="DISC1_GET_LEV3")
    var disc1GetLev3  :Double =0.0,

    @Column(name ="DISC1_GET_LEV3_VALUE") //PER KARTON/biggest
    var disc1GetLev3_Value  :Double =0.0,

    @Column(name ="DISC2_GET_LEV3")
    var disc2GetLev3  :Double =0.0,

    @Column(name ="DISC3_GET_LEV3")
    var disc3GetLev3  :Double =0.0,

    @Column(name ="DISC1PLUS_GET_LEV3")
    var disc1PlusGetLev3  :Double =0.0,

    @Column(name ="DISC2PLUS_GET_LEV3")
    var disc2PlusGetLev3  :Double =0.0,

    @Column(name ="FOR_DISC_QTY_OR_VALUE_LEV4")
    var forDiscQtyOrValueLev4  :Double =0.0,

    @Column(name ="DISC1_GET_LEV4")
    var disc1GetLev4  :Double =0.0,

    @Column(name ="DISC1_GET_LEV4_VALUE") //PER KARTON/biggest
    var disc1GetLev4_Value  :Double =0.0,

    @Column(name ="DISC2_GET_LEV4")
    var disc2GetLev4  :Double =0.0,

    @Column(name ="DISC3_GET_LEV4")
    var disc3GetLev4  :Double =0.0,

    @Column(name ="DISC1PLUS_GET_LEV4")
    var disc1PlusGetLev4  :Double =0.0,

    @Column(name ="DISC2PLUS_GET_LEV4")
    var disc2PlusGetLev4  :Double =0.0,

    @Column(name ="FOR_DISC_QTY_OR_VALUE_LEV5")
    var forDiscQtyOrValueLev5  :Double =0.0,

    @Column(name ="DISC1_GET_LEV5")
    var disc1GetLev5  :Double =0.0,

    @Column(name ="DISC1_GET_LEV5_VALUE") //PER KARTON/biggest
    var disc1GetLev5_Value  :Double =0.0,

    @Column(name ="DISC2_GET_LEV5")
    var disc2GetLev5  :Double =0.0,

    @Column(name ="DISC3_GET_LEV5")
    var disc3GetLev5  :Double =0.0,

    @Column(name ="DISC1PLUS_GET_LEV5")
    var disc1PlusGetLev5  :Double =0.0,

    @Column(name ="DISC2PLUS_GET_LEV5")
    var disc2PlusGetLev5  :Double =0.0,

    /*
	 * Kadang dalam suatu promo mendapat 2 barang
	 * FREE GOOD 1 METHOD: What Customers Gets
	 */
    @Column(name ="PROMO_FG1_METHOD" ,  length = 5)
    @Enumerated(EnumType.STRING)
    var promoFg1Method: EnumPromoDiscFgMethod? = EnumPromoDiscFgMethod.UOM1,

    /*
	 * Minimum Pengambilan
	 */
    @Column(name ="FG1_MIN_QTY_OR_VALUE")
    var fg1MinQtyOrValue :Int =0,

    @Column(name ="FG1_KELIPATAN")
    var fg1Kelipatan  :Boolean =true,

    @ManyToOne
    @JoinColumn(name ="freeGood1MaterialBean" ,  referencedColumnName ="ID")
    var freeGood1MaterialEntityBean: FMaterial? = FMaterial(),

    @Column(name ="FG1_HARGA_JUAL_NOL")
    var fg1HargaJualNol  :Boolean =true,

    /*
	 * Dipakai untuk perhitungan berapa nominal rupiah
	 *
	 */
    @Column(name ="FG1_PRICE_PCS")
    var fg1PricePcs  :Double =0.0,

    @Column(name ="FOR_FG1_QTY_OR_VALUE_LEV1" )
    var forFg1QtyOrValueLev1 :Int =0,

    @Column(name ="FG1_QTY_GET_LEV1" )
    var fg1QtyGetLev1 :Int =0,

    @Column(name ="FOR_FG1_QTY_OR_VALUE_LEV2")
    var forFg1QtyOrValueLev2 :Int =0,

    @Column(name ="FG1_QTY_GET_LEV2")
    var fg1QtyGetLev2 :Int =0,

    @Column(name ="FOR_FG1_QTY_OR_VALUE_LEV3")
    var forFg1QtyOrValueLev3 :Int =0,

    @Column(name ="FG1_QTY_GET_LEV3")
    var fg1QtyGetLev3 :Int =0,

    @Column(name ="FOR_FG1_QTY_OR_VALUE_LEV4")
    var forFg1QtyOrValueLev4 :Int =0,

    @Column(name ="FG1_QTY_GET_LEV4")
    var fg1QtyGetLev4 :Int =0,

    @Column(name ="FOR_FG1_QTY_OR_VALUE_LEV5")
    var forFg1QtyOrValueLev5 :Int =0,

    @Column(name ="FG1_QTY_GET_LEV5")
    var fg1QtyGetLev5 :Int =0,

    /*
	 * Kadang dalam suatu promo mendapat 2 barang
	 * FREE GOOD 2 METHOD: What Customers Gets
	 */
    @Column(name ="PROMO_FG2_METHOD" ,  length = 5)
    @Enumerated(EnumType.STRING)
    var promoFg2Method: EnumPromoDiscFgMethod? = EnumPromoDiscFgMethod.UOM1,

    /*
	 * Minimum Pengambilan
	 */
    @Column(name ="FG2_MIN_QTY_OR_VALUE")
    var fg2MinQtyOrValue :Int =0,

    @Column(name ="FG2_KELIPATAN")
    var fg2Kelipatan  :Boolean =true,

    @ManyToOne
    @JoinColumn(name ="freeGood2MaterialBean" ,  referencedColumnName ="ID")
    var freeGood2MaterialEntityBean: FMaterial? = FMaterial(),

    @Column(name ="FG2_HARGA_JUAL_NOL")
    var fg2HargaJualNol  :Boolean =true,

    /*
	 * Dipakai untuk perhitungan berapa nominal rupiah
	 *
	 */
    @Column(name ="FG2_PRICE_PCS")
    var fg2PricePcs  :Double =0.0,

    @Column(name ="FOR_FG2_QTY_OR_VALUE_LEV1" ,  length = 8)
    var forFg2QtyOrValueLev1 :Int =0,

    @Column(name ="FG2_QTY_GET_LEV1" ,  length = 8)
    var fg2QtyGetLev1 :Int =0,

    @Column(name ="FOR_FG2_QTY_OR_VALUE_LEV2" ,  length = 8)
    var forFg2QtyOrValueLev2 :Int =0,

    @Column(name ="FG2_QTY_GET_LEV2" ,  length = 8)
    var fg2QtyGetLev2 :Int =0,

    @Column(name ="FOR_FG2_QTY_OR_VALUE_LEV3" ,  length = 8)
    var forFg2QtyOrValueLev3 :Int =0,

    @Column(name ="FG2_QTY_GET_LEV3" ,  length = 8)
    var fg2QtyGetLev3 :Int =0,

    @Column(name ="FOR_FG2_QTY_OR_VALUE_LEV4" ,  length = 8)
    var forFg2QtyOrValueLev4 :Int =0,

    @Column(name ="FG2_QTY_GET_LEV4" ,  length = 8)
    var fg2QtyGetLev4 :Int =0,

    @Column(name ="FOR_FG2_QTY_OR_VALUE_LEV5" ,  length = 8)
    var forFg2QtyOrValueLev5 :Int =0,

    @Column(name ="FG2_QTY_GET_LEV5" ,  length = 8)
    var fg2QtyGetLev5 :Int =0,

    //CASHBACK
    @Column(name ="CASH_BACKVALUE1")
    var cashBackValue1  :Double =0.0,

    @Column(name ="CASH_BACKGET1")
    var cashBackGet1  :Double =0.0,

    @Column(name ="CASH_BACKVALUE2")
    var cashBackValue2  :Double =0.0,

    @Column(name ="CASH_BACKGET2")
    var cashBackGet2  :Double =0.0,

    @Column(name ="CASH_BACKVALUE3")
    var cashBackValue3  :Double =0.0,

    @Column(name ="CASH_BACKGET3")
    var cashBackGet3  :Double =0.0,

    @Column(name ="CASH_BACKVALUE4")
    var cashBackValue4  :Double =0.0,

    @Column(name ="CASH_BACKGET4")
    var cashBackGet4  :Double =0.0,

    //	@OneToMany(mappedBy= :Int =0,fPromoBean ,  fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    //	@Fetch(FetchMode.JOIN)
    //	private Set<FtDcvh> ftDcvSet;
    //LOG
    @Column(name ="CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created :Date =Date(),

    @Column(name ="MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified :Date =Date(),

    @Column(name ="MODIFIED_BY" ,  length = 20)
    var modifiedBy  :String ="" //User ID

    //** Tools: Jangan dihapus
//    var isPersisted: Boolean
//        get() = id !:Int =0,
//


): Serializable