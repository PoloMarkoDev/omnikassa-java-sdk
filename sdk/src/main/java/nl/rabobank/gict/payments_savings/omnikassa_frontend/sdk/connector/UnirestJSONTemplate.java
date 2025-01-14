package nl.rabobank.gict.payments_savings.omnikassa_frontend.sdk.connector;

import kong.unirest.GetRequest;
import kong.unirest.RequestBodyEntity;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import nl.rabobank.gict.payments_savings.omnikassa_frontend.sdk.model.JsonConvertible;

import java.util.Map;

class UnirestJSONTemplate {
    private final String baseURL;

    UnirestJSONTemplate(String baseURL) {
        this.baseURL = baseURL;
    }

    public JSONObject get(String path, String token) {
        GetRequest jsonResponse = Unirest.get(baseURL + "/" + path)
                                         .header("Content-type", "application/json")
                                         .header("Authorization", "Bearer " + token);

        return jsonResponse.asJson().getBody().getObject();
    }

    public JSONObject post(String path, JsonConvertible body, String token) {
        RequestBodyEntity requestBodyEntity = Unirest.post(baseURL + "/" + path)
                                                     .header("Content-type", "application/json")
                                                     .header("Authorization", "Bearer " + token)
                                                     .body(body.asJson());

        return requestBodyEntity.asJson().getBody().getObject();
    }

    public JSONObject postWithHeader(String path, JsonConvertible body, Map<String, String> headers, String token) {
        headers.put("Content-type", "application/json");
        headers.put("Authorization", "Bearer " + token);
        RequestBodyEntity requestBodyEntity = Unirest.post(baseURL + "/" + path)
                                                     .headers(headers)
                                                     .body(body.asJson());

        return requestBodyEntity.asJson().getBody().getObject();
    }
}
