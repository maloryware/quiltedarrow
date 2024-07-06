package io.github.maloryware.quilted_arrow.item.custom;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SkeletonArmorRenderer extends GeoArmorRenderer<ArmorItem> {
	public SkeletonArmorRenderer() {
		super(new SkeletonArmorModel());
	}
}
