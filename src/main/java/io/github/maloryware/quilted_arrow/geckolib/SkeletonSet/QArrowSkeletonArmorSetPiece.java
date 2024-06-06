package io.github.maloryware.quilted_arrow.geckolib.SkeletonSet;

import io.github.maloryware.quilted_arrow.item.QArrowArmorItem;
import io.github.maloryware.quilted_arrow.item.QArrowItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class QArrowSkeletonArmorSetPiece extends QArrowArmorItem implements IAnimatable {
	// if i ever have to touch this file, open this file, rewrite this file,
	// change code inside this file, look at the functions inside this file,
	// debug code found within this file, or do anything related to this file,
	// i will wear a bomb vest and walk *straight* into geckolib's developer's
	// parent's home.

	private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

	public QArrowSkeletonArmorSetPiece(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	// Predicate runs every frame
	private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		// This is all the extradata this event carries. The livingentity is the entity
		// that's wearing the armor. The itemstack and equipmentslottype are self
		// explanatory.
		LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);

		// Always loop the animation but later on in this method we'll decide whether or
		// not to actually play it
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));

		// If the living entity is an armorstand just play the animation nonstop
		if (livingEntity instanceof ArmorStandEntity) {
			return PlayState.CONTINUE;
		}

		// elements 2 to 6 are the armor so we take the sublist. Armorlist now only
		// contains the 4 armor slots
		List<Item> armorList = new ArrayList<>(4);
		for (EquipmentSlot slot : EquipmentSlot.values()) {
			if (slot.getType() == EquipmentSlot.Type.ARMOR) {
				if (livingEntity.getEquippedStack(slot) != null) {
					armorList.add(livingEntity.getEquippedStack(slot).getItem());
				}
			}
		}

		// Make sure the player is wearing all the armor. If they are, continue playing
		// the animation, otherwise stop
		boolean isWearingAll = new HashSet<>(armorList).containsAll(Arrays.asList(QArrowItems.SKELETON_BOOTS,
			QArrowItems.SKELETON_LEGGINGS, QArrowItems.SKELETON_CHESTPLATE, QArrowItems.SKELETON_HELMET));
		return isWearingAll ? PlayState.CONTINUE : PlayState.STOP;
	}

	// All you need to do here is add your animation controllers to the
	// AnimationData

	// this is virtually useless seeing as there are no animations to implement but i'd rather not break what works
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
	}


	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
}

