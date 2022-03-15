package app.utills;

public interface SQLQuery {

    String saveImage = "insert into img (name, address, date_time, user_id) values ( '%s', '%s', '%s', %d)";
    String saveMusic = "insert into img (name, address, date_time) values ('%s','%s','%s')";
    String saveVideo = "insert into img (name, address, date_time) values ('%s','%s','%s')";
//    String getAllImageByUser = ""



}
