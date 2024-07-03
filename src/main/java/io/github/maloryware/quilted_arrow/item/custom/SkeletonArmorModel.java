package io.github.maloryware.quilted_arrow.item.custom;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonArmorModel extends GeoModel<QArmorItem> {
	@Override
	public Identifier getModelResource(QArmorItem animatable) {
		return new Identifier(QuiltedArrow.ID, "geo/skeleton_set.geo.json");
	}

	@Override
	public Identifier getTextureResource(QArmorItem animatable) {
		return new Identifier(QuiltedArrow.ID, "textures/models/armor/skeleton_set_texture.png");
	}

	@Override
	public Identifier getAnimationResource(QArmorItem animatable) {
		return new Identifier(QuiltedArrow.ID, "animations/none.json");
	}
}
