/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import Database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Chung Wei Ming
 */
public class MemberLogin {



     public String login(String userid,String password) throws SQLException, ClassNotFoundException
    {
        String Userid, UserPassword;
        String id="false";

        Connection conn = DbConnection.doConnection();
        String sql = "SELECT * FROM PMS_LOGIN WHERE USER_ID=? AND PASSWORD=?";
        //PreparedStatement ps = conn.prepareStatement("SELECT * FROM PMS_LOGIN WHERE USER_ID=? AND PASSWORD=?");
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userid);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if(rs.next())
        {
            Userid = rs.getString("USER_ID");
            UserPassword = rs.getString("PASSWORD");
            id = rs.getString("USER_ID");
            if(Userid.equals(userid) && UserPassword.equals(password))
            {
                return id;
            }
             else
                return "false";
        }
        return id;
    }

}
