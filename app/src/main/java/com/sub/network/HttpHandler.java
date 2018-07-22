package com.sub.network;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HttpHandler {

    private HttpHandler() {
    }

    private static final int CONNECTION_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 10000;

    public static String getSubscriptionResponse(final String apiUrl) {

        try {
            URL url = new URL(apiUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.connect();

            int resCode = httpURLConnection.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                int byteRead = -1;
                char[] buffer = new char[512];
                StringBuffer sb = new StringBuffer();

                while ((byteRead = br.read(buffer)) != -1) {
                    sb.append(buffer, 0, byteRead);
                }

                return sb.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String getSubscriptionFromAssets(Context context, String fileName) {

        try {
            InputStream inputStream = context.getAssets().open(fileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            int byteRead = -1;
            char[] buffer = new char[512];
            StringBuffer sb = new StringBuffer();

            while ((byteRead = br.read(buffer)) != -1) {
                sb.append(buffer, 0, byteRead);
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static boolean isInternetConnected(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
