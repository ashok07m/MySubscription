package com.sub.task;

import android.content.Context;
import android.os.AsyncTask;

import com.sub.network.HttpHandler;
import com.sub.network.JsonParser;
import com.sub.network.model.SubscriptionModel;

public class LoginAsyncTask extends AsyncTask<String, Void, SubscriptionModel> {

    private LoginListener mSubscriptionListener;
    private Context mContext;

    public LoginAsyncTask(Context context, LoginListener subscriptionListener) {
        mContext = context;
        mSubscriptionListener = subscriptionListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mSubscriptionListener != null) {
            mSubscriptionListener.showLoading();
        }
    }

    @Override
    protected SubscriptionModel doInBackground(String... strings) {

        String response = HttpHandler.getSubscriptionFromAssets(mContext, strings[0]);
        SubscriptionModel subscriptionModel = JsonParser.parseSubscriptionResponse(response);

        return subscriptionModel;
    }

    @Override
    protected void onPostExecute(SubscriptionModel subscriptionModel) {
        super.onPostExecute(subscriptionModel);

        if (mSubscriptionListener != null) {
            mSubscriptionListener.hideLoading();

            mSubscriptionListener.onLoginResponse(subscriptionModel);
        }
    }


    public interface LoginListener {

        void showLoading();

        void hideLoading();

        void onLoginResponse(SubscriptionModel subscriptionModel);

    }
}
