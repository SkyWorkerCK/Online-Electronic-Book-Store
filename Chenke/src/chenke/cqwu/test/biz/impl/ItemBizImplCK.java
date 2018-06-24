package chenke.cqwu.test.biz.impl;

import chenke.cqwu.test.biz.ItemBizCK;
import chenke.cqwu.test.dao.ItemDaoCK;
import chenke.cqwu.test.entity.ItemCK;

public class ItemBizImplCK implements ItemBizCK{

	private ItemDaoCK itemdao = null;

	public ItemDaoCK getItemdao() {
		return itemdao;
	}
	public void setItemdao(ItemDaoCK itemdao) {
		this.itemdao = itemdao;
	}
	
	public boolean addItemToOrder(ItemCK item) {
		int row = itemdao.insert(item);
		return row>0?true:false;
	}
	public int count() {	
		return itemdao.count();
	}
	
	
}
