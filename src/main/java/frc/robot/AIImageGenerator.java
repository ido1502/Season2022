// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * AIImageGenerator provides AI-powered image generation by relying on an
 * external (outsourced) AI image generation service (OpenAI DALL-E).
 *
 * <p>Set your API key in {@link Constants#OPENAI_API_KEY} before using this class.
 */
public class AIImageGenerator {

    private static final String API_URL = "https://api.openai.com/v1/images/generations";

    private final HttpClient httpClient;
    private final String apiKey;

    /**
     * Creates an AIImageGenerator using the API key defined in {@link Constants}.
     */
    public AIImageGenerator() {
        this(Constants.OPENAI_API_KEY);
    }

    /**
     * Creates an AIImageGenerator with the given API key.
     *
     * @param apiKey the OpenAI API key
     * @throws IllegalArgumentException if the API key is null or empty
     */
    public AIImageGenerator(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException(
                    "AIImageGenerator: OPENAI_API_KEY is not set. "
                    + "Set the OPENAI_API_KEY environment variable before using this class.");
        }
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
    }

    /**
     * Generates an image URL for the given text prompt using the DALL-E model.
     *
     * @param prompt  the text description of the image to generate (must not be null or empty)
     * @param model   the DALL-E model to use (e.g. "dall-e-3" or "dall-e-2")
     * @param size    the image size (e.g. "1024x1024", "512x512")
     * @return the URL of the generated image, or {@code null} if generation failed
     * @throws IOException              if an I/O error occurs
     * @throws InterruptedException     if the request is interrupted
     * @throws IllegalArgumentException if the prompt is null or empty
     */
    public String generateImageUrl(String prompt, String model, String size)
            throws IOException, InterruptedException {

        if (prompt == null || prompt.trim().isEmpty()) {
            throw new IllegalArgumentException("AIImageGenerator: prompt must not be null or empty");
        }

        String requestBody = buildRequestBody(prompt, model, size);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseImageUrl(response.body());
        } else {
            System.err.println("AIImageGenerator: API request failed with status "
                    + response.statusCode());
            return null;
        }
    }

    /**
     * Generates an image URL for the given prompt using default settings
     * (dall-e-3, 1024x1024).
     *
     * @param prompt the text description of the image to generate
     * @return the URL of the generated image, or {@code null} if generation failed
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the request is interrupted
     */
    public String generateImageUrl(String prompt) throws IOException, InterruptedException {
        return generateImageUrl(prompt, Constants.OPENAI_IMAGE_MODEL, Constants.OPENAI_IMAGE_SIZE);
    }

    /**
     * Escapes a string value for safe inclusion in a JSON string literal.
     */
    private String escapeJson(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    /**
     * Builds the JSON request body for the OpenAI images API.
     */
    private String buildRequestBody(String prompt, String model, String size) {
        return "{\"model\":\"" + escapeJson(model) + "\","
                + "\"prompt\":\"" + escapeJson(prompt) + "\","
                + "\"n\":1,"
                + "\"size\":\"" + escapeJson(size) + "\"}";
    }

    /**
     * Parses the image URL from the OpenAI API JSON response.
     *
     * <p>Expected response shape:
     * <pre>{"data":[{"url":"https://..."}]}</pre>
     */
    private String parseImageUrl(String responseBody) {
        // Simple extraction without adding a JSON library dependency
        int urlKeyIndex = responseBody.indexOf("\"url\"");
        if (urlKeyIndex == -1) {
            System.err.println("AIImageGenerator: could not find 'url' field in API response");
            return null;
        }
        int colonIndex = responseBody.indexOf(":", urlKeyIndex);
        if (colonIndex == -1) {
            return null;
        }
        int startQuote = responseBody.indexOf("\"", colonIndex + 1);
        if (startQuote == -1) {
            return null;
        }
        int endQuote = responseBody.indexOf("\"", startQuote + 1);
        if (endQuote == -1) {
            return null;
        }
        return responseBody.substring(startQuote + 1, endQuote);
    }
}
