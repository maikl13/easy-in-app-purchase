
package com.billing.simple.main;
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

            if (result == BillingResponse.OK) {
                mActivity.BillingReady();
            } else {
                mActivity.BillingError();

            }


        }
        @Override
        public void onPurchasesUpdated(List<Purchase> purchaseList) {
            Purchased.clear();
            for (Purchase purchase : purchaseList) {
                Purchased.add(purchase.getSku());

            }
            mActivity.PaidSuccess();
        }
    }
    private ArrayList<String> Purchased;


}