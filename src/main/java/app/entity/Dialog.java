package app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dialog {

    private String path;
    private User sender;
    private User recipient;
    private List<Message> msgs;


}
