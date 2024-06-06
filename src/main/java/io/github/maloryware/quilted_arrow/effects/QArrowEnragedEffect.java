package io.github.maloryware.quilted_arrow.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class QArrowEnragedEffect extends StatusEffect {
	public QArrowEnragedEffect(StatusEffectType statusEffectType, int color) {
		super(statusEffectType, color);
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amp) {
		super.applyUpdateEffect(entity, amp);

	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amp) {
		return true;
	}

}
