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
public class Post {
    @Column(name = "content")
    private String content;
    @Transient
    private User sender;
    @Column(name = "date_time")
    private LocalDateTime dateTime;

}
