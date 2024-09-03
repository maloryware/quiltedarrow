package io.github.maloryware.quilted_arrow.mixin.antigrief;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {


	@Shadow
	public abstract StructureManager getStructureManager();

	// don't you fucking dare cheese it you motherfucker

	@ModifyReturnValue(method = "canPlayerModifyAt", at = @At("RETURN"))
	public boolean isInsideMyStruct(boolean original, PlayerEntity player,  BlockPos pos){
		return original && ((
			this.getStructureManager().getStructureStartAt(
				pos,
				RegistryKey.of(RegistryKeys.STRUCTURE_FEATURE,
					new Identifier("quilted_arrow:waystone_tower"))
			) == StructureStart.DEFAULT) || player.getAbilities().creativeMode);

	}
}
