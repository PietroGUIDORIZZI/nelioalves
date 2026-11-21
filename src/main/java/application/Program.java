package application;

import db.DB;

import java.sql.Connection;

public class Program {
    static void main(String[] args) {

        Connection conn = DB.getConnection();
        DB.closeConnection();
    }
}
