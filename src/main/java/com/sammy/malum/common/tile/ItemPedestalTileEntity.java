package com.sammy.malum.common.tile;

import com.sammy.malum.MalumHelper;
import com.sammy.malum.common.block.spirit_altar.IAltarProvider;
import com.sammy.malum.common.item.misc.MalumSpiritItem;
import com.sammy.malum.core.registry.block.TileEntityRegistry;
import com.sammy.malum.core.systems.blockentity.SimpleBlockEntityInventory;
import com.sammy.malum.core.systems.spirit.SpiritHelper;
import com.sammy.malum.core.systems.blockentity.SimpleInventoryBlockEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.vector.Vector3d;

import java.awt.*;

public class ItemPedestalTileEntity extends SimpleInventoryBlockEntity implements IAltarProvider, ITickableTileEntity
{
    public ItemPedestalTileEntity()
    {
        super(TileEntityRegistry.ITEM_PEDESTAL_TILE_ENTITY.get());
        inventory = new SimpleBlockEntityInventory(1, 64)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                ItemPedestalTileEntity.this.setChanged();
                clearCache();
                MalumHelper.updateAndNotifyState(level, LevelPosition);
            }
        };
    }

    @Override
    public SimpleBlockEntityInventory providedInventory()
    {
        return inventory;
    }
    @Override
    public Vector3d providedItemPos()
    {
        return itemPos(this);
    }
    public static Vector3d itemPos(SimpleTileEntity tileEntity)
    {
        return MalumHelper.pos(tileEntity.getBlockPos()).add(itemOffset());
    }
    public static Vector3d itemOffset()
    {
        return new Vector3d(0.5f, 1.1f, 0.5f);
    }

    @Override
    public void tick()
    {
        if (MalumHelper.areWeOnServer(level))
        {
            return;
        }
        if (inventory.getStackInSlot(0).getItem() instanceof MalumSpiritItem)
        {
            MalumSpiritItem item = (MalumSpiritItem) inventory.getStackInSlot(0).getItem();
            Color color = item.type.color;
            Vector3d pos = itemPos(this);
            double x = pos.x;
            double y = pos.y + Math.sin((level.getGameTime() % 360) / 20f) * 0.1f;
            double z = pos.z;
            SpiritHelper.spawnSpiritParticles(level, x,y,z, color);
        }
    }
}
