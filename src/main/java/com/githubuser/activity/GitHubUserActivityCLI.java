package com.githubuser.activity;

public class GitHubUserActivityCLI {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: github-activity <username>");
            return;
        }

        String username = args[0];
        GitHubService service = new GitHubService();

        try {
            service.fetchAndDisplayActivity(username);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}