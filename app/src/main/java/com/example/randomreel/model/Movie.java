package com.example.randomreel.model;

import java.util.List;

public class Movie {
    private String title;
    private String overview;
    private String release_date;
    private List<Integer> genre_ids;
    private double vote_average;
    private int runtime;

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public List<Integer> getGenreIds() {
        return genre_ids;
    }

    public void setGenreIds(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
}
