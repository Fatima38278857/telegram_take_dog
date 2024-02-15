package com.example.telegram_take_dog.service;

import com.example.telegram_take_dog.model.Dog;
import com.example.telegram_take_dog.model.PhotoAnimal;
import com.example.telegram_take_dog.repository.DogRepository;
import com.example.telegram_take_dog.repository.PhotoAnimalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
/**
 * Сервер который
 * добавляет фотографии животных
 */
@Service
@Transactional
public class PhotoAnimalService {

    private final DogRepository dogRepository;
    private final PhotoAnimalRepository photoAnimalRepository;
    private final String dogDir;

    public PhotoAnimalService(DogRepository dogRepository, PhotoAnimalRepository photoAnimalRepository, @Value("${path.to.photoAnimal.folder}") String dogDir) {
        this.dogRepository = dogRepository;
        this.photoAnimalRepository = photoAnimalRepository;
        this.dogDir = dogDir;
    }

    /**
     *
     * @param dogId
     * @param avatarFile
     * @throws IOException
     */
    public void uploadAvatar(Long dogId, MultipartFile avatarFile) throws IOException {
        Dog dog = dogRepository.getById(dogId);
        Path filePath = Path.of(dogDir, dog + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        PhotoAnimal photoAnimal = new PhotoAnimal();
        photoAnimal.setDog(dog);
        photoAnimal.setFilePath(filePath.toString());
        photoAnimal.setFileSize(avatarFile.getSize());
        photoAnimal.setMediaType(avatarFile.getContentType());
        photoAnimal.setData(avatarFile.getBytes());
        photoAnimalRepository.save(photoAnimal);
    }

    /**
     *
     * @param fileName
     * @return
     */
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);

    }

    /**
     *
     * @param id
     * @return
     */
    public PhotoAnimal findDog(Long id) {
        return photoAnimalRepository.findById(id).get();

    }

    /**
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<PhotoAnimal> getListsPhotoAnimals(Integer pageNumber, Integer pageSize) {
        return photoAnimalRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
    }
}
