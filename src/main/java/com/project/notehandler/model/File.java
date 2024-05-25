package com.project.notehandler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "fileType", unique = false, nullable = false)
    private String fileType;

    @Lob
    @Column(name = "fileData", columnDefinition = "MEDIUMBLOB")
    private byte[] fileData;
}
