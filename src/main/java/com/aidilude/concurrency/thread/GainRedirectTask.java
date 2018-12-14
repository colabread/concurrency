package com.aidilude.concurrency.thread;

import com.aidilude.concurrency.dto.Temp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class GainRedirectTask implements Callable<Temp> {

    private String name;

    private String url;

    public GainRedirectTask(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public Temp call() throws Exception {
        String redirectUrl = null;
        try {
            redirectUrl = getRedirectUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Temp(name, redirectUrl);
    }

    public static String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection)new URL(path).openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(3000);
        return conn.getHeaderField("Location");
    }

}