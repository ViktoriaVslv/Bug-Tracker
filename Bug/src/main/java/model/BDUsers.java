package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class BDUsers{
	private Statement stmt;
	private ResultSet rs;
	private ConnectionBD conn;
	
	
	public BDUsers() {
		conn = new ConnectionBD();
		stmt=conn.getStatement();
	}
	
	public boolean find(String login) throws Exception {
		int flag = 0;
		rs =conn.connectUsers();
		while (rs.next()) {
			String dblog = rs.getString(1) ;
			if(dblog.equals(login))
				flag++;
		}
		if (flag==0)
			return false;
		else
			return true;
	}
	
	public void insert(String login, String pass)throws Exception {
		rs =conn.connectUsers();
		stmt.executeUpdate("INSERT INTO Users (login, password) VALUES ('"+login+"', '" + pass+"')");	
	}
	
	public  boolean auth(String login, String pass) throws Exception {
		int flag = 0;
		rs =conn.connectUsers();
		while (rs.next()) {
			String dblog = rs.getString(1) ;
			String dbpass = rs.getString(2);
			if((dblog.equals(login))&&(dbpass.equals(pass))) {
				flag++;
				break;
			}
		}
		if (flag==0)
			return false;
		else
			return true;
	}
	
	public ArrayList getAllUsers() throws Exception{
		ArrayList us = new ArrayList();
		rs =conn.connectUsers();
		while (rs.next()) {
			String dblog = rs.getString(1) ;
			us.add(dblog);
		}
		return us;
	}	

	public void close() {
		conn.close();
	}
}