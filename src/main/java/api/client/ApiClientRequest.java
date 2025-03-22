package api.client;

import api.core.Base;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class ApiClientRequest {
    private final String baseUri;
    private final String endpoint;
    private final HTTPMethod method;
    private final Map<String,String> headers;
    private final Map<String,String> queryParams;
    private final Map<String,String> pathParams;
    private final Object body;
    private final ContentType contentType;

    private ApiClientRequest(ApiRequestBuilder apiRequestBuilder){
        this.baseUri = apiRequestBuilder.baseUri;
        this.endpoint = apiRequestBuilder.endpoint;
        this.method = apiRequestBuilder.method;
        this.headers = apiRequestBuilder.headers;
        this.queryParams = apiRequestBuilder.queryParams;
        this.pathParams = apiRequestBuilder.pathParams;
        this.body = apiRequestBuilder.body;
        this.contentType = apiRequestBuilder.contentType;
    }

    public static ApiRequestBuilder builder(String baseUri,String endpoint , HTTPMethod method){
        return new ApiRequestBuilder(baseUri, endpoint, method);
    }

    public void sendRequest(){
        // Set the base URI
        RestAssured.baseURI = baseUri;

        // Initialize the RequestSpecification
        RequestSpecification requestSpec = RestAssured.given();

        // Set the endpoint
        requestSpec.basePath(endpoint);

        // Optional: Add headers if provided
        if (headers != null) {
            requestSpec.headers(headers);
        }

        // Optional: Add query parameters if provided
        if (queryParams != null) {
            requestSpec.queryParams(queryParams);
        }

        // Optional: Add path parameters if provided
        if (pathParams != null) {
            requestSpec.pathParams(pathParams);
        }

        // Optional: Add the request body if provided
        if (body != null) {
            requestSpec.body(body);
        }

        // Optional: Set the content type
        if (contentType != null) {
            requestSpec.contentType(contentType);
        }

        // Execute the request based on the method
        switch (method) {
            case GET -> Base.response = requestSpec.when().get();
            case POST -> Base.response = requestSpec.when().post();
            case PUT -> Base.response = requestSpec.when().put();
            case DELETE -> Base.response = requestSpec.when().delete();
            default -> throw new IllegalArgumentException("Unsupported HTTP Method");
        }
    }

    public static class ApiRequestBuilder {

        //Mandatory fields
        private final String baseUri;
        private final String endpoint;
        private final HTTPMethod method;

        public ApiRequestBuilder(String baseUri,String endpoint,HTTPMethod method){
            this.baseUri = baseUri;
            this.endpoint = endpoint;
            this.method = method;
        }

        //Optional fields
        private Map<String,String> headers;
        private Map<String,String> queryParams;
        private Map<String,String> pathParams;
        private Object body;
        private ContentType contentType;

        public ApiRequestBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this; // To enable fluent chaining in the builder pattern.
        }

        public ApiRequestBuilder queryParams(Map<String, String> queryParams) {
            this.queryParams = queryParams;
            return this;
        }

        public ApiRequestBuilder pathParams(Map<String, String> pathParams) {
            this.pathParams = pathParams;
            return this;
        }

        public ApiRequestBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public ApiRequestBuilder contentType(ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        // Static method to create an ApiRequestBuilder instance. Used to start the request-building process.
        public ApiClientRequest build(){
            return new ApiClientRequest(this);
        }
    }
}