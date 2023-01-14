package ru.rtszh.tasktracker.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
//@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //    @Column(name = "login")
    @Column(name = "login", unique = true)
    private String login;

    @OneToMany(cascade = CascadeType.REMOVE,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    List<Task> tasks;

}
