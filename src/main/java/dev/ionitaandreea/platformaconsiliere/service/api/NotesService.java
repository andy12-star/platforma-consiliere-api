package dev.ionitaandreea.platformaconsiliere.service.api;

import dev.ionitaandreea.platformaconsiliere.dto.response.NotesResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Notes;

import java.util.List;

public interface NotesService {

    Notes saveNote(Notes note);

    void deleteNote(Long noteId);

    List<NotesResponse> getAllNotesByUserId(Long userId);

}
