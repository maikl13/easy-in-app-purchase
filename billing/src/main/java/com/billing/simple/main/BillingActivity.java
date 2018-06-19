package com.billing.simple.main;
import android.app.Activity;
import android.os.Bundle;
import com.android.billingclient.api.BillingClient;
import com.billing.simple.BillingManager;
import com.billing.simple.BillingProvider;

import static com.android.billingclient.api.BillingClient.BillingResponse;
public abstract class BillingActivity extends Activity implements BillingProvider {
    public static final String LAST_SKU_KEY = "lastSku";
    private static final String BILLING_SHARED_KEY = "Billing";
    private BillingManager mBillingManager;
    private MainViewController mViewController;
    public abstract String getDeveloperKey();
    public abstract void BillingReady();
    public abstract void BillingInitError();
    public abstract void PaidFailed(String sku);
    public abstract void PaidSuccess(String sku);
    public void OnBillingReady() {
        BillingReady = true;
        BillingReady();
    }
    boolean BillingReady = false;
    public void OnPaidUpdate() {
        String LAST_SKU = getSharedPreferences(BILLING_SHARED_KEY, 0).getString(LAST_SKU_KEY, "");
        if (!BillingReady) {
            BillingReady();
        } else {
            if (!LAST_SKU.isEmpty()) {
                if (mViewController.Purchased.contains(LAST_SKU)) {
                    PaidSuccess(LAST_SKU);
                } else {
                    PaidFailed(LAST_SKU);
                }
            }
        }
        getSharedPreferences(BILLING_SHARED_KEY, 0).edit().remove(LAST_SKU_KEY).apply();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewController = new MainViewController(this);
        mBillingManager = new BillingManager(this, mViewController.getUpdateListener()) {
            @Override
            public String getDeveloperKey() {
                return BillingActivity.this.getDeveloperKey();
            }
        };
    }
    public void Purchase(final String skuId, final @BillingClient.SkuType String billingType) {
        getSharedPreferences(BILLING_SHARED_KEY, 0).edit().putString(LAST_SKU_KEY, skuId).apply();
        getBillingManager().initiatePurchaseFlow(skuId, billingType);
    }
    void onBillingManagerSetupFinished() {
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mBillingManager != null && mBillingManager.getBillingClientResponseCode() == BillingResponse.OK) {
            mBillingManager.queryPurchases();
        }
    }
    @Override
    public BillingManager getBillingManager() {
        return mBillingManager;
    }
    @Override
    public boolean isPurchased(String sku) {
        return mViewController.isPurchased(sku);
    }
    @Override
    public void onDestroy() {
        if (mBillingManager != null) {
            mBillingManager.destroy();
        }
        super.onDestroy();
    }
}
