package com.mrbysco.forcecraft.menu.furnace;

import com.mrbysco.forcecraft.registry.ForceContainers;
import com.mrbysco.forcecraft.blockentities.AbstractForceFurnaceBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.network.FriendlyByteBuf;

public class ForceFurnaceMenu extends AbstractForceFurnaceMenu {
    public ForceFurnaceMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        super(windowId, playerInventory, data);
    }

    public ForceFurnaceMenu(int id, Inventory playerInventoryIn, AbstractForceFurnaceBlockEntity te) {
        super(id, playerInventoryIn, te);
    }

    @Override
    public MenuType<?> getType() {
        return ForceContainers.FORCE_FURNACE.get();
    }
}