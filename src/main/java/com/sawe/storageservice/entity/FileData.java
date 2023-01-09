package com.sawe.storageservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "FileData")
@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class FileData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String filePath;

}
