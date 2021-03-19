package mrbysco.forcecraft.datagen;

import mrbysco.forcecraft.registry.ForceRegistry;
import mrbysco.forcecraft.registry.ForceTables;
import net.minecraft.data.loot.GiftLootTables;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;

public class SpoilsBagLootTables extends GiftLootTables {

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> consumer) {
		super.accept(consumer);
		consumer.accept(ForceTables.TIER_1, LootTable.builder()
				.addLootPool(LootPool.builder().rolls(RandomValueRange.of(1.0F, 4.0F))
						.name("Force Loot")
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_MITT.get()).weight(15))
						.addEntry(ItemLootEntry.builder(ForceRegistry.BUCKET_FLUID_FORCE.get()).weight(12))
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_GEM.get()).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_INGOT.get()).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_INGOT.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 8.0F))))
				)
				.addLootPool(LootPool.builder()
						.name("Other tables")
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_VILLAGE_VILLAGE_TOOLSMITH))
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_VILLAGE_VILLAGE_WEAPONSMITH))
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_SPAWN_BONUS_CHEST)))
		);

		consumer.accept(ForceTables.TIER_2, LootTable.builder()
				.addLootPool(LootPool.builder().rolls(RandomValueRange.of(1.0F, 4.0F))
						.name("Force Loot")
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_MITT.get()).weight(15))
						.addEntry(ItemLootEntry.builder(ForceRegistry.BUCKET_FLUID_FORCE.get()).weight(12))
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_GEM.get()).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_INGOT.get()).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_INGOT.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 8.0F))))
				)
				.addLootPool(LootPool.builder()
						.name("Other Tables")
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_SIMPLE_DUNGEON))
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_DESERT_PYRAMID))
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_JUNGLE_TEMPLE))
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_STRONGHOLD_CROSSING))
				)
		);

		consumer.accept(ForceTables.TIER_3, LootTable.builder()
				.addLootPool(LootPool.builder().rolls(RandomValueRange.of(1.0F, 4.0F))
						.name("Force Loot")
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_MITT.get()).weight(15))
						.addEntry(ItemLootEntry.builder(ForceRegistry.BUCKET_FLUID_FORCE.get()).weight(12))
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_GEM.get()).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_INGOT.get()).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 8.0F))))
						.addEntry(ItemLootEntry.builder(ForceRegistry.FORCE_INGOT.get()).weight(15).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 8.0F))))
				)
				.addLootPool(LootPool.builder()
						.name("Other Tables")
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_ABANDONED_MINESHAFT))
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_STRONGHOLD_CORRIDOR))
						.addEntry(TableLootEntry.builder(LootTables.CHESTS_STRONGHOLD_LIBRARY))
				)
		);
	}
}