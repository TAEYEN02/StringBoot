package com.kor.rnBoard.dto;

import java.util.List;

import com.kor.rnBoard.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDTO {
    private int id;
    private String title;
    private String author;
    private String description;
    private String time;
    private int views;

    public PostDTO(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.description = entity.getDescription();
        this.time = entity.getTime();
        this.views = entity.getViews();
    }

    public static Post toEntity(PostDTO dto) {
        return Post.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .description(dto.getDescription())
                .time(dto.getTime() == null ? null : dto.getTime())
                .views(dto.getViews())
                .build();
    }
}
