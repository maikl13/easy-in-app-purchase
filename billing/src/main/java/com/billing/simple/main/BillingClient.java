package com.billing.simple.main;
public class BillingClient {

    public @interface SkuType {
        /** A type of SKU for in-app products. */
        String INAPP = "inapp";
        /** A type of SKU for subscriptions. */
        String SUBS = "subs";
    }


}
