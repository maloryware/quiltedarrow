package io.github.maloryware.quilted_arrow.geckolib.SkeletonSet;


import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class QArrowSkeletonArmorRenderer extends GeoArmorRenderer<QArrowSkeletonArmorSetPiece> {

	public QArrowSkeletonArmorRenderer() {
		super(new QArrowSkeletonArmorModel());

		this.headBone = "armorHead";
		this.bodyBone = "armorBody";
		this.rightArmBone = "armorRightArm";
		this.leftArmBone = "armorLeftArm";
		this.rightLegBone = "armorLeftLeg";
		this.leftLegBone = "armorRightLeg";
		this.rightBootBone = "armorLeftBoot";
		this.leftBootBone = "armorRightBoot";
	}
}

