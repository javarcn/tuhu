package com.domain;

/**
 * Created by hwc on 2016/9/19.
 */
public class Product {

    private String Pid;
    private String DisplayName;
/*    private String Oid;
    private String ProductId;
    private String VariantId;
    private String CatalogName;
    private String PrimaryParentCategory;
    private String Image;
    private String Price;
    private String Remark;
    private String BrakePosition;
    private String ShuXing1;
    private String ShuXing2;
    private String ShuXing3;
    private String ShuXing4;
    private String ShuXing5;
    private String ShuXing6;
    private String AvailableStockQuantity;
    private String RateNumber;
    private String OrderQuantity;
    private String SalesQuantity;
    private String CommentTimes;
    private String CPTab;
    private String OePartCode;
    private String PartNo;
    private String PartName;
    private String Brand;
    private String Unit;
    private String Color;
    private String FactoryColor;
    private String WiperSeries;
    private String StockOut;
    private String OnSale;
    private String IsUsedInAdaptation;
    private String isOE;

    */


    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

   /* public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getVariantId() {
        return VariantId;
    }

    public void setVariantId(String variantId) {
        VariantId = variantId;
    }


    public String getCatalogName() {
        return CatalogName;
    }

    public void setCatalogName(String catalogName) {
        CatalogName = catalogName;
    }

    public String getPrimaryParentCategory() {
        return PrimaryParentCategory;
    }

    public void setPrimaryParentCategory(String primaryParentCategory) {
        PrimaryParentCategory = primaryParentCategory;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getBrakePosition() {
        return BrakePosition;
    }

    public void setBrakePosition(String brakePosition) {
        BrakePosition = brakePosition;
    }

    public String getShuXing1() {
        return ShuXing1;
    }

    public void setShuXing1(String shuXing1) {
        ShuXing1 = shuXing1;
    }

    public String getShuXing2() {
        return ShuXing2;
    }

    public void setShuXing2(String shuXing2) {
        ShuXing2 = shuXing2;
    }

    public String getShuXing3() {
        return ShuXing3;
    }

    public void setShuXing3(String shuXing3) {
        ShuXing3 = shuXing3;
    }

    public String getShuXing4() {
        return ShuXing4;
    }

    public void setShuXing4(String shuXing4) {
        ShuXing4 = shuXing4;
    }

    public String getShuXing5() {
        return ShuXing5;
    }

    public void setShuXing5(String shuXing5) {
        ShuXing5 = shuXing5;
    }

    public String getShuXing6() {
        return ShuXing6;
    }

    public void setShuXing6(String shuXing6) {
        ShuXing6 = shuXing6;
    }

    public String getAvailableStockQuantity() {
        return AvailableStockQuantity;
    }

    public void setAvailableStockQuantity(String availableStockQuantity) {
        AvailableStockQuantity = availableStockQuantity;
    }

    public String getRateNumber() {
        return RateNumber;
    }

    public void setRateNumber(String rateNumber) {
        RateNumber = rateNumber;
    }

    public String getOrderQuantity() {
        return OrderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        OrderQuantity = orderQuantity;
    }

    public String getSalesQuantity() {
        return SalesQuantity;
    }

    public void setSalesQuantity(String salesQuantity) {
        SalesQuantity = salesQuantity;
    }

    public String getCommentTimes() {
        return CommentTimes;
    }

    public void setCommentTimes(String commentTimes) {
        CommentTimes = commentTimes;
    }

    public String getCPTab() {
        return CPTab;
    }

    public void setCPTab(String CPTab) {
        this.CPTab = CPTab;
    }

    public String getOePartCode() {
        return OePartCode;
    }

    public void setOePartCode(String oePartCode) {
        OePartCode = oePartCode;
    }

    public String getPartNo() {
        return PartNo;
    }

    public void setPartNo(String partNo) {
        PartNo = partNo;
    }

    public String getPartName() {
        return PartName;
    }

    public void setPartName(String partName) {
        PartName = partName;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getFactoryColor() {
        return FactoryColor;
    }

    public void setFactoryColor(String factoryColor) {
        FactoryColor = factoryColor;
    }

    public String getWiperSeries() {
        return WiperSeries;
    }

    public void setWiperSeries(String wiperSeries) {
        WiperSeries = wiperSeries;
    }

    public String getStockOut() {
        return StockOut;
    }

    public void setStockOut(String stockOut) {
        StockOut = stockOut;
    }

    public String getOnSale() {
        return OnSale;
    }

    public void setOnSale(String onSale) {
        OnSale = onSale;
    }

    public String getIsUsedInAdaptation() {
        return IsUsedInAdaptation;
    }

    public void setIsUsedInAdaptation(String isUsedInAdaptation) {
        IsUsedInAdaptation = isUsedInAdaptation;
    }

    public String getIsOE() {
        return isOE;
    }

    public void setIsOE(String isOE) {
        this.isOE = isOE;
    }*/

}
