package com.mrbysco.forcecraft.registry;

import com.mojang.math.Vector3f;
import com.mrbysco.forcecraft.Reference;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ForceFluids {
	private static final ResourceLocation STILL_METAL = new ResourceLocation(Reference.MOD_ID, "fluid/force_fluid_source");
	private static final ResourceLocation FLOWING_METAL = new ResourceLocation(Reference.MOD_ID, "fluid/force_fluid_flowing");

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MOD_ID);
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Reference.MOD_ID);

	private static RegistryObject<FluidType> FORCE_TYPE = FLUID_TYPES.register("force", () -> new FluidType(createTypeProperties()) {
		@Override
		public double motionScale(Entity entity) {
			return entity.level.dimensionType().ultraWarm() ? 0.007D : 0.0023333333333333335D;
		}

		@Override
		public void setItemMovement(ItemEntity entity) {
			Vec3 vec3 = entity.getDeltaMovement();
			entity.setDeltaMovement(vec3.x * (double) 0.95F, vec3.y + (double) (vec3.y < (double) 0.06F ? 5.0E-4F : 0.0F), vec3.z * (double) 0.95F);
		}

		@Override
		public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
			consumer.accept(new IClientFluidTypeExtensions() {

				@Override
				public ResourceLocation getStillTexture() {
					return STILL_METAL;
				}

				@Override
				public ResourceLocation getFlowingTexture() {
					return FLOWING_METAL;
				}

				@Override
				public int getTintColor() {
					return 0xFFFFFFFF;
				}

				@Override
				public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
					int color = this.getTintColor();
					return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
				}
			});
		}
	});
	public static RegistryObject<FlowingFluid> FORCE_FLUID_SOURCE;
	public static RegistryObject<FlowingFluid> FORCE_FLUID_FLOWING;

	public static ForgeFlowingFluid.Properties FLUID_FORCE_PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> FORCE_TYPE.get(), () -> FORCE_FLUID_SOURCE.get(), () -> FORCE_FLUID_FLOWING.get())
			.bucket(ForceRegistry.BUCKET_FLUID_FORCE).block(() -> (LiquidBlock) ForceRegistry.FORCE_FLUID_BLOCK.get());

	public static void registerFluids() {
		FORCE_FLUID_SOURCE = FLUIDS.register("fluid_force_source", () -> new ForgeFlowingFluid.Source(FLUID_FORCE_PROPERTIES));
		FORCE_FLUID_FLOWING = FLUIDS.register("fluid_force_flowing", () -> new ForgeFlowingFluid.Flowing(FLUID_FORCE_PROPERTIES));
	}


	public static FluidType.Properties createTypeProperties() {
		return FluidType.Properties.create()
				.canSwim(false)
				.canDrown(false)
				.pathType(BlockPathTypes.LAVA)
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
				.rarity(Rarity.COMMON)
				.lightLevel(0).density(2000).viscosity(1000).temperature(120);
	}
}
