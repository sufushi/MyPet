package com.rdc.pet.mypet.chat.model;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpData extends AsyncTask<String, Void, String>{

    private HttpClient httpClient;
    private HttpGet httpGet;
    private HttpResponse httpResponse;
    private HttpEntity httpEntity;

    private String url;
    private InputStream inputStream;
    private HttpGetData listener;

    public HttpData(String url, HttpGetData listener) {
        this.url = url;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(url);
            httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            StringBuffer stringBuffer = new StringBuffer();
            while((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        listener.getDataUrl(s);
        super.onPostExecute(s);
    }
}
