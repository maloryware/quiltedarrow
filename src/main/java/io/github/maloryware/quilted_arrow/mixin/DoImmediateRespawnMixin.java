package io.github.maloryware.quilted_arrow.mixin;


import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Debug(export = true)
@Mixin(GameRules.class)
public class DoImmediateRespawnMixin {
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
	private static boolean injected(boolean thisbool){
		return true;
	}

}

