package com.example.mymissyou.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "delete_time is null")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String openid;

    private String nickname;

    private String username;

    private String email;

    private String mobile;

    private String password;

    private Long unifyUid;
}
