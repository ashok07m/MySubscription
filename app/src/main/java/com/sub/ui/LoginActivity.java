package com.sub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sub.R;
import com.sub.network.model.SubscriptionModel;
import com.sub.task.LoginAsyncTask;
import com.sub.utils.AppConstants;


public class LoginActivity extends BaseActivity implements LoginAsyncTask.LoginListener {

    private EditText etMsn;
    private Button btnLogin;

    String mEnteredMsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMsn = (EditText) findViewById(R.id.et_msn);
        btnLogin = (Button) findViewById(R.id.bt_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEnteredMsn = etMsn.getText().toString();
                if (!TextUtils.isEmpty(mEnteredMsn)) {

                    LoginAsyncTask loginAsyncTask =
                            new LoginAsyncTask(LoginActivity.this, LoginActivity.this);
                    loginAsyncTask.execute(AppConstants.SUBSCRIPTION_JSON_FILE);
                } else {
                    showMessage(R.string.enter_msn);
                }
            }
        });
    }

    @Override
    public void showLoading() {
        super.showProgressDialog();
    }

    @Override
    public void hideLoading() {
        super.hideProgressDialog();
    }

    @Override
    public void onLoginResponse(SubscriptionModel subscriptionModel) {

        if (subscriptionModel != null && subscriptionModel.getServiceInfo() != null) {
            String msnId = subscriptionModel.getServiceInfo().getMsnId();
            if (msnId != null && mEnteredMsn.equals(msnId)) {
                showMessage(R.string.info_login_success);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(AppConstants.EXTRA_SUBSCRIPTION_MODEL, subscriptionModel);
                startActivity(intent);
                finish();
            } else {
                showMessage(R.string.error_login_failed);
            }
        } else {
            showMessage(R.string.error_login_failed);
        }

    }
}
