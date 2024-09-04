package io.github.maloryware.quilted_arrow.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class EnragedEffect extends StatusEffect {
	public EnragedEffect(StatusEffectType statusEffectType, int color) {
		super(statusEffectType, color);
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amp) {
		if (entity instanceof PlayerEntity){
			//ShaderUtils.invokeLoadShader("")

			/*
			 * consider working on this
			 * once everything else
			 * is done and ready.
			 * veeery low priority atm
			 */
			return;
		}
		super.applyUpdateEffect(entity, amp);

	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amp) {
		return true;
	}

}
