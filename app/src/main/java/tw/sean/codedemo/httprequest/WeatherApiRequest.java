package tw.sean.codedemo.httprequest;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

public class WeatherApiRequest extends ApiRequest {
    private static final String API_URL = "/v1/rest/datastore/F-C0032-001";

    @Override
    HttpUrl getUrl() {
        String url = Config.CWB_OPEN_DATA_URL + API_URL + "?" +
                "Authorization=" + Config.TOKEN +
                "&locationName=臺北市";
        return HttpUrl.parse(url);
    }

    @Override
    RequestBody getRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest() {
        return new Request.Builder()
                .url(getUrl())
                .get()
                .build();
    }
}
