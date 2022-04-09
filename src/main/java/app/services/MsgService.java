package app.services;


import app.entity.Dialog;
import app.entity.Message;
import app.repository.MsgRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Service
public class MsgService extends MsgRepository {

    @Override
    public boolean saveMessage(Message msg, Dialog dialog) {
        return super.saveMessage(msg, dialog);
    }

    public boolean saveMessage(Message msg, File file) {
        Dialog dialog = new Dialog();
        dialog.setFile(file);
        return super.saveMessage(msg, dialog);
    }

    @Override
    public List<Integer> getUsersIdByFileName(File file) {
        if(super.getUsersIdByFileName(file).isEmpty()){
            return Collections.emptyList();
        }
        return super.getUsersIdByFileName(file);
    }

    @Override
    public Dialog getDialog(File file) {
        return super.getDialog(file);
    }

    @Override
    public List<Message> extractMsgs(Dialog dialog) {
        return super.extractMsgs(dialog);
    }

    @Override
    public void updateMessage(Dialog dialog, Message message, Message newMessage) {
        super.updateMessage(dialog, message, newMessage);
    }

    @Override
    public void deleteMessage(Dialog dialog, Message message) {
        super.deleteMessage(dialog, message);
    }
}
