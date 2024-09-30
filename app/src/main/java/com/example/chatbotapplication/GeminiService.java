package com.example.chatbotapplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;

public class GeminiService {

    private static final String API_BASE_URL = "https://api.gemini.com/v1/chat/completions"; // Adjust as needed
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private String apiKey;

    public GeminiService(String apiKey) {
        this.apiKey = apiKey;
    }

    // Method to make a request to Gemini API
    public String getResponseFromGemini(String message) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Create the JSON request body for Gemini chat API
        String jsonBody = String.format("{\"model\":\"gemini-model-name\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}]}", message); // Adjust model name as needed
        RequestBody requestBody = RequestBody.create(jsonBody, JSON);

        // Create the request with the correct headers
        Request request = new Request.Builder()
                .url(API_BASE_URL)
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + apiKey) // Gemini API Key in header
                .build();

        // Execute the request and get the response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response.code() + ": " + response.message());
            }

            // Parse the response JSON and extract the bot's response
            String responseBody = response.body().string();

            JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
            if (jsonResponse.has("choices") && jsonResponse.getAsJsonArray("choices").size() > 0) {
                String botResponse = jsonResponse.getAsJsonArray("choices")
                        .get(0).getAsJsonObject()
                        .get("message").getAsJsonObject()
                        .get("content").getAsString();
                return botResponse.trim();  // Return the generated text from the API
            } else {
                return "Sorry, I couldn't generate a response."; // Handle the case where no choices are found
            }
        }
    }
}
