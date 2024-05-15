package io.github.maloryware.quilted_arrow.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class QArrowSkeletonSetMaterial implements ArmorMaterial {



	private static final int[] DURABILITY = new int[]{3,7,3,3};
	private static final int[] PROTECTION = new int[]{5,16,8,4};

	@Override
	public int getDurability(EquipmentSlot slot) {
		return DURABILITY[slot.getEntitySlotId()];
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return PROTECTION[slot.getEntitySlotId()];
	}

	@Override
	public int getEnchantability() {
		return 0;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.BLOCK_BONE_BLOCK_BREAK;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return null;
	}

	@Override
	public String getName() {
		return "bone_and_leather";
	}

	@Override
	public float getToughness() {
		return 2;
	}

	@Override
	public float getKnockbackResistance() {
		return 0.2f;
	}
}
