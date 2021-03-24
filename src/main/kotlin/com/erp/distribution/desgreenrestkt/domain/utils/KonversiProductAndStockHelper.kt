package com.erp.distribution.desgreenrestkt.domain.utils

import com.erp.distribution.desgreenrestkt.data.source.entity.FMaterialEntity

interface KonversiProductAndStockHelper {
    fun setValue(pcsOrUom4: Double, fMaterialEntity: FMaterialEntity?)
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
    fun getUom1234StringUom(fmaterialBean: FMaterialEntity?, qtyStock_Smallest: Double): String
    fun getUom1234StringUomHtml(fmaterialBean: FMaterialEntity?, qtyStock_Smallest: Double): String
    fun getUom_234StringUomHtml(fmaterialBean: FMaterialEntity?, qtyStock_Smallest: Double): String
    val uom_234StringUom: String
    val uom_234StringUomHurufBesar: String
    fun getSmallestFromUom1234(fMaterialEntityBean: FMaterialEntity, uom1: Double, uom2: Double, uom3: Double, uom4: Double): Double
}