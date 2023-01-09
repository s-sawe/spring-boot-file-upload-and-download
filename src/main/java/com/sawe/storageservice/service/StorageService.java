package com.sawe.storageservice.service;


import com.sawe.storageservice.entity.ImageData;
import com.sawe.storageservice.repository.StorageRepository;
import com.sawe.storageservice.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {

       ImageData imageData = repository.save(ImageData.builder()
               .name(file.getOriginalFilename())
               .type(file.getContentType())
               .imageData(ImageUtils.compressImage(file.getBytes())).build());

       if (imageData != null){
           return "File Uploaded Successfully : " +file.getOriginalFilename();
       }
       return null;
    }

    public byte[] dowloadImage(String imageName){
        Optional<ImageData> databaseImageData = repository.findByName(imageName);
        byte[] image = ImageUtils.decompressImage(databaseImageData.get().getImageData());
        return image;
    }

}
