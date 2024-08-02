package io.github.maloryware.quilted_arrow.mixin;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(PlayerEntity.class)
public class WaystoneTickMixin {


	@Unique
	public Box CenteredBox(BlockPos center, float radius){
		return new Box(

			center.getX() - radius,
			center.getY() - radius,
			center.getZ() - radius,
			center.getX() + radius,
			center.getY() + radius,
			center.getZ() + radius

		);
	}

	@Unique
	public Box CenteredBox(BlockPos center, float radius, float height){
		return new Box(

			center.getX() - radius,
			center.getY() - radius,
			center.getZ() - height,
			center.getX() + radius,
			center.getY() + radius,
			center.getZ() + height

		);
	}

	@Inject(
		method = "tick",
		at = @At(target = "HEAD")
	)




}
