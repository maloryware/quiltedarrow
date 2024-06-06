package io.github.maloryware.quilted_arrow.effects;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class QArrowEffects {
	public static StatusEffect STEALTH;
	public static StatusEffect ENRAGED;

	public static StatusEffect registerStatusEffect(StatusEffect effect, String name) {
		return
			Registry.register(Registry.STATUS_EFFECT, new Identifier(QuiltedArrow.ID, name), effect);
	}

	public static void registerEffects() {
		STEALTH = registerStatusEffect(new QArrowStealthEffect(
			StatusEffectType.BENEFICIAL, 0x909090), "stealth");
		ENRAGED = registerStatusEffect(new QArrowEnragedEffect(
			StatusEffectType.NEUTRAL, 0xBC0307), "rage");
	}
}
