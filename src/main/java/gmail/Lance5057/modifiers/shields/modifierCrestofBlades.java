package gmail.Lance5057.modifiers.shields;

import tconstruct.library.tools.ToolCore;
import tconstruct.modifiers.tools.ItemModTypeFilter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class modifierCrestofBlades extends ItemModTypeFilter
{
    String tooltipName;
    int max = 5;
    String guiType;

    public modifierCrestofBlades(String type, int effect, ItemStack[] items, int[] values)
    {
        super(effect, "Blades", items, values);
        tooltipName = "\u00A7fBlades";
        guiType = type;
    }

    @Override
    protected boolean canModify (ItemStack tool, ItemStack[] input)
    {
        NBTTagCompound tags = tool.getTagCompound().getCompoundTag("InfiTool");
        if (!tags.hasKey(key))
            return tags.getInteger("Modifiers") > 0 && matchingAmount(input) <= max;

        if (matchingAmount(input) > max)
            return false;

        int keyPair[] = tags.getIntArray(key);
        if (keyPair[0] + matchingAmount(input) <= keyPair[1])
            return true;

//        else if (keyPair[0] == keyPair[1])
//            return tags.getInteger("Modifiers") > 0;

        else
            return false;
    }

    @Override
    public void modify (ItemStack[] input, ItemStack tool)
    {
        NBTTagCompound tags = tool.getTagCompound().getCompoundTag("InfiTool");
        int increase = matchingAmount(input);
        int[] keyPair;
        int current = 0;
        if (tags.hasKey(key))
        {
            keyPair = tags.getIntArray(key);

            if (keyPair[0] % max == 0)
            {
                keyPair[0] += increase;
                keyPair[1] += max;
                tags.setIntArray(key, keyPair);

                int modifiers = tags.getInteger("Modifiers");
                modifiers -= 1;
                tags.setInteger("Modifiers", modifiers);
            }
            else
            {
                keyPair[0] += increase;
                tags.setIntArray(key, keyPair);
            }
            updateModTag(tool, keyPair);

        }
        else
        {
            int modifiers = tags.getInteger("Modifiers");
            modifiers -= 1;
            tags.setInteger("Modifiers", modifiers);
            String modName = "\u00A76" + guiType + " (" + increase + "/" + max + ")";
            int tooltipIndex = addToolTip(tool, tooltipName, modName);
            keyPair = new int[] { increase, max, tooltipIndex };
            tags.setIntArray(key, keyPair);
        }
        
    }

    void updateModTag (ItemStack tool, int[] keys)
    {
        NBTTagCompound tags = tool.getTagCompound().getCompoundTag("InfiTool");
        String tip = "ModifierTip" + keys[2];
        String modName = "\u00A76" + guiType + " (" + keys[0] + "/" + keys[1] + ")";
        tags.setString(tip, modName);
    }
}
