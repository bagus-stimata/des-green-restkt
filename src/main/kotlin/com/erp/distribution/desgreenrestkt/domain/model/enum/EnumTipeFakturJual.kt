package com.erp.distribution.desgreenrestkt.domain.model.enum

enum class EnumTipeFakturJual(var stringId: String, var description: String) {
    F("F", "Faktur"), R("R", "Retur"), FI("FI", "Nota Intern/Sampling"), FDN("FDN", "Nota Piutang Debit"), RCN("RCN", "Nota Piutang Credit"), SRV("SRV", "Service or Non Stockable Transaction"), SO("SO", "Sales Order (Non Stockable)");

}