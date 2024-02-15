package com.example.telegram_take_dog.model;

import jakarta.persistence.*;


import java.util.Arrays;
import java.util.Objects;
/**
 * Класс
 */
@Entity(name = "photo_animal")
public class PhotoAnimal {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath; // В нем будем хранить путь до файла на  диске.
    private long fileSize; // Это поле содержит информацию о размере файла в байтах.
    private String mediaType; // Тип файла.
    @Lob
    private byte[] data; // В этом поле хранится сама информация о файле, представленная в массиве байтов
    @OneToOne
    private Dog dog;

    public PhotoAnimal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoAnimal that = (PhotoAnimal) o;
        return fileSize == that.fileSize && Objects.equals(id, that.id) && Objects.equals(filePath, that.filePath) && Objects.equals(mediaType, that.mediaType) && Arrays.equals(data, that.data) && Objects.equals(dog, that.dog);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType, dog);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
