package uz.nt.telegraphclone.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostEntity extends BaseEntity{

    private String author;

    private String title;

    @Column(length = 10000)
    private String content;

    @ManyToOne
    private UserEntity owner;

}
