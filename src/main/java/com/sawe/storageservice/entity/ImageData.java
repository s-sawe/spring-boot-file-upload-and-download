package com.sawe.storageservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ImageData")
@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob //annotation to store any binary format in the DB
    @Column(name = "imageData", length = 1000)
    private byte[] imageData;

}
