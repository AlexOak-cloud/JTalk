package app.entity;


import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "msg")
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
