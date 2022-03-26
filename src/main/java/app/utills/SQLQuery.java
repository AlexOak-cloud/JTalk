package app.utills;

public interface SQLQuery {

    String saveImage = "insert into img (name, address, date_time, user_id) values ( '%s', '%s', '%s', %d)";
    String saveMusic = "insert into music (name, address, date_time, user_id) values ( '%s', '%s', '%s', %d)";
    String saveVideo = "insert into video (name, address, date_time, user_id) values ( '%s', '%s', '%s', %d)";
    String getImageById = "SELECT address FROM img WHERE id LIKE %d";
    String showTables = "show tables";
    String createTableMSG = "CREATE TABLE `%d_%ddi` (id INT AUTO_INCREMENT PRIMARY KEY," +
            "content VARCHAR(1000)," +
            "sender_id INT ," +
            "recipient_id INT ," +
            "date_time TIMESTAMP," +
            "is_read BOOLEAN," +
            "FOREIGN KEY (sender_id) REFERENCES usr(id)" +
            "ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY (recipient_id) REFERENCES usr(id)" +
            " ON DELETE CASCADE ON UPDATE CASCADE)" +
            "ENGINE=INNODB ";
    String insertMessage = "insert into '`%s`' (content) values ('%s')";
}
