package io.github.maloryware.quilted_arrow.item.custom;

import com.google.common.collect.ImmutableMap;
import io.github.maloryware.quilted_arrow.effects.QArrowEffects;
import io.github.maloryware.quilted_arrow.item.QArmorMaterials;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings({"unchecked", "rawtypes"})
public class QArmorItem extends ArmorItem implements GeoAnimatable, GeoItem {


	private static final Map<ArmorMaterial, StatusEffectInstance> FullKitEffectMap =
		(new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
			.put(QArmorMaterials.BONE, new StatusEffectInstance(QArrowEffects.STEALTH, 0, 1,
				false, false, false)).build();



	private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapStatusEffect) {

		boolean hasEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

		if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasEffect){
			player.addStatusEffect(new StatusEffectInstance(mapStatusEffect));
		}
	}

	private void evalArmorEffects(PlayerEntity player){
		for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : FullKitEffectMap.entrySet()) {
			ArmorMaterial mapArmorMaterial = entry.getKey();
			StatusEffectInstance mapStatusEffect = entry.getValue();

			if(hasCorrectArmorOn(mapArmorMaterial, player)){
				addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
			}
		}
	}

	private boolean hasFullKit(PlayerEntity player){
		ItemStack boots = player.getInventory().getArmorStack(0);
		ItemStack leggings = player.getInventory().getArmorStack(1);
		ItemStack chestplate = player.getInventory().getArmorStack(2);
		ItemStack helmet = player.getInventory().getArmorStack(3);

		return !boots.isEmpty()
			&& !leggings.isEmpty()
			&& !chestplate.isEmpty()
			&& !helmet.isEmpty();


	}
	private boolean hasCorrectArmorOn(ArmorMaterial material,  PlayerEntity player){
		for(ItemStack armorStack: player.getInventory().armor) {
			if(!(armorStack.getItem() instanceof ArmorItem)) {
				return false;
			}
		}

		ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
		ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
		ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
		ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

		return helmet.getMaterial() == material
			&& breastplate.getMaterial() == material
			&& leggings.getMaterial() == material
			&& boots.getMaterial() == material;
	}



	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient()){
			if(entity instanceof PlayerEntity player && hasFullKit(player)) {
				evalArmorEffects(player);
			}
		}
	}

	private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
	private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);





	public QArmorItem(ArmorMaterial material, ArmorSlot slot, Settings settings) {
		super(material, slot, settings);
	}




	@Override
	public void createRenderer(Consumer<Object> consumer) {
		consumer.accept(new RenderProvider() {
		private SkeletonArmorRenderer renderer;

		@Override
		public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity entity, ItemStack stack,
																	EquipmentSlot slot, BipedEntityModel<LivingEntity> original) {
			if (this.renderer == null)
				this.renderer = new SkeletonArmorRenderer();

			this.renderer.prepForRender(entity, stack, slot, original);

			return this.renderer;

		}

		});
	}

	@Override
	public Supplier<Object> getRenderProvider() {
		return this.renderProvider;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController(this, "controller", 0, this::predicate));
	}

	private PlayState predicate(AnimationState state) {
		state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
		return PlayState.CONTINUE;
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
