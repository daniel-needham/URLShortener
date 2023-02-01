package com.example.urlShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class URLService {
    private final URLRepository urlRepository;
    private final MessageDigest messageDigest;
    private int urlSize = 4;

    @Autowired
    public URLService(URLRepository urlRepository) throws NoSuchAlgorithmException {
        this.urlRepository = urlRepository;
        this.messageDigest = MessageDigest.getInstance("MD5");
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
        URL newURL = null;

        String hash = hash(originalURL, urlSize);
        String tinyURL = hash.substring(0,urlSize);
        Optional<URL> optionalURL = urlRepository.findURLByTinyURL(tinyURL);
        int currentUrlSize = urlSize;
        while (newURL == null && currentUrlSize < 32) {
            if (optionalURL.isEmpty()) {
                newURL = new URL(originalURL, tinyURL);
                urlRepository.save(newURL);
                break;
            }
            if (optionalURL.get().getOriginalURL().equals(originalURL)) {
                newURL = optionalURL.get();
                break;
            }
            tinyURL = hash.substring(0, ++currentUrlSize);
            optionalURL = urlRepository.findURLByTinyURL(tinyURL);

        }
        return newURL;
    }

    public String hash(String originalURL, int length) {
        byte[] urlBytes = originalURL.getBytes();
        messageDigest.update(urlBytes);
        byte[] digest = messageDigest.digest();
        String fullHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return fullHash;
    }
}
