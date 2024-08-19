package com.githubuser.activity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.githubuser.model.GitHubEvent;
import org.apache.hc.client5.http.fluent.Request;

import java.io.IOException;

public class GitHubService {

    private static final String GITHUB_API_URL = "https://api.github.com/users/%s/events";

    public void fetchAndDisplayActivity(String username) throws IOException {
        String url = String.format(GITHUB_API_URL, username);
        String response = Request.get(url).execute().returnContent().asString();

        ObjectMapper objectMapper = new ObjectMapper();
        GitHubEvent[] events = objectMapper.readValue(response, GitHubEvent[].class);

        for (GitHubEvent event : events) {
            System.out.println(event.getType() + " - " + event.getRepo().getName());
        }
    }

}
