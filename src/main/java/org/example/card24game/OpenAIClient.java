package org.example.card24game;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class OpenAIClient {
    public static String getSolutionFromOpenAI(int[] cardValues) throws IOException {
        int retryCount = 0;
        while (retryCount < 5) {
            try {

                URL url = new URL("https://api.openai.com/v1/chat/completions");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", "Bearer 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");

                int responseCode = connection.getResponseCode();

                if (responseCode == 429) {

                    System.out.println("Rate limited, retrying...");
                    retryCount++;
                    TimeUnit.SECONDS.sleep((long) Math.pow(2, retryCount));
                } else if (responseCode == 200) {

                    return "AI Solution";
                } else {

                    throw new IOException("Error: " + responseCode);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                throw new IOException("Failed to fetch solution");
            }
        }
        throw new IOException("Rate limit exceeded. Try again later.");
    }
}