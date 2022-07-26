package com.project.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
public class BigComment {
    @Id
    @GeneratedValue
    @Column(name = "big_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(columnDefinition = "TEXT")
    private String bigComment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getComments().remove(this);
        }
        this.member = member;
        member.getBigComments().add(this);
    }
    public void setComment(Comment comment) {
        if (this.comment != null) {
            this.comment.getBigComments().remove(this);
        }
        this.comment = comment;
        comment.getBigComments().add(this);
    }
}
