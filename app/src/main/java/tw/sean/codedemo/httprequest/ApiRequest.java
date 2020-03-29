package tw.sean.codedemo.httprequest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public abstract class ApiRequest extends AsyncTask<Void, Void, String> {

    static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json");

    private int connectTimeout = 15;
    private int readTimeout = 15;

    abstract HttpUrl getUrl();

    abstract RequestBody getRequestBody();

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    @Override
    protected String doInBackground(Void... voids) {
        OkHttpClient client = buildClient();
        Request request = buildRequest();
        return httpRequest(client, request);
    }

    private OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();
    }

    protected Request buildRequest() {
        return new Request.Builder()
                .url(getUrl())
                .post(getRequestBody())
                .build();
    }

    private String httpRequest(OkHttpClient client, Request request) {
        long useTime;
        long start = System.currentTimeMillis();
        String name = getClass().getSimpleName();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response.code());
            } else {
                String body = response.body().string();
                Log.wtf(getClass().getSimpleName(), body);
                return body;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            useTime = calculateUseTime(start);
            Log.wtf(getClass().getSimpleName(), name + "耗時 =>" + useTime + "毫秒");
        }
        return "";
    }

    public String go() {
        try {
            return this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    private long calculateUseTime(long start) {
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
