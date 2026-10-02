package com.cinebook.cinebook.repository;

import com.cinebook.cinebook.entity.Movie;
import com.cinebook.cinebook.enums.MovieStatus;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecifications {

    public static Specification<Movie> hasTitleContaining(String title) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Movie> hasStatus(MovieStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<Movie> hasGenreId(Long genreId) {
        return (root, query, cb) -> {
            query.distinct(true);
            return cb.equal(root.join("genres").get("id"), genreId);
        };
    }
}
