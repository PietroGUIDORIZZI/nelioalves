package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static db.DB.closeConnection;
import static db.DB.getConnection;

public class Program {
    public static void main(String[] args) {

        Statement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();

            st = conn.createStatement();
            rs = st.executeQuery("select * from department");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("Id") + ", " + rs.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            closeConnection();
        }
    }
}