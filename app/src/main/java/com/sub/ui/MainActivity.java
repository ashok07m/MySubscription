package com.sub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.sub.R;
import com.sub.network.model.SubscriptionModel;
import com.sub.ui.BaseActivity;
import com.sub.utils.AppConstants;

public class MainActivity extends BaseActivity {

    TextView tvUserName;
    TextView tvProdName;
    TextView tvPrice;
    TextView tvRemBal;
    TextView tvExpDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvProdName = (TextView) findViewById(R.id.tv_prd_name_val);
        tvPrice = (TextView) findViewById(R.id.tv_price_val);
        tvRemBal = (TextView) findViewById(R.id.tv_rem_bal_val);
        tvExpDate = (TextView) findViewById(R.id.tv_exp_date_val);


        Intent intent = getIntent();
        if (intent != null) {
            SubscriptionModel subscriptionModel = (SubscriptionModel) intent.getSerializableExtra(
                    AppConstants.EXTRA_SUBSCRIPTION_MODEL);

            displayInformation(subscriptionModel);
        }


    }

    private void displayInformation(SubscriptionModel subscriptionModel) {
        SubscriptionModel.UserInfo userInfo = subscriptionModel.getUserInfo();
        String userName = getResources().getString(R.string.lbl_welcome)
                + "!  " + userInfo.getTitle() + " " + userInfo.getFirstName();
        tvUserName.setText(userName);


        SubscriptionModel.ProductInfo productInfo = subscriptionModel.getProductInfo();
        tvProdName.setText(productInfo.getName());
        tvPrice.setText(productInfo.getPrice());

        SubscriptionModel.SubscriptionInfo subscriptionInfo = subscriptionModel.getSubscriptionInfo();
        tvRemBal.setText(subscriptionInfo.getRemainingBal());
        tvExpDate.setText(subscriptionInfo.getExpiryDate());
    }
}
