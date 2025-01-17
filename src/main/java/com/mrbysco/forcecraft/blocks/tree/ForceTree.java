package com.mrbysco.forcecraft.blocks.tree;

import com.mrbysco.forcecraft.world.feature.ForceFeatureConfigs;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class ForceTree extends AbstractTreeGrower {
	/**
	 * Get a {@link net.minecraft.world.level.levelgen.feature.ConfiguredFeature} of tree
	 */
	@Nullable
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive) {
		return largeHive ? ForceFeatureConfigs.FORCE_TREE_WITH_MORE_BEEHIVES_CONFIG : ForceFeatureConfigs.FORCE_TREE;
	}
}