package io.github.maloryware.quilted_arrow.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class StealthEffect extends StatusEffect {
	public StealthEffect(StatusEffectType statusEffectType, int color) {
		super(statusEffectType, color);
	}

	@Override
	public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
		if (pLivingEntity.getWorld().isClient()) {
			return;
		}
		super.applyUpdateEffect(pLivingEntity, pAmplifier);

	}

	@Override
	public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
		return true;
	}

}
