package ru.rtszh.tasktracker.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "order_number")
    private Integer orderNumber;

}
