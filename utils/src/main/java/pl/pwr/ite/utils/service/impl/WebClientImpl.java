package pl.pwr.ite.utils.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.pwr.ite.utils.service.WebClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebClientImpl implements WebClient {
    private static WebClient INSTANCE = null;
    private static final String BASE_API_URL = "https://api.gios.gov.pl/pjp-api/rest";
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public WebClientImpl() {
        client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public <R> R get(String path, Class<R> responseType) throws IOException, InterruptedException {
        var request = buildRequest(path);
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), responseType);
    }

    private HttpRequest buildRequest(String path) {
        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_API_URL.concat(path)))
                .GET()
                .build();
    }

    public static WebClient getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new WebClientImpl();
        }
        return INSTANCE;
    }


}
