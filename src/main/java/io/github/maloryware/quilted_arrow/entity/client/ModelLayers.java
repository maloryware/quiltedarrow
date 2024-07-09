package io.github.maloryware.quilted_arrow.entity.client;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModelLayers {
	public static final EntityModelLayer CAMERA =
		new EntityModelLayer(new Identifier(QuiltedArrow.ID, "porcupine"), "main");
}
