package com.example.urlShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class URLService {
    private final URLRepository urlRepository;

    @Autowired
    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<URL> getAllURLs() {
        return urlRepository.findAll();
    }

    public String getFullURL(String tinyURL) {
        URL url = urlRepository.findURLByTinyURL(tinyURL).orElseThrow(() ->
                new IllegalStateException("URL with the ending " + tinyURL + " does not exist."));
        return url.getOriginalURL();
    }

    public URL getURL(String tinyURL) {
        URL url = urlRepository.findURLByTinyURL(tinyURL).orElseThrow(() ->
                new IllegalStateException("URL with the ending " + tinyURL + " does not exist."));

        return url;
    }

    //todo first check if the item is already there - (if not then hash) - and return newURL
    public URL tinyfySaveAndGetURL(String originalURL) {
        System.out.println(originalURL);
        String hash = hash(originalURL);
        Optional<URL> optionalURL = urlRepository.findURLByTinyURL(hash);
        URL newURL;
        if (optionalURL.isPresent()) {
            newURL = optionalURL.get();
        } else {
            newURL = new URL(originalURL, hash);
            urlRepository.save(newURL);
        }
        return newURL;
    }

    public String hash(String originalURL) {
        //todo implement hash
        return "11111";
    }
}
