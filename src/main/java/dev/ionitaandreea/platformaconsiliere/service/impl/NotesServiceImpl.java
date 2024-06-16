package dev.ionitaandreea.platformaconsiliere.service.impl;

import dev.ionitaandreea.platformaconsiliere.dto.response.NotesResponse;
import dev.ionitaandreea.platformaconsiliere.entity.Notes;
import dev.ionitaandreea.platformaconsiliere.mapper.Mapper;
import dev.ionitaandreea.platformaconsiliere.repository.NotesRepository;
import dev.ionitaandreea.platformaconsiliere.service.api.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotesRepository notesRepository;

    @Override
    public Notes saveNote(Notes note) {
        return notesRepository.save(note);
    }

    @Override
    public void deleteNote(Long noteId) {
        notesRepository.deleteById(noteId);
    }

    @Override
    public List<NotesResponse> getAllNotesByUserId(Long userId) {
        return notesRepository.findAllByUser_Id(userId).stream()
                .map(Mapper::toNotesResponse).toList();
    }


}
