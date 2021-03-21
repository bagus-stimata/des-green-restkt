package com.erp.distribution.desgreenrestkt.model

import com.erp.distribution.desgreenrestkt.domain.model.modelenum.EnumPromoDiscFgMethod
import com.erp.distribution.desgreenrestkt.model_acc_cb.AccAccount
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.XmlTransient

@JacksonXmlRootElement
@Entity
@Table(name = "fpromotion_rulesh")
class FPromotionRulesh {
    //** End Tools
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2.
	 */
    @Column(name = "SOURCE_ID")
    var sourceID = 0

    @Column(name = "KODE1", length = 15)
    var kode1 = ""

    @Column(name = "KODE2", length = 35)
    var kode2 = ""

    @Column(name = "DESCRIPTION", length = 300)
    var description = ""

    @ManyToOne
    @JoinColumn(name = "fdivisionBean", referencedColumnName = "ID")
    var fdivisionBean: FDivision? = null

    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_PERIODE_PERIODE_FROM")
    var validPeriodDateFrom = Date()

    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_PERIODE_PERIODE_TO")
    var validPeriodDateTo = Date()

    @Column(name = "SHARED_TO_COMPANY")
    var isSharedToCompany = false //setting Shared to company khusus ditaruh disini, bukan di divisi

    @Column(name = "STATUS_AKTIF")
    var isStatusActive = true

