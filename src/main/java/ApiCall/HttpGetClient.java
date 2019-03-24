package ApiCall;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpGetClient {
    private OkHttpClient client;

    public HttpGetClient(){
        client = new OkHttpClient();
    }

    public Response sendHttpGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return client.newCall(request).execute();
    }

    public Response sendHttpGetRequest(String url,String headerName,String headerValue) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                //To authenticate API calls with the API Key, set the Authorization HTTP header value as Bearer API_KEY
                .addHeader(headerName,headerValue)
                .build();
        return client.newCall(request).execute();
    }
}
