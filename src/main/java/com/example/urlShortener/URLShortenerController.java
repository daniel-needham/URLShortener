package com.example.urlShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/")
public class URLShortenerController {

    private final URLService urlService;
    private String address;

    @Autowired
    public URLShortenerController(URLService urlService) {
        this.urlService = urlService;
        this.address = "http://localhost:8080/";
    }

    @GetMapping(path = "{tinyURL}")
    public ModelAndView getRedirectedURL(@PathVariable("tinyURL") String tinyURL) {
        String originalURL = urlService.getFullURL(tinyURL);
        return new ModelAndView("redirect:" + originalURL);
    }


    @GetMapping(path = "{tinyURL}/stats")
    @ResponseBody
    public URL getURLStats(@PathVariable("tinyURL") String tinyURL) {
        URL url = urlService.getURL(tinyURL);
        return url;
    }

    @PostMapping()
    public String postAndGetTinyURL(@RequestParam String originalURL) {
        URL url = urlService.tinyfySaveAndGetURL(originalURL);
        return address + url.getTinyURL();
    }

}
