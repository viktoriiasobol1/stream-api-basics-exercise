package com.epam.rd.autotask.stream.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieQueries {

    private final List<String> movies;

    public MovieQueries(List<String> movies) {
        if (movies == null) {
            throw new IllegalArgumentException("Movies list must not be null");
        }
        this.movies = new ArrayList<>(movies);
    }

    public long getNumberOfMovies() {
        return movies.stream().count();
    }

    public long getNumberOfMoviesThatStartsWith(String start) {
        return movies.stream()
            .filter(title -> title.startsWith(start))
            .count();
    }

    public long getNumberOfMoviesThatStartsWithAndEndsWith(String start, String end) {
        return movies.stream()
            .filter(title -> title.startsWith(start) && title.endsWith(end))
            .count();
    }

    public List<Integer> getLengthOfTitleOfMovies() {
        return movies.stream()
            .map(String::length)
            .collect(Collectors.toList());
    }

    public int getNumberOfLettersInShortestTitle() {
        return movies.stream()
            .mapToInt(String::length)
            .min()
            .orElseThrow(() -> new IllegalArgumentException("Movies list is empty"));
    }

    public Optional<String> getFirstTitleThatContainsThreeWords() {
        return movies.stream()
            .filter(title -> wordCount(title) == 3)
            .findFirst();
    }

    public List<String> getFirstFourTitlesThatContainAtLeastTwoWords() {
        return movies.stream()
            .filter(title -> wordCount(title) >= 2)
            .limit(4)
            .collect(Collectors.toList());
    }

    public void printAllTitleToConsole() {
        movies.stream().forEach(System.out::println);
    }

    private static int wordCount(String title) {
        String trimmed = title.trim();
        return trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
    }
}