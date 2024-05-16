package io.github.maloryware.quilted_arrow.geckolib.SkeletonSet;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class QArrowSkeletonArmorModel extends AnimatedGeoModel<QArrowSkeletonArmorSetPiece> {

	private static final Identifier modelResource = new Identifier(QuiltedArrow.ID, "geo/skeleton_set.geo.json");
	private static final Identifier textureResource = new Identifier(QuiltedArrow.ID, "textures/models/armor/skeleton_set_texture.png");
	private static final Identifier animationResource = new Identifier(QuiltedArrow.ID, "animations/none.json");
	@Override

	//seems to use resource instead of location... despite being the same version of geckolib?

	public Identifier getModelResource(QArrowSkeletonArmorSetPiece object) {
		return modelResource;
	}

	@Override
	public Identifier getTextureResource(QArrowSkeletonArmorSetPiece object) {
		return textureResource;
	}

	@Override
	public Identifier getAnimationResource(QArrowSkeletonArmorSetPiece animatable) {
		return animationResource;
	}
}
