package com.apps.immigrants;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.billing.simple.main.BillingActivity;
import com.billing.simple.main.BillingClient;
public class MainActivity extends BillingActivity {
    Button state_button;
    ImageView mCarImageView;
    public static final String SKU_PREMIUM = "paid_version_price";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        mCarImageView = (findViewById(R.id.free_or_premium));
        state_button = findViewById(R.id.button_purchase);
    }
    @Override
    public String getDeveloperKey() {
        return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArFX6DyjU0ahf8xww9AsQ4yvP4nCZZVf54jcl7xY+MmLj0wHd8u3blYV1v7C51Regp+LEKFsgeklKS0Q/b5Jp28YSHupLRFtntT9jNKxZ9EIeeqcFnfjvxf77OayJN28j+mkQY2Ttx1i+esF3N2RmrdjNdzMH5ybW9VoI4iAc9IJQR53DzvBeUb2vLdLNR9QiUms5tWHFtDODkjidFXTgPzIjSOL8tvudRsKaBLf0xL3ITWwqTJq15Y9qRrjmfApTj3312Gc3Gnefiz++uh21b5XyojGFDavII819noVwKLhoTSfOPyzuB7HpyQv5QuUbcd45x+AGFJU6qtnJDA7XBwIDAQAB";
        // return "YOUR_KEY_HERE";
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
        mCarImageView.setImageResource(isPremiumPurchased ? com.apps.immigrants.R.drawable.premium : com.apps.immigrants.R.drawable.free);
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
