package gmail.Lance5057.armor.parts;

import gmail.Lance5057.TinkersDefense;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Item_Thread extends Item 
{
	public IIcon[] icons = new IIcon[16];
	public static final String[] colors = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};
	
	public Item_Thread()
	{
		super();
		this.setHasSubtypes(true);
		this.setUnlocalizedName("Thread");
		this.setCreativeTab(TinkersDefense.tabName);
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
	    for (int i = 0; i < 16; i ++) {
	        this.icons[i] = reg.registerIcon("tinkersdefense:" + colors[i] + "_thread" );
	    }
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
	    if (meta > 15)
	        meta = 0;

	    return this.icons[meta];
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 16; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return colors[stack.getItemDamage()] + "_" + this.getUnlocalizedName();
	}
}
