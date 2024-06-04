package io.github.maloryware.quilted_arrow.mixin;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.maloryware.quilted_arrow.effects.QArrowEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(LivingEntity.class)
public abstract class TargetMixin {


	@Shadow
	public abstract boolean hasStatusEffect(StatusEffect effect);

	@ModifyExpressionValue(

		method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;canTakeDamage()Z"))

	protected boolean injectCanTargetMethod(boolean original) {
		return (original && !hasStatusEffect(QArrowEffects.STEALTH));

	}
}

