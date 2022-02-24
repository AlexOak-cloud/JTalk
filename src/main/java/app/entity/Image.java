package app.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "img")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
}
