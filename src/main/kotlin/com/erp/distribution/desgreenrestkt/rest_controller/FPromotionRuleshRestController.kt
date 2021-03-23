package com.erp.distribution.desgreenrestkt.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.local.dao.FPromotionRuleshJPARepository
import com.erp.distribution.desgreenrestkt.data.source.local.dao.FVendorJPARepository
import com.erp.distribution.desgreenrestkt.data.source.entity.FDivision
import com.erp.distribution.desgreenrestkt.data.source.entity.FPromotionRulesh
import com.erp.distribution.desgreenrestkt.data.source.entity.FVendor
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumPromoDiscFgMethod
import com.erp.distribution.desgreenrestkt.domain.model.extended.ZLapTemplate2
import com.erp.distribution.desgreenrestkt.utils.KonversiProductAndStockHelper
import com.erp.distribution.desgreenrestkt.utils.KonversiProductAndStockHelperImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@RestController
class FPromotionRuleshRestController {
    @Autowired
    var fPromotionRuleshJPARepository: FPromotionRuleshJPARepository? = null

    @Autowired
    var fVendorJPARepository: FVendorJPARepository? = null

    @RequestMapping(value = ["/rest/getFPromotionRuleshById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFPromotionRuleshById(@PathVariable("id") id: Int): FPromotionRulesh {
        return fPromotionRuleshJPARepository!!.findById(id).get()
    }

    @get:RequestMapping(value = ["/rest/getAllFPromotionRulesh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allPromotionRulesh: List<FPromotionRulesh>
        get() = fPromotionRuleshJPARepository!!.findAll()

    @RequestMapping(value = ["/rest/getAllFPromotionRuleshByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFPromotionRuleshByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FPromotionRulesh> {
        return fPromotionRuleshJPARepository!!.findAllByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFPromotionRulesh"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFPromotionRulesh(@RequestBody fPromotionRuleshNew: FPromotionRulesh): FPromotionRulesh {
        fPromotionRuleshNew.id = 0 //Memastikan ID adalah Nol
        return fPromotionRuleshJPARepository!!.save(fPromotionRuleshNew)
    }

    @RequestMapping(value = ["/rest/updateFPromotionRulesh/{id}"], method = [RequestMethod.PUT])
    fun updateFPromotionRuleshInfo(@PathVariable("id") id: Int, @RequestBody fPromotionRuleshUpdated: FPromotionRulesh?): FPromotionRulesh {
        val fPromotionRulesh = fPromotionRuleshJPARepository!!.findById(id).orElse(FPromotionRulesh())
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fPromotionRuleshUpdated != null) {
            fPromotionRuleshUpdated.id = fPromotionRulesh.id
            if (fPromotionRulesh.fdivisionBean == null) fPromotionRuleshUpdated.fdivisionBean = fPromotionRulesh.fdivisionBean
            fPromotionRuleshJPARepository!!.save(fPromotionRuleshUpdated)
            return fPromotionRuleshUpdated
        }
        return fPromotionRulesh
    }

    @RequestMapping(value = ["/rest/deleteFPromotionRulesh/{id}"], method = [RequestMethod.DELETE])
    fun deleteFPromotionRulesh(@PathVariable("id") id: Int): FPromotionRulesh? {
        val fPromotionRulesh = fPromotionRuleshJPARepository!!.findById(id).orElse(FPromotionRulesh())
        if (fPromotionRulesh != null) {
            fPromotionRuleshJPARepository!!.delete(fPromotionRulesh)
        }
        return fPromotionRulesh
    }

    var listLapTemplate: List<ZLapTemplate2> = ArrayList()
    fun fillDatabaseReportDaftarPromoBerjalan(dateTransactionFrom: Date?, dateTransactionTo: Date, listFDivision: List<FDivision>, listFVendor: List<FVendor?>): List<ZLapTemplate2> {
        val sdf_lengkap = SimpleDateFormat("dd MMMM yyyy")
        val nf = NumberFormat.getInstance()
        nf.maximumFractionDigits = 0
        val nf2 = NumberFormat.getInstance()
        nf2.maximumFractionDigits = 2

//		try{
//			dateTransactionFrom = CommonDateFormat.fromLocalDate(view.dateField1From.getValue());
//			dateTransactionTo = CommonDateFormat.fromLocalDate(view.dateField1To.getValue());
//		} catch(Exception ex){
//			ex.printStackTrace();
//		}

//        paramJudulHeaderSub1 = "POSISI TANGGAL " + sdf_lengkap.format(dateTransactionTo);
        val listLapTemplate1: MutableList<ZLapTemplate2> = ArrayList()
        listLapTemplate = ArrayList()
//        val posisiTanggal = dateTransactionTo
        val listFPromotionRulesForThisDay: List<FPromotionRulesh> = ArrayList()
        try {
//            listFPromotionRulesForThisDay = new ArrayList<FPromotionRulesh>( fPromotionRuleshJPARepository
//                    .findAllBy_ValidByDate( listFDivision,
//                            posisiTanggal, false) );
        } catch (e: Exception) {
        }
        for (fpromotionRuleshBean in listFPromotionRulesForThisDay) {
            var status = "AKTIF"
            var vendor = ""
            if (fpromotionRuleshBean.statusActive == false) status = "NON AKTIF"
            var valid_Products = ""
            val fvendorSet: MutableSet<FVendor?> = HashSet()
            for (detilItem in fpromotionRuleshBean.fpromotionRulesdValidProductsSet!!) {
                if (detilItem.validMaterialBean != null) {
                    fvendorSet.add(detilItem.validMaterialBean!!.fvendorBean)
                    valid_Products += detilItem.validMaterialBean!!.pname + " (" + detilItem.validMaterialBean!!.pcode + "), "
                } else if (detilItem.validMaterialGroup3Bean != null) {
                    val allVendor = FVendor()
                    allVendor.id = 7777777
                    allVendor.vcode = "ALL"
                    allVendor.vname = "ALL VENDOR"
                    fvendorSet.add(allVendor)
                    valid_Products += detilItem.validMaterialGroup3Bean!!.description + ", "
                } else if (detilItem.validMaterialGroup2Bean != null) {
                    val allVendor = FVendor()
                    allVendor.id = 7777777
                    allVendor.vcode = "ALL"
                    allVendor.vname = "ALL VENDOR"
                    fvendorSet.add(allVendor)
                    valid_Products += detilItem.validMaterialGroup2Bean!!.description + ", "
                } else if (detilItem.validMaterialSalesBrandBean != null) {
                    val allVendor = FVendor()
                    allVendor.id = 7777777
                    allVendor.vcode = "ALL"
                    allVendor.vname = "ALL VENDOR"
                    fvendorSet.add(allVendor)
                    valid_Products += detilItem.validMaterialSalesBrandBean!!.description + ", "
                } else if (detilItem.validVendorBean != null) {
                    fvendorSet.add(detilItem.validVendorBean)
                    valid_Products += detilItem.validVendorBean!!.vname + ", "
                }
            } //end for Valid Product
            var isValidVendor = false
            for (fvendorBean in fvendorSet) {
                vendor += fvendorBean!!.vname + " (" + fvendorBean.vcode + "), "
                if (fvendorBean.vcode.contains("ALL")) isValidVendor = true
                if (isValidVendor == false) {
                    for (fvendorSelected in listFVendor) {
                        if (fvendorBean == fvendorBean) {
                            isValidVendor = true
                            break
                        }
                    } //endfor fvendor Selected
                }
            } //end for fvendorSet
            try {
                if (fpromotionRuleshBean.totalQtyApplied >= fpromotionRuleshBean.maxTargetQty && fpromotionRuleshBean.maxTargetQty > 0 ||
                        fpromotionRuleshBean.totalValueApplied >= fpromotionRuleshBean.maxTargetValue && fpromotionRuleshBean.maxTargetValue > 0) {
                    status += ", OVER BUDGET"
                }
            } catch (e: Exception) {
            }

            /*
             * 1. DESCRIPTION
             */
            val domainDescription = ZLapTemplate2()
            domainDescription.grup1 = fpromotionRuleshBean.kode1 + " " + fpromotionRuleshBean.description + "      status: " + status
            domainDescription.grup2 = "A. DESKRIPSI"
            domainDescription.grup3 = "-"
            var description = ""
            description += """${fpromotionRuleshBean.description} (${fpromotionRuleshBean.kode1}) ref: ${fpromotionRuleshBean.kode2}
"""
            var string_IsClaimPabrik = ""
            if (fpromotionRuleshBean.claimPabrik && fpromotionRuleshBean.accAccountBean != null && fpromotionRuleshBean.accAccount_CreditBean != null) string_IsClaimPabrik = "CLAIM PABRIK"
            description += "Vendor: " + vendor + "   DIV: " + fpromotionRuleshBean.fdivisionBean!!.kode1 + "  " + (if (fpromotionRuleshBean.sharedToCompany == true) "SHARED" else "") + "  " + string_IsClaimPabrik
            domainDescription.string1 = description
            domainDescription.string23 = vendor //BUAT VENDOR
            listLapTemplate1.add(domainDescription)
            /*
             * 2. MEKANISME
             */
            val domainMekanisme = ZLapTemplate2()
            domainMekanisme.grup1 = fpromotionRuleshBean.kode1 + " " + fpromotionRuleshBean.description + "      status: " + status
            domainMekanisme.grup2 = "B. Mekanisme"
            domainMekanisme.grup3 = "-"
            var mekanisme = ""
            if (fpromotionRuleshBean.isValidProductsAccumulation) mekanisme += "Metode Akumulasi untuk "
            mekanisme += "Setiap Pembelian Product/Group/Vendor: $valid_Products\n"
            if (fpromotionRuleshBean.promoDiscMethod != null) {
                val additionTabSpace = ""
                var satuan = ""
                if (fpromotionRuleshBean.promoDiscMethod == EnumPromoDiscFgMethod.VAL) {
//					additionTabSpace = "\t";
                } else if (fpromotionRuleshBean.promoDiscMethod == EnumPromoDiscFgMethod.UOM1) {
                    satuan = " KRT "
                } else if (fpromotionRuleshBean.promoDiscMethod == EnumPromoDiscFgMethod.UOM4) {
                    satuan = " pcs "
                }
                mekanisme += "MENDAPATKAN DISKON atau POTONGAN HARGA \n"
                if (fpromotionRuleshBean.forDiscQtyOrValueLev1 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forDiscQtyOrValueLev2 - 1)
                    if (fpromotionRuleshBean.forDiscQtyOrValueLev2 == 0.0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forDiscQtyOrValueLev1) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat diskon "
                    if (fpromotionRuleshBean.disc1GetLev1 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc1GetLev1) + "%, "
                    if (fpromotionRuleshBean.disc1GetLev1_Value > 0) mekanisme += "potongan IDR " + nf.format(fpromotionRuleshBean.disc1GetLev1_Value) + " per KRT, "
                    if (fpromotionRuleshBean.disc2GetLev1 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc2GetLev1) + "%, "
                    if (fpromotionRuleshBean.disc3GetLev1 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc3GetLev1) + "%, "
                    if (fpromotionRuleshBean.disc1PlusGetLev1 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc1PlusGetLev1) + "%, "
                    if (fpromotionRuleshBean.disc2PlusGetLev1 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc2PlusGetLev1) + "%, "
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forDiscQtyOrValueLev2 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forDiscQtyOrValueLev3 - 1)
                    if (fpromotionRuleshBean.forDiscQtyOrValueLev3 == 0.0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forDiscQtyOrValueLev2) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat diskon "
                    if (fpromotionRuleshBean.disc1GetLev2 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc1GetLev2) + "%, "
                    if (fpromotionRuleshBean.disc1GetLev2_Value > 0) mekanisme += "potongan IDR " + nf.format(fpromotionRuleshBean.disc1GetLev2_Value) + " per KRT, "
                    if (fpromotionRuleshBean.disc2GetLev2 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc2GetLev2) + "%, "
                    if (fpromotionRuleshBean.disc3GetLev2 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc3GetLev2) + "%, "
                    if (fpromotionRuleshBean.disc1PlusGetLev2 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc1PlusGetLev2) + "%, "
                    if (fpromotionRuleshBean.disc2PlusGetLev2 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc2PlusGetLev2) + "%, "
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forDiscQtyOrValueLev3 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forDiscQtyOrValueLev4 - 1)
                    if (fpromotionRuleshBean.forDiscQtyOrValueLev4 == 0.0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forDiscQtyOrValueLev3) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat diskon "
                    if (fpromotionRuleshBean.disc1GetLev3 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc1GetLev3) + "%, "
                    if (fpromotionRuleshBean.disc1GetLev3_Value > 0) mekanisme += "potongan IDR " + nf.format(fpromotionRuleshBean.disc1GetLev3_Value) + " per KRT, "
                    if (fpromotionRuleshBean.disc2GetLev3 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc2GetLev3) + "%, "
                    if (fpromotionRuleshBean.disc3GetLev3 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc3GetLev3) + "%, "
                    if (fpromotionRuleshBean.disc1PlusGetLev3 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc1PlusGetLev3) + "%, "
                    if (fpromotionRuleshBean.disc2PlusGetLev3 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc2PlusGetLev3) + "%, "
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forDiscQtyOrValueLev4 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forDiscQtyOrValueLev5 - 1)
                    if (fpromotionRuleshBean.forDiscQtyOrValueLev5 == 0.0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forDiscQtyOrValueLev4) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat diskon "
                    if (fpromotionRuleshBean.disc1GetLev4 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc1GetLev4) + "%, "
                    if (fpromotionRuleshBean.disc1GetLev4_Value > 0) mekanisme += "potongan IDR " + nf.format(fpromotionRuleshBean.disc1GetLev4_Value) + " per KRT, "
                    if (fpromotionRuleshBean.disc2GetLev4 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc2GetLev4) + "%, "
                    if (fpromotionRuleshBean.disc3GetLev4 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc3GetLev4) + "%, "
                    if (fpromotionRuleshBean.disc1PlusGetLev4 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc1PlusGetLev4) + "%, "
                    if (fpromotionRuleshBean.disc2PlusGetLev4 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc2PlusGetLev4) + "%, "
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forDiscQtyOrValueLev5 > 0) {
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forDiscQtyOrValueLev5) + " - ~ " + satuan + additionTabSpace + "\t mendapat diskon "
                    if (fpromotionRuleshBean.disc1GetLev5 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc1GetLev5) + "%, "
                    if (fpromotionRuleshBean.disc1GetLev5_Value > 0) mekanisme += "potongan IDR " + nf.format(fpromotionRuleshBean.disc1GetLev5_Value) + " per KRT, "
                    if (fpromotionRuleshBean.disc2GetLev5 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc2GetLev5) + "%, "
                    if (fpromotionRuleshBean.disc3GetLev5 > 0) mekanisme += nf2.format(fpromotionRuleshBean.disc3GetLev5) + "%, "
                    if (fpromotionRuleshBean.disc1PlusGetLev5 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc1PlusGetLev5) + "%, "
                    if (fpromotionRuleshBean.disc2PlusGetLev5 > 0) mekanisme += "+Disc " + nf2.format(fpromotionRuleshBean.disc2PlusGetLev5) + "%, "
                    mekanisme += "\n"
                }
                //				mekanisme += "\n";
                if (fpromotionRuleshBean.isDiscCashOnly) mekanisme += "TUNAI/CASH SAJA\n"
            } //end if diskon method
            if (fpromotionRuleshBean.promoFg1Method != null && fpromotionRuleshBean.freeGood1MaterialBean != null) {
                mekanisme += "Mendapatkan Bonus Barang    " + "\" " + fpromotionRuleshBean.freeGood1MaterialBean!!.pname.toUpperCase() + " (" + fpromotionRuleshBean.freeGood1MaterialBean!!.pcode + ") " + "\""
                mekanisme += "\n"
                val additionTabSpace = ""
                var satuan = ""
                if (fpromotionRuleshBean.promoFg1Method == EnumPromoDiscFgMethod.VAL) {
//					additionTabSpace = "\t";
                } else if (fpromotionRuleshBean.promoFg1Method == EnumPromoDiscFgMethod.UOM1) {
                    satuan = " KRT "
                } else if (fpromotionRuleshBean.promoFg1Method == EnumPromoDiscFgMethod.UOM4) {
                    satuan = " pcs "
                }
                if (fpromotionRuleshBean.forFg1QtyOrValueLev1 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev2 - 1.toLong())
                    if (fpromotionRuleshBean.forFg1QtyOrValueLev2 == 0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev1) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat Bonus "
                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev1.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
                    if (fpromotionRuleshBean.fg1QtyGetLev1 > 0) mekanisme += kps.uom1234StringUom
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forFg1QtyOrValueLev2 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev3 - 1.toLong())
                    if (fpromotionRuleshBean.forFg1QtyOrValueLev3 == 0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev2) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat Bonus "
                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev2.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
                    if (fpromotionRuleshBean.fg1QtyGetLev2 > 0) mekanisme += kps.uom1234StringUom
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forFg1QtyOrValueLev3 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev4 - 1.toLong())
                    if (fpromotionRuleshBean.forFg1QtyOrValueLev4 == 0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev3) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat Bonus "
                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev3.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
                    if (fpromotionRuleshBean.fg1QtyGetLev3 > 0) mekanisme += kps.uom1234StringUom
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forFg1QtyOrValueLev4 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev5 - 1.toLong())
                    if (fpromotionRuleshBean.forFg1QtyOrValueLev5 == 0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev4) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat Bonus "
                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev4.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
                    if (fpromotionRuleshBean.fg1QtyGetLev4 > 0) mekanisme += kps.uom1234StringUom
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forFg1QtyOrValueLev5 > 0) {
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev5) + " - ~ " + satuan + additionTabSpace + "\t mendapat Bonus "
                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev5.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
                    if (fpromotionRuleshBean.fg1QtyGetLev5 > 0) mekanisme += kps.uom1234StringUom
                    mekanisme += "\n"
                }
            } //endfor FG1
            domainMekanisme.string1 = mekanisme
            domainMekanisme.string23 = vendor //BUAT VENDOR
            listLapTemplate1.add(domainMekanisme)

            /*
             * 3. BERLAKU UNTUK
             */
            val domainBerlakuUntuk = ZLapTemplate2()
            domainBerlakuUntuk.grup1 = fpromotionRuleshBean.kode1 + " " + fpromotionRuleshBean.description + "      status: " + status
            domainBerlakuUntuk.grup2 = "C. BERLAKU"
            domainBerlakuUntuk.grup3 = "-"
            var berlaku = ""
            berlaku += "Mulai " + sdf_lengkap.format(fpromotionRuleshBean.validPeriodDateFrom) + " sampai dengan " + sdf_lengkap.format(fpromotionRuleshBean.validPeriodDateTo)
            berlaku += "\nUNTUK\n"
            var validCustomer = ""
            val counterCustomer = 0
            for (detilItem in fpromotionRuleshBean.fpromotionRulesdValidCustsSet!!) {
                try {
                    if (detilItem.validCustomerBean != null) {
                        validCustomer += (counterCustomer.inc()).toString() + ". " + detilItem.validCustomerBean!!.custname + " (" + detilItem.validCustomerBean!!.custno + ") "
                    } else if (detilItem.validAreaBean != null) {
                        validCustomer += (counterCustomer.inc()).toString() + ". " + detilItem.validAreaBean!!.description + " (" + detilItem.validAreaBean!!.kode1 + ") "
                    } else if (detilItem.validCustomerGroupBean != null) {
                        validCustomer += (counterCustomer.inc()).toString() + ". " + detilItem.validCustomerGroupBean!!.description + " (" + detilItem.validCustomerGroupBean!!.kode1 + ") "
                    } else if (detilItem.validDistributionChannelBean != null) {
                        validCustomer += (counterCustomer.inc()).toString() + ". " + detilItem.validDistributionChannelBean!!.description + " (" + detilItem.validDistributionChannelBean!!.kode1 + ") "
                    }
                    validCustomer += "\n"
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (validCustomer.trim { it <= ' ' } == "") validCustomer = "SEMUA CUSTOMER"
            berlaku += validCustomer.trim { it <= ' ' }
            domainBerlakuUntuk.string1 = berlaku
            domainBerlakuUntuk.string23 = vendor //BUAT VENDOR
            listLapTemplate1.add(domainBerlakuUntuk)
        } //end for fpromotionRuleshBean


//        listLapTemplate = new ArrayList<>();
//        for (ZLapTemplate2 lapTemplate: listLapTemplate1) {
//            boolean isValidVendor = false;
//            for (FVendor fvendorSelected: view.gridVendor.getSelectedItems()) {
//                if (lapTemplate.getString23().toLowerCase().contains(fvendorSelected.getVcode().toLowerCase()) ||
//                        lapTemplate.getString23().toLowerCase().contains("all")) {
//                    isValidVendor = true;
//
////					System.out.println("True " + lapTemplate.getString23());
//
//                    break;
//                }
//            }
//            if (isValidVendor) listLapTemplate.add(lapTemplate);
//        }//endfor listLapTemplate1
//
//        listLapTemplate.sort(Comparator.comparing(ZLapTemplate2::getGrup3));
//        listLapTemplate.sort(Comparator.comparing(ZLapTemplate2::getGrup2));
//        listLapTemplate.sort(Comparator.comparing(ZLapTemplate2::getGrup1));
        return listLapTemplate
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FPromotionRuleshRestController::class.java)
    }
}