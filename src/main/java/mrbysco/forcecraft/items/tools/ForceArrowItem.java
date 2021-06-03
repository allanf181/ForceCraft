package mrbysco.forcecraft.items.tools;

import mrbysco.forcecraft.entities.projectile.ForceArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import static mrbysco.forcecraft.capablilities.CapabilityHandler.CAPABILITY_TOOLMOD;

public class ForceArrowItem extends ArrowItem {
	public ForceArrowItem(Properties builder) {
		super(builder);
	}

	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		ForceArrowEntity forceArrow = new ForceArrowEntity(worldIn, shooter);
		if(shooter instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) shooter;
			ItemStack heldItem = player.getActiveItemStack();
			if(heldItem.getItem() instanceof ForceBowItem) {
				heldItem.getCapability(CAPABILITY_TOOLMOD).ifPresent(cap -> {
					if(cap.hasFreezing()) {
						forceArrow.addEffect(new EffectInstance(Effects.SLOWNESS, 60, 2));
					}
					if(cap.hasEnder()) {
						CompoundNBT tag = stack.getOrCreateTag();
						tag.putBoolean("ender", true);
						stack.setTag(tag);
					}
				});
			}
		}
		forceArrow.setPotionEffect(stack);
		return forceArrow;
	}
}
