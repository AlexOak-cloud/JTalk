package app.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
}
