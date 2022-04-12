package app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "content")
    private String content;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User sender;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "path_to_image")
    private String pathToImage;
}
