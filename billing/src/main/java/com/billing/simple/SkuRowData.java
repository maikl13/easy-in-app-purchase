
package com.billing.simple;

import com.android.billingclient.api.BillingClient.SkuType;
import com.android.billingclient.api.SkuDetails;


public class SkuRowData {
    private String sku, title, price, description;

    private @SkuType String billingType;

    public SkuRowData(SkuDetails details,
            @SkuType String billingType) {
        this.sku = details.getSku();
        this.title = details.getTitle();
        this.price = details.getPrice();
        this.description = details.getDescription();

        this.billingType = billingType;
    }



    public String getSku() {
        return sku;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public @SkuType String getSkuType() {
        return billingType;
    }
}
