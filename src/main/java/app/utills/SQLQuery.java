package app.utills;

public interface SQLQuery {

    String saveImage = "insert into img " +
            "(name, address, date_time, user_id) " +
            "values" +
            " ( '%s', '%s', '%s', %d)";
    String saveMusic = "insert into music " +
            "(name, address, date_time, user_id)" +
            " values " +
            "( '%s', '%s', '%s', %d)";
    String getImageById = "SELECT address FROM img WHERE id LIKE %d";
    String savePost = "insert into post " +
            "(content ,sender, date_time ,path_to_image)" +
            " values " +
            "('%s', %d,'%s' ,'%s')";
}
