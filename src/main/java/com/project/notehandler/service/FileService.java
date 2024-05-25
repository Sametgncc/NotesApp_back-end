package com.project.notehandler.service;

import com.project.notehandler.Exceptions.NotFoundException;
import com.project.notehandler.model.File;
import com.project.notehandler.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Scope(value = "singleton")
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileRepository fileRepository;

    public File save(File file) {
        return fileRepository.save(file);
    }

    public File findById(Long id) {
        Optional<File> fileOptional = fileRepository.findById(id);

        if (fileOptional.isPresent()) {
            File file = fileOptional.get();
            return file;
        }
        else {
            throw new NotFoundException("Dosya bulunamadı.");
        }
    }

    public File delete(Long id) {
        Optional<File> fileOptional = fileRepository.findById(id);

        if (fileOptional.isPresent()) {
            File file = fileOptional.get();
            fileRepository.delete(file);
            return file;
        }
        else {
            throw new NotFoundException("Dosya bulunamadı.");
        }

    }
}
