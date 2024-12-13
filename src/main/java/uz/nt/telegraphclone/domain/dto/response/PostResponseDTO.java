package uz.nt.telegraphclone.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponseDTO {

    private UUID id;

    private String author;

    private String title;

    private String content;

    private UUID ownerId;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
