package com.example.sns.model.entity;

import com.example.sns.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"post\"")
@Getter
@Setter
//@SQLDelete(sql = "UPDATE \"user\" SET deleted_at = NOW() where id=?")
//@Where(clause = "delete_at is NULL")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

//    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
//    private List<CommentEntity> commentEntityList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
//    private List<LikeEntity> likeEntityList = new ArrayList<>();


    //@PrePersist
    void registeredAt(){
        this.registeredAt = Timestamp.from(Instant.now());
    }

    //@PrePersist
    void updatedAt(){
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public static PostEntity of(String title, String body, UserEntity userEntity){
        PostEntity entity = new PostEntity();
        entity.setTitle(title);
        entity.setBody(body);
        entity.setUser(userEntity);

        return entity;
    }
}
