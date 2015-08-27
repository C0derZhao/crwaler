package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBBean {
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL = "";
	private static final String USER = "";
	private static final String PASS ="";
	public DBBean()throws Exception {
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, USER, PASS);
	}
    public Connection getCon(){
        return con;
    }
	public ResultSet excuteQuery(String sql,Object...params) throws Exception {
		stmt = con.prepareStatement(sql);
		if(params!=null)
			for(int i=0;i<params.length;i++){
				stmt.setObject(i+1, params[i]);
			}
		return stmt.executeQuery();
	}
	public int executeUpdate(String sql,Object...params) throws Exception {
		stmt = con.prepareStatement(sql);
		if(params!=null)
			for(int i=0;i<params.length;i++){
				stmt.setObject(i+1, params[i]);
			}
		return stmt.executeUpdate();
	}
	public void close(){
		try{
			if(rs!=null)
				rs.close();}
			catch(Exception e){}
			try{
				if(stmt!=null)
					stmt.close();}
				catch(Exception e){}
			try{
				if(con!=null)
					con.close();}
				catch(Exception e){}
			
	}
}
