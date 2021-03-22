package com.erp.distribution.desgreenrestkt.domain.model.enum

enum class EnumTipePajakCustomer(/*
	 * Ditambah denga 1
	 */
        var intId: Int, var stringId: String, var description: String) {
    REG_01(0, "01", "Pihak yang bukan pemungut pajak"), PB_02(1, "02", "Pemungut Bendaharawan"), PSB_03(2, "03", "Pemungut Selain Bendaharawan"), DPL_04(3, "04", "DPP Nilai Lain-lain"), PL_05(4, "05", "Penyerahan Lainnya"), PPTD_06(5, "06", "Peyerahan yang PPN-nya tidak Dipungut"), PPBS_07(6, "07", "Penyerahan yang PPN-nya Dibebaskan"), PA_08(7, "08", "Penyerahan Aktiva (Pasal 16D UU PPN)");

}