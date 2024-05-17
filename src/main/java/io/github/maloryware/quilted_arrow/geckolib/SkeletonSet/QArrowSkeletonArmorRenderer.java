package io.github.maloryware.quilted_arrow.geckolib.SkeletonSet;


import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class QArrowSkeletonArmorRenderer extends GeoArmorRenderer<QArrowSkeletonArmorSetPiece> {

	public QArrowSkeletonArmorRenderer() {
		super(new QArrowSkeletonArmorModel());

		this.headBone = "armorHead";
		this.bodyBone = "armorBody";
		this.rightArmBone = "armorRightArm";
		this.leftArmBone = "armorLeftArm";

		// NO LONGER APPLIES // at some point, while fiddling with the geckolib model, i
		// NO LONGER APPLIES // accidentally fucked it up beyond repair AND fixed it.
		// NO LONGER APPLIES // i have no idea how. but it works now. so.
		// NO LONGER APPLIES // don't touch it.


		// note: don't rotate folders. just. don't.

		this.rightLegBone = "armorRightLeg";
		this.leftLegBone = "armorLeftLeg";
		this.rightBootBone = "armorRightBoot";
		this.leftBootBone = "armorLeftBoot";
	}
}

