package app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "content")
    private String content;
    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @OneToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "is_read")
    private boolean isRead;
}
