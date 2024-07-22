package io.github.maloryware.quilted_arrow.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static io.github.maloryware.quilted_arrow.component.ComponentRegistryHelper.RESPAWN;

// fuck you for this noclip check, mojang
@Mixin(Entity.class)
public class IsInsideWallMixin {

	@WrapOperation(
		method = "isInsideWall",
		at = @At(
			opcode = Opcodes.GETFIELD,
			value = "FIELD",
			target = "Lnet/minecraft/entity/Entity;noClip:Z"
		)
	)

	public boolean isInRespawnPhase(Entity instance, Operation<Boolean> original){
        return original.call(instance) && RESPAWN.get(instance).getRespawnPhase().isEmpty();
	}

}
