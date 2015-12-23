package com.share.utils;

import com.share.core.DataRegister;
import com.share.core.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.core.MediaType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wahyuoi on 12/23/15.
 */
public class Parse {
    private static final String registerUrl = "https://api.parse.com/1/installations";
    private static final String notifUrl = "https://api.parse.com/1/push";
    public static void register(User user, String parseAppId, String parseRestApi) {
        System.err.println("do register " + parseAppId + " " + parseRestApi);
        Client client = Client.create();
        WebResource webResource = client.resource(registerUrl);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("c"+String.valueOf(user.getId()));

        jsonObject.put("deviceType", "android");
        jsonObject.put("deviceToken", user.getDeviceId());
        jsonObject.put("pushType", "gcm");
        jsonObject.put("channels", jsonArray);

        String input = JSONObject.toJSONString(jsonObject);
        System.err.println(input);

        ClientResponse response = webResource
                .header("X-Parse-Application-Id", parseAppId)
                .header("X-Parse-REST-API-Key", parseRestApi)
                .type("application/json")
                .post(ClientResponse.class, input);

        System.err.println("end register" + response.getStatus());
    }

    public static void push(List<String> devices, String url) {

    }
}
