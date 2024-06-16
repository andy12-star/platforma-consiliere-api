package dev.ionitaandreea.platformaconsiliere.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotesResponse {

    private Long id;

    private String title;

    private String  notes;

    private LocalDateTime createdAt;
}
