package com.tutorial.keycloakbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    private Long id;

    @Column(name = "poster_path")
    private String poster_path;

    @Column(name = "adult")
    private boolean adult;

    @Column(name = "overview", length = 2048)
    private String overview;

    @Column(name = "release_date")
    private String release_date;

    @ElementCollection
    @CollectionTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre_id")
    private List<Integer> genre_ids;

    @Column(name = "original_title")
    private String original_title;

    @Column(name = "original_language")
    private String original_language;

    @Column(name = "title")
    private String title;

    @Column(name = "backdrop_path")
    private String backdrop_path;

    @Column(name = "popularity")
    private double popularity;

    @Column(name = "vote_count")
    private int vote_count;

    @Column(name = "video")
    private boolean video;

    @Column(name = "vote_average")
    private double vote_average;
}

