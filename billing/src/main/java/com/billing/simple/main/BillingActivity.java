package com.billing.simple.main;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.billingclient.api.BillingClient;
import com.billing.simple.BillingManager;
import com.billing.simple.BillingProvider;

import static com.android.billingclient.api.BillingClient.BillingResponse;
public abstract class BillingActivity extends AppCompatActivity implements BillingProvider {

    private BillingManager mBillingManager;

    private MainViewController mViewController;
    public abstract String  getDeveloperKey();


  public   abstract void BillingReady();
    public   abstract void PaidSuccess();
    public   abstract void BillingError();

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

    public void Purchase(final String skuId, final @BillingClient.SkuType String billingType){
        getBillingManager().initiatePurchaseFlow(skuId,billingType);
    }
    void onBillingManagerSetupFinished(){


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
