package com.project.notehandler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "folderName", nullable = false)
    private String folderName;


    @Column(name = "noteTitle", nullable = false)
    private String noteTitle;

    @Column(name = "noteText", nullable = false)
    private String noteText;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "files", nullable = true, unique = false)
    private List<File> files = new ArrayList<>();

}
