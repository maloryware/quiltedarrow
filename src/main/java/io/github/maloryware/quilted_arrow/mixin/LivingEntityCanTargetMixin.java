package io.github.maloryware.quilted_arrow.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.maloryware.quilted_arrow.effects.QArrowEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;


//yea
@Debug(export = true)
@Mixin(LivingEntity.class)

public abstract class LivingEntityCanTargetMixin {


	@Shadow
	public abstract boolean hasStatusEffect(StatusEffect effect);

	@Shadow
	private @Nullable LivingEntity attacker;

	@WrapOperation(
		method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;canTakeDamage()Z")
	)

	private boolean injectCanTarget(LivingEntity instance, Operation<Boolean> original) {

		return original.call(instance)
			&& (
				!instance.hasStatusEffect(QArrowEffects.STEALTH)
					|| this.hasStatusEffect(QArrowEffects.ENRAGED)
					&& this.attacker == instance
		);
	}


}

