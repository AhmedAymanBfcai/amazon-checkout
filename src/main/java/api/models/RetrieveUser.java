package api.models;

import api.client.ApiClientRequest;
import api.client.HTTPMethod;
import io.restassured.http.ContentType;
import static api.utils.Settings.baseUri;
import static api.utils.Settings.endpoint;

public class RetrieveUser {

    private final String userId;

    private RetrieveUser(String userId){
        this.userId = userId;
    }

    public static RetrieveUser getRetrieveUser(String userId){
        return new RetrieveUser(userId);
    }

    public void sendRequest(){
        System.out.println(baseUri()+endpoint()+"/"+userId);
        ApiClientRequest.builder(baseUri(), endpoint()+"/"+userId, HTTPMethod.GET)
                .contentType(ContentType.JSON)
                .build()
                .sendRequest();
    }
}