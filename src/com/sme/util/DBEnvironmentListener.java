package com.sme.util;

import java.sql.Connection;
import java.sql.ResultSet;

public class DBEnvironmentListener {
	
	//判断数据库环境是否已经搭建
	public boolean isDataBaseInit(Connection con) throws Exception{
		String[] tableType = {"TABLE"};
		ResultSet rs = con.getMetaData().getTables("plugins", null, null, tableType);
		boolean isNull = true;
		while(rs.next()){
			isNull = false;
			System.out.println("catalog : "+rs.getObject(1)+" ! and table name is :"+rs.getObject(3));
		}
		if(!isNull){
			con.close();
		}
		return isNull;
	}
	
	//搭建数据库环境
	/*public void initDataBase(Connection con) {
		System.out.print("????????????????????????????????");
		InputStream dbShell = DBEnvironmentListener.class.getClassLoader().getResourceAsStream("initDataBase.sql");
		System.out.print(dbShell);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(dbShell));
		try {
			con.setAutoCommit(false);
			String sql = null;
			while((sql = buffer.readLine()) != null){
				if(sql.trim().length()>0){
					System.out.println("Excuting sql at " +new Date()+ "-->" +sql);
					Statement stmt = con.createStatement();
					stmt.execute(sql);
				}
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("ERROR OF :" + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}*/
}
