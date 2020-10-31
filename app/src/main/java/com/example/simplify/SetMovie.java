package com.example.simplify;

public class SetMovie {
    String title,rating,critic,directed;
    String image;
        SetMovie(String title, String image, String rating, String critic, String directed) {
    this.title = title;
    this.image = image;
    this.rating = rating;
    this.critic = critic;
    this.directed = directed;
        }

    public String getCritic() {
        return critic;
    }

    public String getDirected() {
        return directed;
    }

    public String getImage() {
        return image;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }
}
