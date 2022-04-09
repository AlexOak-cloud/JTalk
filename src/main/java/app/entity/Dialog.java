package app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dialog {

    private File file;
    private User sender;
    private User recipient;
    private List<Message> msgs;


}
