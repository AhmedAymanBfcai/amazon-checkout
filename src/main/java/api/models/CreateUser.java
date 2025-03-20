package api.models;

import api.client.ApiClientRequest;
import api.client.HTTPMethod;
import io.restassured.http.ContentType;
import static api.utils.Settings.baseUri;
import static api.utils.Settings.endpoint;

// It uses a factory pattern for instantiation and delegates the heavy lifting to ApiClientRequest
public class CreateUser {
    private final Object payLoad;

    private CreateUser(Object payLoad){
        this.payLoad = payLoad;
    }

    // Static Factory Method
    // Acts as a controlled entry point, replacing direct constructor access.
    // Role: Factory method to instantiate CreateUser, hiding the constructor.
    // Why Static: Allows creation without exposing the constructor publicly.

    // Q: Why use a factory method (getCreateUser) instead of a public constructor?
    // A: It encapsulates object creation, hides the constructor, and provides a clear, named entry point.
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
