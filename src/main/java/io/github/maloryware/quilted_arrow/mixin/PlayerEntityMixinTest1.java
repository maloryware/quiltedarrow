package io.github.maloryware.quilted_arrow.mixin;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/*
 * This mixin is based off of
 * AwwShoot's Random Respawns Quilt
 * which can be found here:
 * https://modrinth.com/mod/randomrespawn
 *
 */

@Mixin(PlayerEntity.class)
public class PlayerEntityMixinTest1 extends LivingEntity {
	protected PlayerEntityMixinTest1(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
		throw new IllegalStateException("MixedinClassMixin dummy constructor called!");
	}

	@Inject(
		method = "findRespawnPosition",
		at = @At("HEAD")
	)

	private void findRespawnPosition(ServerWorld world, BlockPos pos, float spawnAngle, boolean isSpawnPointSet, boolean alive, CallbackInfoReturnable<Optional<Vec3d>> cir) {
		// Change the player's respawn position to their current one.
		cir.setReturnValue(Optional.of(this.getPos()));
		// uhhh?
	}


	@Shadow
	public Iterable<ItemStack> getArmorItems() { return null; }
	@Shadow
	public ItemStack getEquippedStack(EquipmentSlot slot) {	return null; }
	@Shadow
	public void equipStack(EquipmentSlot slot, ItemStack stack) {}
	@Shadow
	public Arm getMainArm() { return null; }

}