    //PEMBAYARAN
    @XmlTransient
    @OneToMany(mappedBy = "fpromotionRuleshBean", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var ftApPaymentdSet // Dibayar Potong Nota
            : Set<FtApPaymentd>? = null

    //	@XmlTransient
    //	@OneToMany(mappedBy="fpromotionRuleshBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
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
    @JoinColumn(name = "accAccountBean", referencedColumnName = "ID")
    var accAccountBean //DEBIT
            : AccAccount? = null

    @ManyToOne
    @JoinColumn(name = "accAccount_CreditBean", referencedColumnName = "ID")
    var accAccount_CreditBean //CREDIT
            : AccAccount? = null

    @Column(name = "CLAIM_PABRIK")
    var isClaimPabrik = false

    //	@Column(name="GANTI_BARANG")
    //	private boolean gantiBarang=false;
    //	@ManyToOne
    //	@JoinColumn(name="accAccountDebitBean", referencedColumnName="ID")
    //	private AccAccount accAccountDebitBean; //Acount Debit jika Claimable ke Pabrik
    @Column(name = "TARGET_VALUE")
    var maxTargetValue = 0.0 // 0 (nol) berarti tidak ada target sampai behenti

    @Column(name = "TARGET_QTY", length = 9)
    var maxTargetQty = 0 // 0 (nol) berarti tidak ada target sampai berhenti

    @Column(name = "TOTAL_VALUE_APPLIED")
    var totalValueApplied = 0.0

    @Column(name = "TOTAL_QTY_APPLIED", length = 9)
    var totalQtyApplied = 0

    @Transient
    var totalQtyAppliedUom1234 = ""

    @Column(name = "AMOUNT_PAY_RP")
    var amountPayRp = 0.0 //Menghilangkan DCV

    /*
	 * Valid For Customer
	 */
    @XmlTransient
    @OneToMany(mappedBy = "fpromotionRuleshBean", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fpromotionRulesdValidCustsSet: Set<FPromotionRulesdValidCusts>? = null

    /*
	 * VALID PRODUCT
	 */
    @Column(name = "VALID_PRODUCTS_ACCUMULATION")
    var isValidProductsAccumulation = false

    @XmlTransient
    @OneToMany(mappedBy = "fpromotionRuleshBean", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var fpromotionRulesdValidProductsSet: Set<FPromotionRulesdValidProducts>? = null

    @XmlTransient
    @OneToMany(mappedBy = "fpromoBean", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var ftSalesdPromoTprbSet: Set<FtSalesdPromoTprb>? = null

    @XmlTransient
    @OneToMany(mappedBy = "fPromoBean", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @Fetch(FetchMode.JOIN)
    var ftSalesdPromoTpruDiscSet: Set<FtSalesdPromoTpruDisc>? = null

    /*
	 * DISCOUNT METHOD: What Customers Gets
	 */
    @Column(name = "PROMO_DISC_METHOD", length = 5)
    @Enumerated(EnumType.STRING)
    var promoDiscMethod: EnumPromoDiscFgMethod? = null

    /*
	 * Minimum Pengambilan
	 */
    @Column(name = "DISC_MIN_QTY_OR_VALUE", length = 9)
    var discMinQtyOrValue = 0

    @Column(name = "DISC_MAX_QTY_OR_VALUE", length = 9)
    var discMaxQtyOrValue = 0

    /*
	 * KALAU DISC KAN PASTI KELIPATAN YA?
	 * INI BERARTI TIDAK PERLU:
	 * PERLU DIEVALUASI LAGI
	 */
    @Column(name = "DISC_KELIPATAN")
    var isDiscKelipatan = true

    @Column(name = "DISC_CASH_ONLY")
    var isDiscCashOnly = false

    @Column(name = "FOR_DISC_QTY_OR_VALUE_LEV1")
    var forDiscQtyOrValueLev1 = 0.0

    @Column(name = "DISC1_GET_LEV1")
    var disc1GetLev1 = 0.0

    @Column(name = "DISC1_GET_LEV1_VALUE") //PER KARTON/biggest
    var disc1GetLev1_Value = 0.0

    @Column(name = "DISC2_GET_LEV1")
    var disc2GetLev1 = 0.0

    @Column(name = "DISC3_GET_LEV1")
    var disc3GetLev1 = 0.0

    @Column(name = "DISC1PLUS_GET_LEV1")
    var disc1PlusGetLev1 = 0.0

    @Column(name = "DISC2PLUS_GET_LEV1")
    var disc2PlusGetLev1 = 0.0

    @Column(name = "FOR_DISC_QTY_OR_VALUE_LEV2")
    var forDiscQtyOrValueLev2 = 0.0

    @Column(name = "DISC1_GET_LEV2")
    var disc1GetLev2 = 0.0

    @Column(name = "DISC1_GET_LEV2_VALUE") //PER KARTON/biggest
    var disc1GetLev2_Value = 0.0

    @Column(name = "DISC2_GET_LEV2")
    var disc2GetLev2 = 0.0

    @Column(name = "DISC3_GET_LEV2")
    var disc3GetLev2 = 0.0

    @Column(name = "DISC1PLUS_GET_LEV2")
    var disc1PlusGetLev2 = 0.0

    @Column(name = "DISC2PLUS_GET_LEV2")
    var disc2PlusGetLev2 = 0.0

    @Column(name = "FOR_DISC_QTY_OR_VALUE_LEV3")
    var forDiscQtyOrValueLev3 = 0.0

    @Column(name = "DISC1_GET_LEV3")
    var disc1GetLev3 = 0.0

    @Column(name = "DISC1_GET_LEV3_VALUE") //PER KARTON/biggest
    var disc1GetLev3_Value = 0.0

    @Column(name = "DISC2_GET_LEV3")
    var disc2GetLev3 = 0.0

    @Column(name = "DISC3_GET_LEV3")
    var disc3GetLev3 = 0.0

    @Column(name = "DISC1PLUS_GET_LEV3")
    var disc1PlusGetLev3 = 0.0

    @Column(name = "DISC2PLUS_GET_LEV3")
    var disc2PlusGetLev3 = 0.0

    @Column(name = "FOR_DISC_QTY_OR_VALUE_LEV4")
    var forDiscQtyOrValueLev4 = 0.0

    @Column(name = "DISC1_GET_LEV4")
    var disc1GetLev4 = 0.0

    @Column(name = "DISC1_GET_LEV4_VALUE") //PER KARTON/biggest
    var disc1GetLev4_Value = 0.0

    @Column(name = "DISC2_GET_LEV4")
    var disc2GetLev4 = 0.0

    @Column(name = "DISC3_GET_LEV4")
    var disc3GetLev4 = 0.0

    @Column(name = "DISC1PLUS_GET_LEV4")
    var disc1PlusGetLev4 = 0.0

    @Column(name = "DISC2PLUS_GET_LEV4")
    var disc2PlusGetLev4 = 0.0

    @Column(name = "FOR_DISC_QTY_OR_VALUE_LEV5")
    var forDiscQtyOrValueLev5 = 0.0

    @Column(name = "DISC1_GET_LEV5")
    var disc1GetLev5 = 0.0

    @Column(name = "DISC1_GET_LEV5_VALUE") //PER KARTON/biggest
    var disc1GetLev5_Value = 0.0

    @Column(name = "DISC2_GET_LEV5")
    var disc2GetLev5 = 0.0

    @Column(name = "DISC3_GET_LEV5")
    var disc3GetLev5 = 0.0

    @Column(name = "DISC1PLUS_GET_LEV5")
    var disc1PlusGetLev5 = 0.0

    @Column(name = "DISC2PLUS_GET_LEV5")
    var disc2PlusGetLev5 = 0.0

    /*
	 * Kadang dalam suatu promo mendapat 2 barang
	 * FREE GOOD 1 METHOD: What Customers Gets
	 */
    @Column(name = "PROMO_FG1_METHOD", length = 5)
    @Enumerated(EnumType.STRING)
    var promoFg1Method: EnumPromoDiscFgMethod? = null

    /*
	 * Minimum Pengambilan
	 */
    @Column(name = "FG1_MIN_QTY_OR_VALUE")
    var fg1MinQtyOrValue = 0

    @Column(name = "FG1_KELIPATAN")
    var isFg1Kelipatan = true

    @ManyToOne
    @JoinColumn(name = "freeGood1MaterialBean", referencedColumnName = "ID")
    var freeGood1MaterialBean: FMaterial? = null

    @Column(name = "FG1_HARGA_JUAL_NOL")
    var isFg1HargaJualNol = true

    /*
	 * Dipakai untuk perhitungan berapa nominal rupiah
	 *
	 */
    @Column(name = "FG1_PRICE_PCS")
    var fg1PricePcs = 0.0

    @Column(name = "FOR_FG1_QTY_OR_VALUE_LEV1", length = 8)
    var forFg1QtyOrValueLev1 = 0

    @Column(name = "FG1_QTY_GET_LEV1", length = 8)
    var fg1QtyGetLev1 = 0

    @Column(name = "FOR_FG1_QTY_OR_VALUE_LEV2", length = 8)
    var forFg1QtyOrValueLev2 = 0

    @Column(name = "FG1_QTY_GET_LEV2", length = 8)
    var fg1QtyGetLev2 = 0

    @Column(name = "FOR_FG1_QTY_OR_VALUE_LEV3", length = 8)
    var forFg1QtyOrValueLev3 = 0

    @Column(name = "FG1_QTY_GET_LEV3", length = 8)
    var fg1QtyGetLev3 = 0

    @Column(name = "FOR_FG1_QTY_OR_VALUE_LEV4", length = 8)
    var forFg1QtyOrValueLev4 = 0

    @Column(name = "FG1_QTY_GET_LEV4", length = 8)
    var fg1QtyGetLev4 = 0

    @Column(name = "FOR_FG1_QTY_OR_VALUE_LEV5", length = 8)
    var forFg1QtyOrValueLev5 = 0

    @Column(name = "FG1_QTY_GET_LEV5", length = 8)
    var fg1QtyGetLev5 = 0

    /*
	 * Kadang dalam suatu promo mendapat 2 barang
	 * FREE GOOD 2 METHOD: What Customers Gets
	 */
    @Column(name = "PROMO_FG2_METHOD", length = 5)
    @Enumerated(EnumType.STRING)
    var promoFg2Method: EnumPromoDiscFgMethod? = null

    /*
	 * Minimum Pengambilan
	 */
    @Column(name = "FG2_MIN_QTY_OR_VALUE", length = 8)
    var fg2MinQtyOrValue = 0

    @Column(name = "FG2_KELIPATAN")
    var isFg2Kelipatan = true

    @ManyToOne
    @JoinColumn(name = "freeGood2MaterialBean", referencedColumnName = "ID")
    var freeGood2MaterialBean: FMaterial? = null

    @Column(name = "FG2_HARGA_JUAL_NOL")
    var isFg2HargaJualNol = true

    /*
	 * Dipakai untuk perhitungan berapa nominal rupiah
	 *
	 */
    @Column(name = "FG2_PRICE_PCS")
    var fg2PricePcs = 0.0

    @Column(name = "FOR_FG2_QTY_OR_VALUE_LEV1", length = 8)
    var forFg2QtyOrValueLev1 = 0

    @Column(name = "FG2_QTY_GET_LEV1", length = 8)
    var fg2QtyGetLev1 = 0

    @Column(name = "FOR_FG2_QTY_OR_VALUE_LEV2", length = 8)
    var forFg2QtyOrValueLev2 = 0

    @Column(name = "FG2_QTY_GET_LEV2", length = 8)
    var fg2QtyGetLev2 = 0

    @Column(name = "FOR_FG2_QTY_OR_VALUE_LEV3", length = 8)
    var forFg2QtyOrValueLev3 = 0

    @Column(name = "FG2_QTY_GET_LEV3", length = 8)
    var fg2QtyGetLev3 = 0

    @Column(name = "FOR_FG2_QTY_OR_VALUE_LEV4", length = 8)
    var forFg2QtyOrValueLev4 = 0

    @Column(name = "FG2_QTY_GET_LEV4", length = 8)
    var fg2QtyGetLev4 = 0

    @Column(name = "FOR_FG2_QTY_OR_VALUE_LEV5", length = 8)
    var forFg2QtyOrValueLev5 = 0

    @Column(name = "FG2_QTY_GET_LEV5", length = 8)
    var fg2QtyGetLev5 = 0

    //CASHBACK
    @Column(name = "CASH_BACKVALUE1")
    var cashBackValue1 = 0.0

    @Column(name = "CASH_BACKGET1")
    var cashBackGet1 = 0.0

    @Column(name = "CASH_BACKVALUE2")
    var cashBackValue2 = 0.0

    @Column(name = "CASH_BACKGET2")
    var cashBackGet2 = 0.0

    @Column(name = "CASH_BACKVALUE3")
    var cashBackValue3 = 0.0

    @Column(name = "CASH_BACKGET3")
    var cashBackGet3 = 0.0

    @Column(name = "CASH_BACKVALUE4")
    var cashBackValue4 = 0.0

    @Column(name = "CASH_BACKGET4")
    var cashBackGet4 = 0.0

    //	@OneToMany(mappedBy="fPromoBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    //	@Fetch(FetchMode.JOIN)
    //	private Set<FtDcvh> ftDcvSet;
    //LOG
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    var created = Date()

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    var modified = Date()

    @Column(name = "MODIFIED_BY", length = 20)
    var modifiedBy = "" //User ID

    //** Tools: Jangan dihapus
    val isPersisted: Boolean
        get() = id != 0

    override fun toString(): String {
        return "$kode1- $description"
    }


}