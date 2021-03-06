package finalproject.ninegag.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import finalproject.ninegag.model.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReadyCommentDTO {

    @NotNull
    private long id;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datePosted;
    @NotNull
    private String text;
    private String imageUrl;
    @NotNull
    private long postId;
    @NotNull
    private ReadyUserDTO commentOwner;
    private ChildCommentDTO parentComment;

    public ReadyCommentDTO(Comment comment){
        setId(comment.getId());
        setText(comment.getText());
        setDatePosted(comment.getDatePosted());
        if(comment.getImageUrl() != null){
            setImageUrl(comment.getImageUrl());
        }
        setImageUrl(comment.getImageUrl());
        setPostId(comment.getPost().getId());
        setCommentOwner(comment.getUser().toUserDTO());
        if(comment.getParentComment() != null) {
            setParentComment(comment.getParentComment().toChildCommentDTO());//todo
        }
    }

    public ReadyCommentDTO(Comment comment, ReadyUserDTO userDTO) {
        setId(comment.getId());
        setText(comment.getText());
        setDatePosted(comment.getDatePosted());
        if(comment.getImageUrl() != null){
            setImageUrl(comment.getImageUrl());
        }
        setImageUrl(comment.getImageUrl());
        setPostId(comment.getPost().getId());
        setCommentOwner(userDTO);
        if(comment.getParentComment() != null) {
            //ChildComment DTO is a Comment DTO without parentComment
            setParentComment(comment.getParentComment().toChildCommentDTO());
        }
    }

}
