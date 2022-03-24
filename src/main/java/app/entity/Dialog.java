package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "dialog")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @OneToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    @Transient
    private List<Message> messages;
}
