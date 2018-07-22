package com.sub.network;

import com.sub.network.model.SubscriptionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    private JsonParser() {
    }

    public static SubscriptionModel parseSubscriptionResponse(final String response) {

        try {

            SubscriptionModel subscriptionModel = new SubscriptionModel();

            JSONObject jsonObject = new JSONObject(response);

            // get user info
            JSONObject dataAttributes = jsonObject.getJSONObject("data").getJSONObject("attributes");

            String email = dataAttributes.getString("email-address");
            String title = dataAttributes.getString("title");
            String firstName = dataAttributes.getString("first-name");

            // prepare user info model
            SubscriptionModel.UserInfo userInfo =
                    new SubscriptionModel.UserInfo(email, title, firstName);

            subscriptionModel.setUserInfo(userInfo);

            // get service,product,subscription info
            JSONArray jsonArray = jsonObject.getJSONArray("included");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                JSONObject includedAttributes = object.getJSONObject("attributes");
                String type = object.getString("type");

                if (type != null && type.equals("services")) {

                    String msnId = includedAttributes.getString("msn");
                    String credit = includedAttributes.getString("credit");
                    String expiryDate = includedAttributes.getString("credit-expiry");

                    // prepare service model
                    SubscriptionModel.ServiceInfo serviceInfo =
                            new SubscriptionModel.ServiceInfo(msnId, credit, expiryDate);

                    subscriptionModel.setServiceInfo(serviceInfo);

                }

                if (type != null && type.equals("subscriptions")) {

                    String remainingBal = includedAttributes.getString("included-data-balance");
                    String expiryDate = includedAttributes.getString("expiry-date");

                    // prepare subscription model
                    SubscriptionModel.SubscriptionInfo subscriptionInfo =
                            new SubscriptionModel.SubscriptionInfo(remainingBal, expiryDate);

                    subscriptionModel.setSubscriptionInfo(subscriptionInfo);
                }

                if (type != null && type.equals("products")) {

                    String name = includedAttributes.getString("name");
                    String price = includedAttributes.getString("price");

                    // prepare products model
                    SubscriptionModel.ProductInfo productInfo =
                            new SubscriptionModel.ProductInfo(name, price);

                    subscriptionModel.setProductInfo(productInfo);
                }


            }

            return subscriptionModel;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException n) {
            n.printStackTrace();
        }


        return null;
    }
}
