package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import com.erp.distribution.desgreenrestkt.data.source.entity.FDivisionEntity
import com.erp.distribution.desgreenrestkt.data.source.entity.FVendorEntity
import com.erp.distribution.desgreenrestkt.domain.model.FPromotionRulesh
import com.erp.distribution.desgreenrestkt.domain.model.enum.EnumPromoDiscFgMethod
import com.erp.distribution.desgreenrestkt.domain.model.aux.ZLapTemplate2
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFPromotionRuleshUseCase
import com.erp.distribution.desgreenrestkt.domain.usecase.GetFVendorUseCase
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@RestController
class FPromotionRuleshRestController @Autowired constructor(
    val getFPromotionRuleshUseCase: GetFPromotionRuleshUseCase,
    val getFVendorUseCase: GetFVendorUseCase
){

    @RequestMapping(value = ["/rest/getFPromotionRuleshById/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFPromotionRuleshById(@PathVariable("id") id: Int): FPromotionRulesh {
        return getFPromotionRuleshUseCase.findById(id)
    }

    @get:RequestMapping(value = ["/rest/getAllFPromotionRulesh"], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allPromotionRuleshEntities: List<FPromotionRulesh>
        get() = getFPromotionRuleshUseCase.findAll()

    @RequestMapping(value = ["/rest/getAllFPromotionRuleshByDivision/{fdivisionBean}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFPromotionRuleshByDivision(@PathVariable("fdivisionBean") fdivisionBean: Int): List<FPromotionRulesh> {
        return getFPromotionRuleshUseCase.findByDivision(fdivisionBean)
    }

    @RequestMapping(value = ["/rest/createFPromotionRulesh"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFPromotionRulesh(@RequestBody fPromotionRuleshEntityNew: FPromotionRulesh): FPromotionRulesh {
        fPromotionRuleshEntityNew.id = 0 //Memastikan ID adalah Nol
        return getFPromotionRuleshUseCase.save(fPromotionRuleshEntityNew)
    }

    @RequestMapping(value = ["/rest/updateFPromotionRulesh/{id}"], method = [RequestMethod.PUT])
    fun updateFPromotionRuleshInfo(@PathVariable("id") id: Int, @RequestBody fPromotionRuleshEntityUpdated: FPromotionRulesh?): FPromotionRulesh {
        val fPromotionRulesh = getFPromotionRuleshUseCase.findById(id)
        //Tidak Meng Update Parent: Hanya Info Saja
        if (fPromotionRuleshEntityUpdated != null) {
            fPromotionRuleshEntityUpdated.id = fPromotionRulesh.id
            if (fPromotionRulesh.fdivisionBean == null) fPromotionRuleshEntityUpdated.fdivisionBean = fPromotionRulesh.fdivisionBean
            getFPromotionRuleshUseCase.save(fPromotionRuleshEntityUpdated)
            return fPromotionRuleshEntityUpdated
        }
        return fPromotionRulesh
    }

    @RequestMapping(value = ["/rest/deleteFPromotionRulesh/{id}"], method = [RequestMethod.DELETE])
    fun deleteFPromotionRulesh(@PathVariable("id") id: Int): FPromotionRulesh? {
        val fPromotionRulesh = getFPromotionRuleshUseCase.findById(id).also {
            try {
                getFPromotionRuleshUseCase.delete(it)
            }catch (ex: Exception){}
        }
//        if (fPromotionRulesh != null) {
//            getFPromotionRuleshUseCase.delete(fPromotionRulesh)
//        }
        return fPromotionRulesh
    }

    var listLapTemplate: List<ZLapTemplate2> = ArrayList()
    fun fillDatabaseReportDaftarPromoBerjalan(dateTransactionFrom: Date?, dateTransactionTo: Date, listFDivisionEntity: List<FDivisionEntity>, listFVendorEntity: List<FVendorEntity?>): List<ZLapTemplate2> {
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
        val listFPromotionRulesForThisDayEntity: List<FPromotionRulesh> = ArrayList()
        try {
//            listFPromotionRulesForThisDay = new ArrayList<FPromotionRulesh>( fPromotionRuleshJPARepository
//                    .findAllBy_ValidByDate( listFDivision,
//                            posisiTanggal, false) );
        } catch (e: Exception) {
        }
        for (fpromotionRuleshBean in listFPromotionRulesForThisDayEntity) {
            var status = "AKTIF"
            var vendor = ""
            if (fpromotionRuleshBean.statusActive == false) status = "NON AKTIF"
            var valid_Products = ""
            val fvendorSet: MutableSet<FVendorEntity?> = HashSet()
//            for (detilItem in fpromotionRuleshBean.fpromotionRulesdValidProductsEntitySet) {
//                if (detilItem.validMaterialEntityBean != null) {
//                    fvendorSet.add(detilItem.validMaterialEntityBean!!.fvendorBean)
//                    valid_Products += detilItem.validMaterialEntityBean!!.pname + " (" + detilItem.validMaterialEntityBean!!.pcode + "), "
//                } else if (detilItem.validMaterialGroup3EntityBean != null) {
//                    val allVendor = FVendorEntity()
//                    allVendor.id = 7777777
//                    allVendor.vcode = "ALL"
//                    allVendor.vname = "ALL VENDOR"
//                    fvendorSet.add(allVendor)
//                    valid_Products += detilItem.validMaterialGroup3EntityBean!!.description + ", "
//                } else if (detilItem.validMaterialGroup2EntityBean != null) {
//                    val allVendor = FVendorEntity()
//                    allVendor.id = 7777777
//                    allVendor.vcode = "ALL"
//                    allVendor.vname = "ALL VENDOR"
//                    fvendorSet.add(allVendor)
//                    valid_Products += detilItem.validMaterialGroup2EntityBean!!.description + ", "
//                } else if (detilItem.validMaterialSalesBrandEntityBean != null) {
//                    val allVendor = FVendorEntity()
//                    allVendor.id = 7777777
//                    allVendor.vcode = "ALL"
//                    allVendor.vname = "ALL VENDOR"
//                    fvendorSet.add(allVendor)
//                    valid_Products += detilItem.validMaterialSalesBrandEntityBean!!.description + ", "
//                } else if (detilItem.validVendorEntityBean != null) {
//                    fvendorSet.add(detilItem.validVendorEntityBean)
//                    valid_Products += detilItem.validVendorEntityBean!!.vname + ", "
//                }
//            } //end for Valid Product
            var isValidVendor = false
            for (fvendorBean in fvendorSet) {
                vendor += fvendorBean!!.vname + " (" + fvendorBean.vcode + "), "
                if (fvendorBean.vcode.contains("ALL")) isValidVendor = true
                if (isValidVendor == false) {
                    for (fvendorSelected in listFVendorEntity) {
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
            if (fpromotionRuleshBean.claimPabrik && fpromotionRuleshBean.accAccountEntityBean != null && fpromotionRuleshBean.accAccount_CreditBean != null) string_IsClaimPabrik = "CLAIM PABRIK"
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
            if (fpromotionRuleshBean.validProductsAccumulation) mekanisme += "Metode Akumulasi untuk "
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
                if (fpromotionRuleshBean.discCashOnly) mekanisme += "TUNAI/CASH SAJA\n"
            } //end if diskon method
            if (fpromotionRuleshBean.promoFg1Method != null && fpromotionRuleshBean.freeGood1MaterialBean != null) {
//                mekanisme += "Mendapatkan Bonus Barang    " + "\" " + fpromotionRuleshBean.freeGood1MaterialBean!!.pname.toUpperCase() + " (" + fpromotionRuleshBean.freeGood1MaterialBean!!.pcode + ") " + "\""
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
//                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev1.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
//                    if (fpromotionRuleshBean.fg1QtyGetLev1 > 0) mekanisme += kps.uom1234StringUom
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forFg1QtyOrValueLev2 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev3 - 1.toLong())
                    if (fpromotionRuleshBean.forFg1QtyOrValueLev3 == 0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev2) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat Bonus "
//                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev2.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
//                    if (fpromotionRuleshBean.fg1QtyGetLev2 > 0) mekanisme += kps.uom1234StringUom
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forFg1QtyOrValueLev3 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev4 - 1.toLong())
                    if (fpromotionRuleshBean.forFg1QtyOrValueLev4 == 0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev3) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat Bonus "
//                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev3.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
//                    if (fpromotionRuleshBean.fg1QtyGetLev3 > 0) mekanisme += kps.uom1234StringUom
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forFg1QtyOrValueLev4 > 0) {
                    var stringSampai = nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev5 - 1.toLong())
                    if (fpromotionRuleshBean.forFg1QtyOrValueLev5 == 0) stringSampai = "~"
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev4) + " - " + stringSampai + satuan + additionTabSpace + "\t mendapat Bonus "
//                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev4.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
//                    if (fpromotionRuleshBean.fg1QtyGetLev4 > 0) mekanisme += kps.uom1234StringUom
                    mekanisme += "\n"
                }
                if (fpromotionRuleshBean.forFg1QtyOrValueLev5 > 0) {
                    mekanisme += "\t" + nf.format(fpromotionRuleshBean.forFg1QtyOrValueLev5) + " - ~ " + satuan + additionTabSpace + "\t mendapat Bonus "
//                    val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(fpromotionRuleshBean.fg1QtyGetLev5.toDouble(), fpromotionRuleshBean.freeGood1MaterialBean)
//                    if (fpromotionRuleshBean.fg1QtyGetLev5 > 0) mekanisme += kps.uom1234StringUom
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
//            for (detilItem in fpromotionRuleshBean.fpromotionRulesdValidCustsEntitySet) {
//                try {
//                    if (detilItem.validCustomerEntityBean != null) {
//                        validCustomer += (counterCustomer.inc()).toString() + ". " + detilItem.validCustomerEntityBean!!.custname + " (" + detilItem.validCustomerEntityBean!!.custno + ") "
//                    } else if (detilItem.validAreaEntityBean != null) {
//                        validCustomer += (counterCustomer.inc()).toString() + ". " + detilItem.validAreaEntityBean!!.description + " (" + detilItem.validAreaEntityBean!!.kode1 + ") "
//                    } else if (detilItem.validCustomerGroupEntityBean != null) {
//                        validCustomer += (counterCustomer.inc()).toString() + ". " + detilItem.validCustomerGroupEntityBean!!.description + " (" + detilItem.validCustomerGroupEntityBean!!.kode1 + ") "
//                    } else if (detilItem.validDistributionChannelEntityBean != null) {
//                        validCustomer += (counterCustomer.inc()).toString() + ". " + detilItem.validDistributionChannelEntityBean!!.description + " (" + detilItem.validDistributionChannelEntityBean!!.kode1 + ") "
//                    }
//                    validCustomer += "\n"
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
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