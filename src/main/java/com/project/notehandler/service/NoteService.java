package com.project.notehandler.service;

import com.project.notehandler.Exceptions.NotFoundException;
import com.project.notehandler.model.File;
import com.project.notehandler.model.Note;
import com.project.notehandler.repository.FileRepository;
import com.project.notehandler.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "singleton")
@RequiredArgsConstructor
@Slf4j
public class NoteService {
    private final NoteRepository noteRepository;
    private final FileRepository fileRepository;

    public Note save(Note note) {

        return noteRepository.save(note);

    }

    public Note saveWithFiles(String folderName, String noteText, List<Integer> fileIDs) {

        List<File> files = new ArrayList<File>();
        for (Integer fileID : fileIDs) {
            Optional<File> file = fileRepository.findById(Long.valueOf(fileID));
            if (file.isPresent()) {
                files.add(file.get());
            }
        }
        Note note = new Note();
        note.setNoteText(noteText);
        note.setFolderName(folderName);
        note.setFiles(files);
        return noteRepository.save(note);

    }

    public Note updateWithFiles(Long noteID, String folderName, String noteText, List<Integer> fileIDs)  {
        Optional<Note> foundNote=noteRepository.findById(noteID);
        if (foundNote.isPresent()) {
            Note note=foundNote.get();
            if(!folderName.isEmpty()) {
                note.setFolderName(folderName);
            }
            if(!noteText.isEmpty()) {
                note.setNoteText(noteText);
            }
            if(!fileIDs.isEmpty()) {
                List<File> files = new ArrayList<File>();
                for (Integer fileID : fileIDs) {
                    Optional<File> file = fileRepository.findById(Long.valueOf(fileID));
                    if (file.isPresent()) {
                        files.add(file.get());
                    }
                }
                note.setFiles(files);
            }
            return noteRepository.save(note);
        }
        else{
            throw new NotFoundException("Not bulunamadı.");
        }


    }

    public Note delete(Long noteID) {
        Optional<Note> foundNote=noteRepository.findById(noteID);
        if (foundNote.isPresent()) {
            Note note=foundNote.get();
            noteRepository.delete(note);
            return note;
        }
        else{
            throw new NotFoundException("Not bulunamadı.");
        }

    }

    public Note update(Long noteID, Note newNote) {
        Optional<Note> foundNote=noteRepository.findById(noteID);
        if (foundNote.isPresent()) {
            Note note=foundNote.get();
            note.setNoteText(newNote.getNoteText());
            note.setFolderName(newNote.getFolderName());
            note.setFiles(newNote.getFiles());
            return noteRepository.save(note);
        }
        else {
            throw new NotFoundException("Not bulunamadı.");
        }
    }
}
