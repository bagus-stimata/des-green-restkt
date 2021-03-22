package com.erp.distribution.desgreenrestkt.domain.model.enum

enum class EnumSalesType(var stringId: String, var description: String) {
    TO("TO", "Taking Order"), C("C", "Canvas"), TF("TF", "Task Force"), SHOP("SHOP", "Shop Sales"), R("R", "RETAIL"), KS("KS", "Kassa"), COL("COL", "Collector"), DRV("DRV", "Driver"), HLP("HLP", "Helper"), OTH("OTH", "Others");

}