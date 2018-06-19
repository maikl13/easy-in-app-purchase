package com.billing.simple.main;
import android.util.Log;
import com.android.billingclient.api.BillingClient.BillingResponse;
import com.android.billingclient.api.Purchase;
import com.billing.simple.BillingManager;

import java.util.ArrayList;
import java.util.List;
public class MainViewController {
    private static final String TAG = "MainViewController";
    private final UpdateListener mUpdateListener;
    private BillingActivity mActivity;
    MainViewController(BillingActivity activity) {
        mUpdateListener = new UpdateListener();
        mActivity = activity;
        Purchased = new ArrayList<>();
    }
    public UpdateListener getUpdateListener() {
        return mUpdateListener;
    }
    public boolean isPurchased(String sku) {
        return Purchased.contains(sku);
    }
    private class UpdateListener implements BillingManager.BillingUpdatesListener {
        @Override
        public void onBillingClientSetupFinished() {
            mActivity.onBillingManagerSetupFinished();
        }
        @Override
        public void onConsumeFinished(String token, @BillingResponse int result) {
            Log.d(TAG, "Consumption finished. Purchase token: " + token + ", result: " + result);
            if (result == BillingResponse.OK) {
                mActivity.OnBillingReady();
            } else {
                mActivity.BillingInitError();
            }
        }
        @Override
        public void onPurchasesUpdated(List<Purchase> purchaseList) {
            for (Purchase purchase : purchaseList) {
                Purchased.add(purchase.getSku());
            }
            mActivity.OnPaidUpdate();
        }
    }
    public ArrayList<String> Purchased;
}