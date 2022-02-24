package app.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "music")
@Data
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
}
