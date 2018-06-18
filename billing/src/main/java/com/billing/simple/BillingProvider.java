
package com.billing.simple;




public interface BillingProvider {
    BillingManager getBillingManager();
    boolean isPurchased(String sku);
}

