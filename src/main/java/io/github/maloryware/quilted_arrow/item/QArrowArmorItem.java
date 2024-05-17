package io.github.maloryware.quilted_arrow.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.include.com.google.common.collect.ImmutableMap;

import java.util.Map;

public class QArrowArmorItem extends ArmorItem {

	private static final Map<ArmorMaterial, StatusEffectInstance> BONE_MATERIAL_TO_EFFECT_MAP =
		(new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
			.put(QArrowArmorMaterials.BONE, new StatusEffectInstance(StatusEffects.SPEED, 0, 1)).build();


	// never again GOOD fucking LORD

	public QArrowArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings){
		super(material, slot, settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient()){

			//if shit hits the fan, change this back to how it was (see kaupenjoe vid)
			if(entity instanceof PlayerEntity player) {

				if(hasFullSuitOfArmorOn(player)){
					evaluateArmorEffects(player);
				}
			}
		}

		super.inventoryTick(stack,world,entity,slot,selected);
	}

	private void evaluateArmorEffects(PlayerEntity player){
		for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : BONE_MATERIAL_TO_EFFECT_MAP.entrySet()) {
			ArmorMaterial mapArmorMaterial = entry.getKey();
			StatusEffectInstance mapStatusEffect = entry.getValue();

			if(hasCorrectArmorOn(mapArmorMaterial, player)) {
				addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
			}
		}
	}

	private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapStatusEffect){
		boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

		if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
			player.addStatusEffect(new StatusEffectInstance(mapStatusEffect.getEffectType(),
				mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
		}
	}

	private boolean hasFullSuitOfArmorOn(PlayerEntity player){
		ItemStack boots = player.getInventory().getArmorStack(0);
		ItemStack leggings = player.getInventory().getArmorStack(1);
		ItemStack breastplate = player.getInventory().getArmorStack(2);
		ItemStack helmet = player.getInventory().getArmorStack(3);

		return !helmet.isEmpty()
			&& !breastplate.isEmpty()
			&& !leggings.isEmpty()
			&& !boots.isEmpty();
	}

	private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
		ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
		ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
		ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
		ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

		return boots.getMaterial() == material
			&& leggings.getMaterial() == material
			&& breastplate.getMaterial() == material
			&& helmet.getMaterial() == material;

	}
}
