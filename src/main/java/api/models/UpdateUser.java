package api.models;

import api.client.ApiClientRequest;
import api.client.HTTPMethod;
import io.restassured.http.ContentType;
import static api.utils.Settings.baseUri;
import static api.utils.Settings.endpoint;

public class UpdateUser {

    private final String userId;
    private final Object payLoad;

    private UpdateUser(String userId,Object payLoad){
        this.userId = userId;
        this.payLoad = payLoad;
    }

    public static UpdateUser getUpdateUser(String userId,Object payLoad){
        return new UpdateUser(userId,payLoad);
    }

    public void sendRequest(){
        System.out.println(baseUri()+endpoint()+"/"+userId);
        ApiClientRequest.builder(baseUri(), endpoint()+"/"+userId, HTTPMethod.PUT)
                .contentType(ContentType.JSON)
                .body(payLoad.toString())
                .build()
                .sendRequest();
    }
}