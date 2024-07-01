package dev.ionitaandreea.platformaconsiliere.controller;


import dev.ionitaandreea.platformaconsiliere.dto.request.NotesRequest;
import dev.ionitaandreea.platformaconsiliere.entity.User;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.service.api.NotesService;
import dev.ionitaandreea.platformaconsiliere.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('users:read') or hasAuthority('medic:read')")
public class NotesController {

    private final NotesService notesService;
    private final UserServiceImpl userServiceImpl;

    @PostMapping()
    public ResponseEntity<?> saveNote(@RequestBody NotesRequest notesRequest) {
        User noteUser = userServiceImpl.getUserById(notesRequest.getUserId());
        notesService.saveNote(Mapper.toNotes(notesRequest, noteUser));
        return ResponseEntity.ok("Note saved successfully");
    }
    @DeleteMapping("{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Long noteId) {
        notesService.deleteNote(noteId);
        return ResponseEntity.status(200).body("Note deleted successfully!");
    }
    @GetMapping({"{userId}"})
    public ResponseEntity<?> getAllNotesForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(notesService.getAllNotesByUserId(userId));
    }
}
