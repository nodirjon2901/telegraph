package uz.nt.telegraphclone.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.nt.telegraphclone.domain.entity.enums.Permission;
import uz.nt.telegraphclone.domain.entity.enums.UserRole;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {

    private UUID id;

    private String fullName;

    private String username;

    private String password;

    private UserRole role;

    private Boolean isBlock;

    private Set<Permission> permissions;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
