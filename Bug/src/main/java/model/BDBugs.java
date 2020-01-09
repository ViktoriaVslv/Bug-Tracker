package model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;


public class BDBugs{
	private Statement stmt;
	private ResultSet rs;
	private ConnectionBD conn;
	
	public BDBugs() {
		conn = new ConnectionBD();
		stmt=conn.getStatement();
	}
	
	public boolean find(String name) throws Exception {
		int flag = 0;
		rs = conn.connectBugs();
		while (rs.next()) {
			String dblog = rs.getString(2) ;
			if(dblog.equals(name))
				flag++;
		}
		if (flag==0)
			return false;
		else
			return true;
	}
	
	public void insert(String status, String name, String fromwho, String towho, String data, String info, String bf, String parent)throws Exception {
		rs = conn.connectBugs();
		stmt.executeUpdate("INSERT INTO Bugs (status, name, fromwho, towho, data, info, bf, child) VALUES ('"+status+"', '"+name+"', '"+fromwho+"', '"+towho+"','"+data+"', '"+info+"', '"+bf+"', '"+parent+"')");		
	}
	
	public void update(String ch, String status, String name, String towho, String data, String info, String bf, String parent)throws Exception { 
		rs = conn.connectBugs();
		stmt.executeUpdate("UPDATE Bugs SET name = '"+name+"'WHERE name='"+ch+"';");
		stmt.executeUpdate("UPDATE Bugs SET status='"+status+"' WHERE name='"+ch+"';");
		stmt.executeUpdate("UPDATE Bugs SET info='"+info+"' WHERE name='"+ch+"';");
		stmt.executeUpdate("UPDATE Bugs SET towho='"+towho+"' WHERE name='"+ch+"';");
		stmt.executeUpdate("UPDATE Bugs SET data='"+data+"' WHERE name='"+ch+"';");
		stmt.executeUpdate("UPDATE Bugs SET bf='"+bf+"' WHERE name='"+ch+"';");
		stmt.executeUpdate("UPDATE Bugs SET child='"+parent+"' WHERE name='"+ch+"';");
	}
	
	public boolean checkSt(String ch, String name) throws Exception {
		rs = conn.connectBugs();
		while (rs.next()) {
				String st2= rs.getString(1);
				String parent2= rs.getString(8);
				if(ch.equals(parent2)&& st2.equals("t")) {
					return true;
				} 		
		}
		return false;
	}
	
	public ArrayList getAllBugs() throws Exception{
		ArrayList bugs = new ArrayList();
		rs = conn.connectBugs();
		while (rs.next()) {
			String parent = rs.getString(2);
			if(rs.getString(1).equals("t"))
				bugs.add(parent);
		}
		return bugs;
	}	
	
	
	public ArrayList getAllBugs1() throws Exception{
		ArrayList bugs = new ArrayList();
		rs = conn.connectBugs();
		while (rs.next()) {
			String parent = rs.getString(2);
			bugs.add(parent);
		}
		return bugs;
	}
	
	public ArrayList getSortedBug() throws Exception{
		ArrayList bugs = new ArrayList();
		ArrayList dat = new ArrayList();
		ArrayList<String> st = new ArrayList<String>();
		SimpleDateFormat format =new SimpleDateFormat();
		format.applyPattern("dd.MM.yyyy HH:mm");
		rs = conn.connectBugs();
		while (rs.next()) {
			String name = rs.getString(2);
			String data = rs.getString(5);
			String s = rs.getString(1);
			Date d=format.parse(data);
			if(dat.size()==0) {
				dat.add(data);
				bugs.add(name);
				st.add(s);
			}
			else {
				int flag=0;
				for(int i=0; i<dat.size();i++) {
					if(d.after(format.parse((String)dat.get(i)))) {
						dat.add(i, data);
						bugs.add(i, name);
						st.add(i, s);
						flag++;
						break;
					}	
				}
				if(flag==0) {
					dat.add(data);
					bugs.add(name);
					st.add(s);
				}
			}
		}
		ArrayList t = new ArrayList();
		ArrayList f = new ArrayList();
		for(int i=0; i<bugs.size();i++) {
			if("t".equals(st.get(i)))
				t.add(bugs.get(i));
			else
				f.add(bugs.get(i));		
		}
		bugs = new ArrayList();
		for(int i=0; i<t.size();i++) 
			bugs.add(i, t.get(i));
		for(int i=0; i<f.size();i++) 
			bugs.add(f.get(i));
		
		return bugs;
	}
	
	
	public void deleteBugs(String name) throws Exception{
		rs = conn.connectBugs();
		stmt.executeUpdate("DELETE FROM Bugs WHERE name='"+name+"'");	
	}
	
	public ArrayList getChildren(String name) throws Exception{
		ArrayList ch = new ArrayList();
		rs = conn.connectBugs();
		while (rs.next()) {
			String parent =rs.getString(8);
			if(name.equals(parent))
				ch.add(rs.getString(2));
			}
		return ch;
	}
	
	public ArrayList getAll(String change) throws Exception{
		ArrayList all = new ArrayList();
	
		rs = conn.connectBugs();
		while (rs.next()) {
			String name = rs.getString(2);
			if (change.equals(name)) {
				String info = rs.getString(6);
				String data = rs.getString(5);
				String form = rs.getString(7);
				if (form.equals("t"))
					form = "Bug";
				else
					form = "Feature";
				String st = rs.getString(1);
				if (st.equals("t"))
					st = "Open";
				else
					st = "Close";
				String fromwho = rs.getString(3);
				String towho = rs.getString(4);
				String parent = rs.getString(8);
				
				all.add(st);
				all.add(change);
				all.add(fromwho);
				all.add(towho);
				all.add(data);
				all.add(info);
				all.add(form);
				all.add(parent);
			}
		}
		return all;
	}
	
	public ArrayList newL(ArrayList b, String name) throws Exception{
		rs = conn.connectBugs();
		ArrayList bugs = b;
		
		ArrayList family = new ArrayList();
		family.add(name);
		while(family.size()!=0) {
			rs = conn.connectBugs();
			while(rs.next()){
				String p=rs.getString(8);
				 String n=rs.getString(2);
				 if(family.get(0).equals(p)) {
					 family.add(n);
					 bugs.remove(n);
				 } 
			}
		 family.remove(0);
		}
		bugs.add(" ");
		return bugs;
	}
	
	public ArrayList newL1(String name) throws Exception{
		ArrayList bugsDelete = new ArrayList();
		ArrayList family = new ArrayList();
		family.add(name);
		bugsDelete.add(name);
		while(family.size()!=0) {
			rs = conn.connectBugs();
			while(rs.next()){
				 String p=rs.getString(8);
				 String n=rs.getString(2);
				 if(family.get(0).equals(p)) {
					 family.add(n);
					 bugsDelete.add(n);
				 } 
			}
		 family.remove(0);
		}
		return bugsDelete;
	}
	
	
	public void close() {
		conn.close();
	}
}