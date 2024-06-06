package io.github.maloryware.quilted_arrow.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

// whoever wrote the code for ArmorMaterial: i will snip your nipples off with a pair of round scissors


// SHUT THE FUCK UP GRAAAAAH IF THERE'S NO ALTERNATIVE THEN STOP BITCHING
@SuppressWarnings("deprecation")

public enum QArrowArmorMaterials implements ArmorMaterial {

	// ah yeah no sure dude makes sense have the durability multiplier followed by the PROTECTION VALUE then when declaring the values
	// have the DURABILITY right next to the DURABILITY MULTIPLIER
	// i spent 20 minutes trying to figure out why my chestplate had 3 protection..... smigh
	// no i'm not dumb. nuh uh. no. it's. lib devs. they're bad. i'm genius. real. type 1

	BONE("bone", 1, new int[]{11, 15, 16, 13}, 0, SoundEvents.BLOCK_BONE_BLOCK_BREAK, 0.5F, 0.0F, () -> null);

	private static final int[] BASE_DURABILITY = new int[]{1, 2, 3, 1};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Lazy<Ingredient> repairIngredientSupplier;

	QArrowArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier repairIngredientSupplier) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.protectionAmounts = protectionAmounts;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredientSupplier = new Lazy(repairIngredientSupplier);
	}

	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
	}

	public int getProtectionAmount(EquipmentSlot slot) {
		return this.protectionAmounts[slot.getEntitySlotId()];
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredientSupplier.get();
	}

	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}
