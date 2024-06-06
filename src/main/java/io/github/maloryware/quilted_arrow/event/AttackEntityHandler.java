package io.github.maloryware.quilted_arrow.event;

import io.github.maloryware.quilted_arrow.effects.QArrowEffects;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEntityHandler implements AttackEntityCallback {
	@Override
	public ActionResult interact(PlayerEntity player, World world, Hand hand,
								 Entity entity, @Nullable EntityHitResult hitResult) {
		if (player.hasStatusEffect(QArrowEffects.STEALTH)
			&& !world.isClient()
			&& entity instanceof LivingEntity
			&& !(entity instanceof PlayerEntity)){

			((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(QArrowEffects.ENRAGED, 600, 0));
			player.sendSystemMessage(Text.of("You have enraged a mob."));
			player.sendSystemMessage(Text.of("Mob enraged: "+ entity.getName()));
		}
		return ActionResult.PASS;
	}
}
