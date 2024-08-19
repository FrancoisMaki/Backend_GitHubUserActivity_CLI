package com.githubuser.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitHubUserActivityCLI {

    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: github-activity <username>");
            return;
        }

        String username = args[0];
        fetchGitHubActivity(username);
    }

    private static void fetchGitHubActivity(String username) {
        try {
            URL url = new URL(GITHUB_API_URL + username + "/events");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();


                System.out.println("Recent activity for user " + username + ":");
                System.out.println(content.toString());
            } else {
                System.out.println("Failed to fetch activity. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}