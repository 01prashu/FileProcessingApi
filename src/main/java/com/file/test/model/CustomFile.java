package com.file.test.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="Files")
@Entity
public class CustomFile {
    @Column(name = "fileId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @Column(name="fileName")
    String name;
    @Column(name="fileSize")
    long size;
    @Column(name = "fileExtension")
    String ext;
    @Column(name="insertionDateTime")
    LocalDateTime insertTime;
}
