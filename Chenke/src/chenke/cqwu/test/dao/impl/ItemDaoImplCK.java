package chenke.cqwu.test.dao.impl;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import chenke.cqwu.test.dao.BaseDao;
import chenke.cqwu.test.dao.ItemDaoCK;
import chenke.cqwu.test.entity.ItemCK;

public class ItemDaoImplCK extends BaseDao implements ItemDaoCK{

	public int insert(ItemCK item) {
		String table = "items";
		
		List list = new ArrayList();		
		list.add( item.getIid() );
		list.add( item.getOid() );
		list.add( item.getBid() );
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts =  new Timestamp(new Date().getTime());	
		list.add( sdf.format(ts.getTime()));
		list.add( item.getCount() );
		list.add( item.getIprice() );
		list.add( item.getState() );
		list.add( item.getTotal_price());
		return insert(table, list);	
	}
	
	public int count() {
		String sql = "select count(*) from items";
		openconnection();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		
		return i;
	}
	
}
