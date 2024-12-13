package uz.nt.telegraphclone.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostCreateDTO {

    private String author;

    private String title;

    private String content;

    private UUID ownerId;

}
