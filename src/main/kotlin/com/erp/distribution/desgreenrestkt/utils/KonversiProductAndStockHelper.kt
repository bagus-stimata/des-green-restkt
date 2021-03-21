package com.erp.distribution.desgreenrestkt.utils

import com.erp.distribution.desgreenrestkt.model.FMaterial

interface KonversiProductAndStockHelper {
    fun setValue(pcsOrUom4: Double, fMaterial: FMaterial?)
    val uom1FromSmallest: Double
    val uom2FromSmallest: Double
    val uom3FromSmallest: Double
    val uom4FromSmallest: Double
    val pPriceBeforePpnFromFMaterialBeforePpn: Double
    val sPriceBeforePpnFromFMaterialBeforePpn: Double
    val pPriceBeforePpnFromFMaterialAfterPpn: Double
    val sPriceBeforePpnFromFMaterialAfterPpn: Double
    fun getUom1234String(jumlahDigit: Double): String
    val uom1234StringUom: String
    val uom1234StringUomHurufBesar: String
    fun getUom1234StringUom_FromConvfact(qtyStock_Smallest: Double, convfact1: Int, convfact2: Int, convfact3: Int, uom1: String?, uom2: String?, uom3: String?, uom4: String?): String
    fun getUom1234StringUom(fmaterialBean: FMaterial?, qtyStock_Smallest: Double): String
    fun getUom1234StringUomHtml(fmaterialBean: FMaterial?, qtyStock_Smallest: Double): String
    fun getUom_234StringUomHtml(fmaterialBean: FMaterial?, qtyStock_Smallest: Double): String
    val uom_234StringUom: String
    val uom_234StringUomHurufBesar: String
    fun getSmallestFromUom1234(fMaterialBean: FMaterial, uom1: Double, uom2: Double, uom3: Double, uom4: Double): Double
}