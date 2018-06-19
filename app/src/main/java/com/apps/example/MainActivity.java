package com.apps.example;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.billing.simple.main.BillingActivity;
import com.billing.simple.main.BillingClient;
public class MainActivity extends BillingActivity {
    public static final String SKU_PREMIUM = "paid_version_price";
    Button state_button;
    ImageView mCarImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        mCarImageView = (findViewById(R.id.free_or_premium));
        state_button = findViewById(R.id.button_purchase);
    }
    @Override
    public String getDeveloperKey() {
        return "YOUR_KEY_HERE";
    }
    @Override
    public void PaidSuccess(String sku) {
        Toast.makeText(this, String.format("Thanks For Purchase : %s", sku), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void PaidFailed(String sku) {
        Toast.makeText(this, String.format("You Canceled : %s", sku), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void BillingReady() {
        // check enable buy button
        state_button.setEnabled(true);
        // check if Premium
        final Boolean isPremiumPurchased = isPurchased(SKU_PREMIUM);
        mCarImageView.setImageResource(isPremiumPurchased ? com.apps.example.R.drawable.premium : com.apps.example.R.drawable.free);
        int textId = isPremiumPurchased ? R.string.button_own : R.string.button_buy;
        state_button.setText(textId);
        state_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPremiumPurchased) {
                    Toast.makeText(MainActivity.this, R.string.alert_already_purchased, Toast.LENGTH_SHORT).show();
                } else {
                    Purchase(SKU_PREMIUM, BillingClient.SkuType.INAPP);
                }
            }
        });
    }
    @Override
    public void BillingInitError() {
        // todo make sure sh1 , developer key and package name is right
        //   state_button.setEnabled(false);
        state_button.setText("Please try again later");
    }
}
