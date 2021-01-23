package com.teestore.backend.api;

import com.teestore.backend.model.Images;
import com.teestore.backend.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("ImageAPI")
public class ImageAPI {

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private Environment environment;

    @GetMapping(value = "/getImages/{reference}")
    public ResponseEntity<List<Images>> getImages(@PathVariable String reference) throws Exception {
        try {
            List<Images> res = imagesService.getImagesByReference(reference);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/addImages")
    public ResponseEntity<List<String>> addImages(@RequestBody List<Images> imagesList) throws Exception {
        try {
            List<String> res = imagesService.addImages(imagesList);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeImages")
    public ResponseEntity<List<String>> removeImages(@RequestBody List<String> imagesIdList) throws Exception {
        try {
            List<String> res = imagesService.removeImages(imagesIdList);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeImage/{imageId}")
    public ResponseEntity<String> removeImage(@PathVariable String imageId) throws Exception {
        try {
            String res = imagesService.removeImage(imageId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @GetMapping(value = "/getImage/{imageId}")
    public ResponseEntity<Images> getImageById(@PathVariable String imageId) throws Exception {
        try {
            Images res = imagesService.getImageById(imageId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
