package io.github.maloryware.quilted_arrow.mixin;


import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;


@Mixin(GameRules.class)
public class QRespawnMixin {

	@ModifyArg(
		method = "<clinit>",
		slice = @Slice(
			from = @At(value = "CONSTANT", args = "stringValue=doImmediateRespawn")

		),
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/GameRules$BooleanRule;create(ZLjava/util/function/BiConsumer;)Lnet/minecraft/world/GameRules$Type;",
			ordinal = 0
		)


	)
	private static boolean injected(boolean deez){
		return true;
	}

}

