package io.github.maloryware.quilted_arrow.effects;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class QArrowEffects {
	public static StatusEffect STEALTH;

	public static StatusEffect registerStatusEffect(String name) {
		return Registry.register(Registry.STATUS_EFFECT, new Identifier(QuiltedArrow.ID, name),
			new QArrowStealthEffect(StatusEffectType.BENEFICIAL, 0xa1337f));
	}

	public static void registerEffects() {
		STEALTH = registerStatusEffect("stealth");
	}
}
