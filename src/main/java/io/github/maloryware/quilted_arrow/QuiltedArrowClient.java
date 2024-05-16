package io.github.maloryware.quilted_arrow;

import io.github.maloryware.quilted_arrow.geckolib.SkeletonSet.QArrowSkeletonArmorRenderer;
import io.github.maloryware.quilted_arrow.item.QArrowItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class QuiltedArrowClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {

		GeoArmorRenderer.registerArmorRenderer(new QArrowSkeletonArmorRenderer(),
			QArrowItems.SKELETON_BOOTS, QArrowItems.SKELETON_LEGGINGS, QArrowItems.SKELETON_CHESTPLATE, QArrowItems.SKELETON_HELMET);
	}
}
