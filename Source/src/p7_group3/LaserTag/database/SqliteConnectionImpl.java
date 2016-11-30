//

package p7_group3.LaserTag.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqliteConnectionImpl implements DbConnection {

    @Override
    public Connection createConnection() {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:LaserTag.db");
            return conn;
        }catch (ClassNotFoundException | SQLException e){
            return null;
        }
    }
}
