package chenke.cqwu.test.biz;

import chenke.cqwu.test.dao.ItemDaoCK;
import chenke.cqwu.test.entity.ItemCK;

public interface ItemBizCK {
	
	public boolean addItemToOrder(ItemCK item);
	public void setItemdao(ItemDaoCK itemdao);
	public ItemDaoCK getItemdao();
	public int count();
	
}
