package com.mrbysco.forcecraft.items.tools;

import com.mrbysco.forcecraft.Reference;
import com.mrbysco.forcecraft.capabilities.magnet.IMagnet;
import com.mrbysco.forcecraft.capabilities.magnet.MagnetCapability;
import com.mrbysco.forcecraft.items.BaseItem;
import com.mrbysco.forcecraft.registry.ForceEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.FakePlayer;

import javax.annotation.Nullable;
import java.util.List;

import static com.mrbysco.forcecraft.capabilities.CapabilityHandler.CAPABILITY_MAGNET;

public class MagnetGloveItem extends BaseItem {

	public MagnetGloveItem(Item.Properties properties) {
		super(properties.stacksTo(1));
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
		if (CAPABILITY_MAGNET == null) {
			return null;
		}
		return new MagnetCapability();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		if (playerIn.isShiftKeyDown()) {
			ItemStack stack = playerIn.getItemInHand(handIn);
			stack.getCapability(CAPABILITY_MAGNET).ifPresent((cap) -> {
				boolean state = cap.isActivated();
				cap.setActivation(!state);
				level.playSound((Player) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.UI_BUTTON_CLICK, SoundSource.NEUTRAL, 1.0F, 1.0F);
			});
		}
		return super.use(level, playerIn, handIn);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof Player && !(entityIn instanceof FakePlayer)) {
			if (itemSlot >= 0 && itemSlot <= Inventory.getSelectionSize()) {
				IMagnet magnetCap = stack.getCapability(CAPABILITY_MAGNET).orElse(null);
				if (magnetCap != null && magnetCap.isActivated()) {
					((Player) entityIn).addEffect(new MobEffectInstance(ForceEffects.MAGNET.get(), 20, 1, true, false));
				}
			}
		}
	}

	// ShareTag for server->client capability data sync
	@Override
	public CompoundTag getShareTag(ItemStack stack) {
		CompoundTag nbt = super.getShareTag(stack);

		IMagnet cap = stack.getCapability(CAPABILITY_MAGNET).orElse(null);
		if (cap != null) {
			CompoundTag shareTag = MagnetCapability.writeNBT(cap);
			if (nbt == null) {
				nbt = new CompoundTag();
			}
			nbt.put(Reference.MOD_ID, shareTag);
		}
		return nbt;
	}

	@Override
	public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
		if (nbt == null || !nbt.contains(Reference.MOD_ID)) {
			return;
		}

		IMagnet cap = stack.getCapability(CAPABILITY_MAGNET).orElse(null);
		if (cap != null) {
			CompoundTag shareTag = nbt.getCompound(Reference.MOD_ID);
			MagnetCapability.readNBT(cap, shareTag);
		}
		super.readShareTag(stack, nbt);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lores, TooltipFlag flagIn) {
		MagnetCapability.attachInformation(stack, lores);
		super.appendHoverText(stack, level, lores, flagIn);
	}
}
