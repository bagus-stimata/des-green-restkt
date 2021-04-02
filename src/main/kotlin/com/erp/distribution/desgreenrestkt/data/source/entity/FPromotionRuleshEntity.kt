package com.erp.distribution.desgreenrestkt.data.source.entity

import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumPromoDiscFgMethod
import com.erp.distribution.desgreenrestkt.data.source.entity_acc_cb.AccAccountEntity
import com.erp.distribution.desgreenrestkt.domain.model.FDivision
import com.erp.distribution.desgreenrestkt.domain.model.FPromotionRulesh
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.XmlTransient
import kotlin.collections.HashSet

@JacksonXmlRootElement
@Entity
@Table(name ="fpromotion_rulesh")
data class FPromotionRuleshEntity (
    //** End Tools
    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator="hibernate_sequence")
    @SequenceGenerator(
        name = "hibernate_sequence",
        sequenceName = "hibernate_sequence_header",
        allocationSize = 20,
        initialValue = 912345668
    )
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
    var fdivisionBean: FDivisionEntity = FDivisionEntity(),

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
    var ftApPaymentdEntitySet : Set<FtApPaymentdEntity> = HashSet<FtApPaymentdEntity>(),

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
//    @ManyToOne
//    @JoinColumn(name ="accAccountBean" ,  referencedColumnName ="ID")
//    var accAccountEntityBean : AccAccountEntity? = AccAccountEntity(),
    var accAccountEntityBean : Int? = 0,


//    @ManyToOne
//    @JoinColumn(name ="accAccount_CreditBean" ,  referencedColumnName ="ID")
//    var accAccount_Entity_CreditBean : AccAccountEntity? = AccAccountEntity(),
    var accAccount_CreditBean : Int? = 0,

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
    var fpromotionRulesdValidCustsEntitySet: Set<FPromotionRulesdValidCustsEntity>? = HashSet<FPromotionRulesdValidCustsEntity>(),

    /*
	 * VALID PRODUCT
	 */
    @Column(name ="VALID_PRODUCTS_ACCUMULATION")
    var validProductsAccumulation  :Boolean =false,

