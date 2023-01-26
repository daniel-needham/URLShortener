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
        URL newUrl = new URL(originalURL, hash(originalURL));
        urlRepository.save(newUrl);
        return newUrl;
    }

    public String hash(String originalURL) {
        //todo implement hash
        return originalURL + "11111";
    }
}
