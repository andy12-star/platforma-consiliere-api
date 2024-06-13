package dev.ionitaandreea.platformaconsiliere.dto.response;

import dev.ionitaandreea.platformaconsiliere.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {

    private Long id;

    private RoleName name;

    private List<String> permissions;
}
