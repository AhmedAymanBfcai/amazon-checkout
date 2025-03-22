package api.models;

import api.client.ApiClientRequest;
import api.client.HTTPMethod;
import io.restassured.http.ContentType;
import static api.utils.Settings.baseUri;
import static api.utils.Settings.endpoint;

public class CreateUser {
    private final Object payLoad;

    private CreateUser(Object payLoad){
        this.payLoad = payLoad;
    }

    public static CreateUser getCreateUser(Object payLoad){
        return new CreateUser(payLoad);
    }

    public void sendRequest(){
        ApiClientRequest.builder(baseUri(), endpoint(), HTTPMethod.POST)
                .contentType(ContentType.JSON)
                .body(payLoad.toString())
                .build()
                .sendRequest();
    }
}