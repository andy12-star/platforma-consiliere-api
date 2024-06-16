package dev.ionitaandreea.platformaconsiliere.dto.request;

import dev.ionitaandreea.platformaconsiliere.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotesRequest {

    @NotNull(message = "Id is required")
    private Long id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Notes are required")
    private String  notes;

    @NotNull(message = "UserId is required")
    private Long userId;
}
