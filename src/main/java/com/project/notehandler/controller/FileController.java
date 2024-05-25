package com.project.notehandler.controller;

import com.project.notehandler.model.File;
import com.project.notehandler.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {


    /* @Autowired*/
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("file_type") String fileType) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dosya yüklenmedi.");
        }
        File fileModel = new File();
        try {
            fileModel.setFileData(file.getBytes());
            fileModel.setFileType(fileType);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dosya yüklenirken hata oluştu. Dosya 16 MB'dan büyük olabilir.");
        }
        File uploadedFile=fileService.save(fileModel);
        return ResponseEntity.ok("Dosya yüklendi. ID: " + uploadedFile.getId());
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        File file = fileService.findById(id);


        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; fileType=\"" + file.getFileType() + "\"")
                .body(file.getFileData());
    }
    @GetMapping("/{id}")
    public File getFile(@PathVariable Long id)  {
        return fileService.findById(id);

    }
    @DeleteMapping("/{id}")
    public File deleteFile(@PathVariable Long id)  {
        return fileService.delete(id);

    }
}
