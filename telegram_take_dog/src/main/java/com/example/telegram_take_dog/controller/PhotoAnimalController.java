package com.example.telegram_take_dog.controller;


import com.example.telegram_take_dog.model.PhotoAnimal;
import com.example.telegram_take_dog.service.PhotoAnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 *
 */
@RestController
@RequestMapping("photoAnima")
@Tag(name = "фатографии собак")
public class PhotoAnimalController {

    private final PhotoAnimalService photoAnimalService;

    public PhotoAnimalController(PhotoAnimalService photoAnimalService) {
        this.photoAnimalService = photoAnimalService;
    }
    @Operation(
            description = "Добавления фото по идентификатору сабаки"
    )
    @PostMapping(value = "/{dogId}/photoAnimal", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable @Parameter(description = "Идентификатор собаки")Long dogId,
                                               @RequestParam  @Parameter(description = "Фотография") MultipartFile photoAnimal) throws IOException {
        photoAnimalService.uploadAvatar(dogId, photoAnimal);
        return ResponseEntity.ok().build();
    }
    @Operation(
            description = "Получить собаку с фотографией из диска"
    )
    @GetMapping(value = "/{id}/photoAnimal-from-db")
    public ResponseEntity<byte[]> downloadPhotoAnimal(@PathVariable @Parameter(description = "Идентификатор собаки") Long id) {
        PhotoAnimal photoAnimal = photoAnimalService.findDog(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(photoAnimal.getMediaType()));
        headers.setContentLength(photoAnimal.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(photoAnimal.getData());
    }

    @Operation(
            description = "Получить собаку с фотографией из файла"
    )
    @GetMapping(value = "/{id}/photoAnimal-from-file")
    public void downloadPhotoAnimal(@PathVariable @Parameter(description = "Идентификатор собаки") Long id, HttpServletResponse response) throws IOException {
        PhotoAnimal photoAnimal = photoAnimalService.findDog(id);
        Path path = Path.of(photoAnimal.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(photoAnimal.getMediaType());
            response.setContentLength((int) photoAnimal.getFileSize());
            is.transferTo(os);
        }
    }
}
