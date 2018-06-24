package chenke.cqwu.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {
	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	
	/*
	 * �������ݿ�����
	 * */
	public void openconnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BOOKSCK?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","root123456");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * ɾ�����������
	 * */
	public int deleteOrUpdate(String sql){
		int row = 0;
		openconnection();
		try {
			ps = con.prepareStatement(sql);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
	/*
	 * ��������
	 * */
	public int insert(String table, List<String> list){
		boolean returns = false;
		StringBuffer sql = null;
		int row = 0;
		sql = new StringBuffer("insert into " + table + " values(");
		for (int i = 0; i < list.size(); i++) {
			if (i + 1 == list.size()) {
				sql.append("?)");
				break;
			}
			sql.append("?,");
		}
		openconnection();
		try {
			ps = con.prepareStatement(sql.toString());
			for (int i = 0; i < list.size(); i++) {
				ps.setObject(i + 1, list.get(i));
			}
			row = ps.executeUpdate();
			returns = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource();
		}
		return row;
	}
	
	/*
	 * ִ��sql��ѯ
	 * */
	protected List query(String sql, String[] columns){
		List list = new ArrayList();
		Map map = null;
		openconnection();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				map = new HashMap();
				for(int i=0;i<columns.length;i++){
					map.put(columns[i],rs.getObject(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			if(e.getMessage().equals("������Ч")){
				System.out.println("++++++++++++++++++��ǰҪ���ҵ��в����ڣ�+++++++++++++++++++");
			}else{
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/*
	 * �ر���Դ
	 * */
	protected boolean closeResource(){
		
		try {
			if(rs != null){
				rs.close();
			}
			if(ps != null){
				ps.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
	/*public void test(){
		System.out.println("enter the test case!");
	}*/
	
}
