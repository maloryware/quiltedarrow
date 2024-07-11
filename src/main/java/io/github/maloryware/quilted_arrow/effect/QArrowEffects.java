package io.github.maloryware.quilted_arrow.effect;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class QArrowEffects {
	public static StatusEffect STEALTH;
	public static StatusEffect ENRAGED;

	public static StatusEffect registerStatusEffect(StatusEffect effect, String name) {
		return
			Registry.register(Registries.STATUS_EFFECT, new Identifier(QuiltedArrow.ID, name), effect);
	}

	public static void register() {
		STEALTH = registerStatusEffect(new StealthEffect(
			StatusEffectType.BENEFICIAL, 0x909090), "stealth");
		ENRAGED = registerStatusEffect(new EnragedEffect(
			StatusEffectType.NEUTRAL, 0xBC0307), "rage");
	}
}
