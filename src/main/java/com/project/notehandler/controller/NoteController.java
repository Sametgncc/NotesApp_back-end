package com.project.notehandler.controller;

import com.project.notehandler.model.Note;
import com.project.notehandler.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    /* @Autowired*/
    private final NoteService noteService;



    @PostMapping()
    public Note createNote(@RequestBody Map<String, Object> requestData) {
        Map<String, String> noteMap = (Map<String, String>) requestData.get("note");
        List<Integer> fileIDs = (List<Integer>) requestData.get("fileID_list");

        String folderName = noteMap.get("folderName");
        String noteText = noteMap.get("noteText");

        return noteService.saveWithFiles(folderName,noteText ,fileIDs);

    }
    @PutMapping(("/updateWithFileList/{noteID}"))
    public Note updateWithFiles(@RequestBody Map<String, Object> requestData,@PathVariable Long noteID) {
        Map<String, String> noteMap = (Map<String, String>) requestData.get("note");
        List<Integer> fileIDs = (List<Integer>) requestData.get("fileID_list");

        String folderName = noteMap.get("folderName");
        String noteText = noteMap.get("noteText");

        return noteService.updateWithFiles(noteID,folderName,noteText ,fileIDs);

    }

    @PutMapping("/{noteID}")
    public Note update(@PathVariable Long noteID, @RequestBody Note newNote ) {
        return  noteService.update(noteID,newNote);
    }

    @DeleteMapping(("/{noteID}"))
    public Note updateNote(@PathVariable Long noteID)  {
        return noteService.delete(noteID);

    }
}