//    @XmlTransient
//    @OneToMany(mappedBy ="fpromotionRuleshBean" ,  fetch = FetchType.LAZY)
    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fpromotionRulesdValidProductsEntitySet: Set<FPromotionRulesdValidProductsEntity> = HashSet<FPromotionRulesdValidProductsEntity>(),

    @XmlTransient
    @OneToMany(mappedBy ="fpromoBean" ,  fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var ftSalesdPromoTprbEntitySet: Set<FtSalesdPromoTprbEntity>? = HashSet<FtSalesdPromoTprbEntity>(),

    @XmlTransient
    @OneToMany(mappedBy ="fPromoBean" ,  fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var ftSalesdPromoTpruDiscEntitySet: Set<FtSalesdPromoTpruDiscEntity>? = HashSet<FtSalesdPromoTpruDiscEntity>(),

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

//    @ManyToOne
//    @JoinColumn(name ="freeGood1MaterialBean" ,  referencedColumnName ="ID")
//    var freeGood1MaterialEntityBean: FMaterialEntity? = FMaterialEntity(),
    var freeGood1MaterialBean: Int? = 0,

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

//    @ManyToOne
//    @JoinColumn(name ="freeGood2MaterialBean" ,  referencedColumnName ="ID")
//    var freeGood2MaterialEntityBean: FMaterialEntity? = FMaterialEntity(),
    var freeGood2MaterialBean: Int? = 0,

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


internal fun FPromotionRuleshEntity.toDomain(): FPromotionRulesh {
    return FPromotionRulesh(
        id = id,
        sourceId = sourceId,

        kode1 = kode1,
        kode2 = kode2,
        description = description,
        fdivisionBean = FDivision(fdivisionBean.id),
        statusActive = statusActive,
        validPeriodDateFrom = validPeriodDateFrom,
        validPeriodDateTo = validPeriodDateTo,
        sharedToCompany = sharedToCompany,

//        ftApPaymentdEntitySet = ftApPaymentdEntitySet,
        accAccountEntityBean = accAccountEntityBean,
        accAccount_CreditBean = accAccount_CreditBean,
        claimPabrik = claimPabrik,
        maxTargetValue = maxTargetValue,
        maxTargetQty = maxTargetQty,
        totalValueApplied = totalValueApplied,
        totalQtyApplied = totalQtyApplied,
        totalQtyAppliedUom1234 = totalQtyAppliedUom1234,
        amountPayRp = amountPayRp,
//        fpromotionRulesdValidCustsEntitySet = fpromotionRulesdValidCustsEntitySet,

        validProductsAccumulation = validProductsAccumulation,
//        fpromotionRulesdValidProductsEntitySet = fpromotionRulesdValidProductsEntitySet,
//        ftSalesdPromoTprbEntitySet = ftSalesdPromoTprbEntitySet,
//        ftSalesdPromoTpruDiscEntitySet = ftSalesdPromoTpruDiscEntitySet,

        promoDiscMethod = promoDiscMethod,
        discMinQtyOrValue = discMinQtyOrValue,
        discMaxQtyOrValue = discMaxQtyOrValue,

        discKelipatan = discKelipatan,
        discCashOnly = discCashOnly,
        forDiscQtyOrValueLev1 = forDiscQtyOrValueLev1,
        disc1GetLev1 = disc1GetLev1,
        disc1GetLev1_Value = disc1GetLev1_Value,
        disc2GetLev1 = disc2GetLev1,
        disc3GetLev1 = disc3GetLev1,
        disc1PlusGetLev1 = disc1PlusGetLev1,
        disc2PlusGetLev1 = disc2PlusGetLev1,
        forDiscQtyOrValueLev2 = forDiscQtyOrValueLev2,
        disc1GetLev2 = disc1GetLev2,
        disc1GetLev2_Value = disc1GetLev2_Value,
        disc2GetLev2 = disc2GetLev2,
        disc3GetLev2 = disc3GetLev2,
        disc1PlusGetLev2 = disc1PlusGetLev2,
        disc2PlusGetLev2 = disc2PlusGetLev2,
        forDiscQtyOrValueLev3 = forDiscQtyOrValueLev3,
        disc1GetLev3 = disc1GetLev3,
        disc1GetLev3_Value = disc1GetLev3_Value,
        disc2GetLev3 = disc2GetLev3,
        disc3GetLev3 = disc3GetLev3,
        disc1PlusGetLev3 = disc1PlusGetLev3,
        disc2PlusGetLev3 = disc2PlusGetLev3,
        forDiscQtyOrValueLev4 = forDiscQtyOrValueLev4,
        disc1GetLev4 = disc1GetLev4,
        disc1GetLev4_Value = disc1GetLev4_Value,
        disc2GetLev4 = disc2GetLev4,
        disc3GetLev4 = disc3GetLev4,
        disc1PlusGetLev4 = disc1PlusGetLev4,
        disc2PlusGetLev4 = disc2PlusGetLev4,
        forDiscQtyOrValueLev5 = forDiscQtyOrValueLev5,
        disc1GetLev5 = disc1GetLev5,
        disc1GetLev5_Value = disc1GetLev5_Value,
        disc2GetLev5 = disc2GetLev5,
        disc3GetLev5 = disc3GetLev5,
        disc1PlusGetLev5 = disc1PlusGetLev5,
        disc2PlusGetLev5 = disc2PlusGetLev5,
        promoFg1Method = promoFg1Method,
        fg1MinQtyOrValue = fg1MinQtyOrValue,
        fg1Kelipatan = fg1Kelipatan,
        freeGood1MaterialBean = freeGood1MaterialBean,
        fg1HargaJualNol = fg1HargaJualNol,
        fg1PricePcs = fg1PricePcs,
        forFg1QtyOrValueLev1 = forFg1QtyOrValueLev1,
        fg1QtyGetLev1 = fg1QtyGetLev1,
        forFg1QtyOrValueLev2 = forFg1QtyOrValueLev2,
        fg1QtyGetLev2 = fg1QtyGetLev2,
        forFg1QtyOrValueLev3 = forFg1QtyOrValueLev3,
        fg1QtyGetLev3 = fg1QtyGetLev3,
        forFg1QtyOrValueLev4 = forFg1QtyOrValueLev4,
        fg1QtyGetLev4 = fg1QtyGetLev4,
        forFg1QtyOrValueLev5 = forFg1QtyOrValueLev5,
        fg1QtyGetLev5 = fg1QtyGetLev5,

        promoFg2Method = promoFg2Method,
        fg2MinQtyOrValue = fg2MinQtyOrValue,
        fg2Kelipatan = fg2Kelipatan,
        freeGood2MaterialBean = freeGood2MaterialBean,
        fg2HargaJualNol = fg2HargaJualNol,
        fg2PricePcs = fg2PricePcs,
        forFg2QtyOrValueLev1 = forFg2QtyOrValueLev1,
        fg2QtyGetLev1 = fg2QtyGetLev1,
        forFg2QtyOrValueLev2 = forFg2QtyOrValueLev2,
        fg2QtyGetLev2 = fg2QtyGetLev2,
        forFg2QtyOrValueLev3 = forFg2QtyOrValueLev3,
        fg2QtyGetLev3 = fg2QtyGetLev3,
        forFg2QtyOrValueLev4 = forFg2QtyOrValueLev4,
        fg2QtyGetLev4 = fg2QtyGetLev4,
        forFg2QtyOrValueLev5 = forFg2QtyOrValueLev5,
        fg2QtyGetLev5 = fg2QtyGetLev5,
        cashBackValue1 = cashBackValue1,
        cashBackGet1 = cashBackGet1,
        cashBackValue2 = cashBackValue2,
        cashBackGet2 = cashBackGet2,
        cashBackValue3 = cashBackValue3,
        cashBackGet3 = cashBackGet3,
        cashBackValue4 = cashBackValue4,
        cashBackGet4 = cashBackGet4,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy
    )
}
