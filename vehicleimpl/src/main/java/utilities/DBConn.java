package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/smartdrive";
    private static final String uName = "postgres";
    private static final String pwd = "password";

    private DBConn(){
        try{
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static DBConn singleton = new DBConn();

    public static Connection getConnection(){
        Connection cnn = null;
        try{
            System.out.println("Connection to database.......@@@@@@@@");
            cnn = DriverManager.getConnection(DB_URL,uName,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnn;
    }
}


/**

 * 	public static Connection getconnection()
 *    {
 * 		Connection cnn = null;
 *
 * 		try {
 * 			System.out.println("Connection to Database !");
 *
 * 			cnn = DriverManager.getConnection(DB_URL,uName,pwd);
 *
 *
 *        } catch (SQLException e) {
 * 			// TODO Auto-generated catch block
 * 			e.printStackTrace();
 *        }
 *
 * 		return cnn;
 *    }
 *
 *
 *
 * }
 *
 *
 *
 *
 /***void insertInDB(String lecid,int subid,int bid,int tid,String date)
 *     {
 *
 *         Connection cnn = ConnectionFactory.getconnection();
 *     	String sqlq = "INSERT INTO LECTURE VALUES(?,?,?,?,?)";
 *         PreparedStatement stmt;
 *         try {
 * 			stmt = cnn.prepareStatement(sqlq);
 *
 * 	        stmt.setString(1,lecid);
 * 	        stmt.setInt(2, subid);
 * 	        stmt.setInt(3, bid);
 * 	        stmt.setInt(4, tid);
 * 	        stmt.setString(5,date);
 *
 * 	        stmt.executeUpdate();
 *                } catch (SQLException e) {
 * 			// TODO Auto-generated catch block
 * 			e.printStackTrace();
 *        }
 *
 *     }
 *
 *
 * */
