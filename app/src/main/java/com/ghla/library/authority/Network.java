package com.ghla.library.authority;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Network {
    private static Network network = null;
    //TODO: Replace this with the production server
    private static String URL="http://localhost:3000";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private Network() { }
    // static method to create instance of Singleton class
    public static Network getInstance() {
        if (network == null)
            network = new Network();

        return network;
    }

    String postJSON(String json){
        //String can be report json.
        //okhttp can be used to send requests to the server.
        String response = "response";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        try {
            response = client.newCall(request).execute().body().toString();
        } catch (IOException e) {
            //TODO: Propagate this to the UI through EventBus or bubble the error all the way up to the UI.
            e.printStackTrace();
        }
        return response;
    }

}
