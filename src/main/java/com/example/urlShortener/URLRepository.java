package com.example.urlShortener;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLRepository  extends JpaRepository<URL, Long> {
    Optional<URL> findURLByTinyURL(String tinyURL);

}
