package io.github.maloryware.quilted_arrow.geckolib.SkeletonSet;


import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class QArrowSkeletonArmorRenderer extends GeoArmorRenderer<QArrowSkeletonArmorSetPiece> {

	public QArrowSkeletonArmorRenderer() {
		super(new QArrowSkeletonArmorModel());

		this.headBone = "armorHead";
		this.bodyBone = "armorBody";
		this.rightArmBone = "armorRightArm";
		this.leftArmBone = "armorLeftArm";

		// a̶t̶ ̶s̶o̶m̶e̶ ̶p̶o̶i̶n̶t̶,̶ ̶w̶h̶i̶l̶e̶ ̶f̶i̶d̶d̶l̶i̶n̶g̶ ̶w̶i̶t̶h̶ ̶t̶h̶e̶ ̶g̶e̶c̶k̶o̶l̶i̶b̶ ̶m̶o̶d̶e̶l̶,̶ ̶i̶
		// a̶c̶c̶i̶d̶e̶n̶t̶a̶l̶l̶y̶ ̶f̶u̶c̶k̶e̶d̶ ̶i̶t̶ ̶u̶p̶ ̶b̶e̶y̶o̶n̶d̶ ̶r̶e̶p̶a̶i̶r̶ ̶A̶N̶D̶ ̶f̶i̶x̶e̶d̶ ̶i̶t̶.̶
		// i̶ ̶h̶a̶v̶e̶ ̶n̶o̶ ̶i̶d̶e̶a̶ ̶h̶o̶w̶.̶ ̶b̶u̶t̶ ̶i̶t̶ ̶w̶o̶r̶k̶s̶ ̶n̶o̶w̶.̶ ̶s̶o̶.̶
		// d̶o̶n̶'̶t̶ ̶t̶o̶u̶c̶h̶ ̶i̶t̶.̶


		// note: don't rotate folders. just. don't.

		this.rightLegBone = "armorRightLeg";
		this.leftLegBone = "armorLeftLeg";
		this.rightBootBone = "armorRightBoot";
		this.leftBootBone = "armorLeftBoot";
	}
}

