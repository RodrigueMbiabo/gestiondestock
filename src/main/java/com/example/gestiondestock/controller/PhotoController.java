package com.example.gestiondestock.controller;

import com.example.gestiondestock.controller.api.PhotoApi;
import com.example.gestiondestock.services.strategy.StrategyPhotoContext;
import com.flickr4java.flickr.FlickrException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotoController implements PhotoApi {


    private final StrategyPhotoContext strategyPhotoContext;

    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @Override
    public Object savePhoto(String context, Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return strategyPhotoContext.savePhoto(context, id, photo.getInputStream(), title);
    }
}
