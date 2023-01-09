package com.sawe.storageservice.service;


import com.sawe.storageservice.entity.FileData;
import com.sawe.storageservice.entity.ImageData;
import com.sawe.storageservice.repository.FileDataRepository;
import com.sawe.storageservice.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileStorageService {

    @Autowired
    private FileDataRepository fileRepository;

    private final String FOLDER_PATH = "/Users/KEN20961/Desktop/MyFiles/"; //the path to the folder created on local-machine or server;
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {

      String filePath = FOLDER_PATH + file.getOriginalFilename();
      FileData fileData = fileRepository.save(FileData.builder()
              .name(file.getOriginalFilename())
              .type(file.getContentType())
              .filePath(filePath).build());

      file.transferTo(new File(filePath));

        if (fileData != null){
            return "File Uploaded Successfully : " +filePath;
        }
        return null;
    }

    public byte[] dowloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath(); //we're getting the path of the file stored in the db
        byte[] image = Files.readAllBytes(new File(filePath).toPath()); //converting the path to byte array
        return image;
    }
}
