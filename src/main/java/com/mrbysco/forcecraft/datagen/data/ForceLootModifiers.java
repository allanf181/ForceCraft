package com.mrbysco.forcecraft.datagen.data;

import com.mrbysco.forcecraft.Reference;
import com.mrbysco.forcecraft.lootmodifiers.SmeltingModifier;
import com.mrbysco.forcecraft.registry.ForceTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ForceLootModifiers extends GlobalLootModifierProvider {
	public ForceLootModifiers(DataGenerator generator) {
		super(generator, Reference.MOD_ID);
	}

	@Override
	protected void start() {
		this.add("smelting", new SmeltingModifier(
				new LootItemCondition[]{
						MatchTool.toolMatches(ItemPredicate.Builder.item().of(ForceTags.TOOLS)).build()
				})
		);
	}
}
