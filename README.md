# easy in app purchase [ ![Download](https://api.bintray.com/packages/sh3lan93/Android/CounterView/images/download.svg) ](https://github.com/maikl13/easy-in-app-purchase) [![](https://jitpack.io/v/sh3lan93/CounterView.svg)](https://jitpack.io/#maikl13/easy-in-app-purchase) [![API](https://img.shields.io/badge/API-16%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=14) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-easy%20in%20app%20purchase-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6476)

Android semple and easy way to use in app purchase .


<img src="https://f.top4top.net/p_8992bo261.jpg" width="500">





## Usage
### Adding Dependency 
Add this to ```build.gradle``` Project level 
```
allprojects {
  repositories {
    maven{
      url 'https://jitpack.io'
    }
  }
}
```

Add this to ``` build.gradle ``` Module:app

```
implementation 'com.github.maikl13:easy-in-app-purchase:1.3.0'
```

for maven usage 
```
<dependency>
  <groupId>com.github.maikl13</groupId>
  <artifactId>easy-in-app-purchase</artifactId>
  <version>1.3.0</version>
</dependency>
```

### Adding The Library

- in billing activity
```
    public class MainActivity extends BillingActivity
```
- then add this method
```
    @Override
    public String getDeveloperKey() {
        // return YOUR_GOOGLE_PLAY_KEY;
    }
```
- add init callback
```
    @Override
    public void BillingReady() {
        // todo make pay button enabled
    }
    @Override
    public void BillingInitError() {
        // todo make sure sh1 , developer key and package name is right
    }
```
- add Purchase callback
```
    @Override
    public void PaidSuccess(String sku) {
        Toast.makeText(this, String.format("Thanks For Purchase : %s", sku), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void PaidFailed(String sku) {
        Toast.makeText(this, String.format("You Canceled : %s", sku), Toast.LENGTH_SHORT).show();
    }
```
- to Purchase new item ( put it in pay button )
```
    Purchase(SKU_PREMIUM, BillingClient.SkuType.INAPP);
```  
- to check if item already paid
```
    boolean premium = isPurchased(SKU_PREMIUM)
```   
