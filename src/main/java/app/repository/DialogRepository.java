package app.repository;


import app.entity.Message;
import app.entity.User;
import app.services.UserService;
import app.utills.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Repository
public class DialogRepository {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    /** Returning name table */
    public String generateTable(User user){
        String nameTable = user.getId() + "_" + userService.getAuthUser().getId() + "di";
        boolean rtnValue = false;
        try(Statement statement = dataSource.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLQuery.showTables);
            while(resultSet.next()) {
                String fromTable = resultSet.getString(1);
                if (fromTable.equals(nameTable)) {
                    rtnValue = true;
                }
            }
                if(rtnValue){
                    statement.executeUpdate(String.format(
                            SQLQuery.createTableMSG,user.getId(),userService.getAuthUser().getId()));
                }
                return nameTable;
        }catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public boolean save(Message message, User recipient){
        String nameTable = generateTable(recipient);
        try(Statement statement = dataSource.getConnection().createStatement()){
            statement.executeUpdate(
                    String.format(
                            SQLQuery.insertMessage,
                            nameTable,message.getContent()));
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
