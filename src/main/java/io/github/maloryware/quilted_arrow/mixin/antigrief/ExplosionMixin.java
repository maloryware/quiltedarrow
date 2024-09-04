package io.github.maloryware.quilted_arrow.mixin.antigrief;

import com.google.common.collect.Iterators;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.structure.StructureStart;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@SuppressWarnings("rawtypes")
@Mixin(Explosion.class)
public abstract class ExplosionMixin {

	@Unique
	private boolean shallDestroy;

	@Shadow
	@Final
	private double z;

	@Shadow
	@Final
	private double x;

	@Shadow
	@Final
	private double y;

	@Shadow
	@Final
	private World world;

	// TODO: fix this
	// 	or alternatively, put in the patch notes "de-optimized explosions"

	@Shadow
	public abstract @Nullable LivingEntity getCausingEntity();

	@Shadow
	public abstract void affectWorld(boolean particles);

	@Shadow
	@Final
	private ObjectArrayList<BlockPos> affectedBlocks;

	@Definition(id = "affectedBlocks", field = "Lnet/minecraft/world/explosion/Explosion;affectedBlocks:Lit/unimi/dsi/fastutil/objects/ObjectArrayList;")
	@Definition(id = "iterator", method = "Lit/unimi/dsi/fastutil/objects/ObjectArrayList;iterator()Lit/unimi/dsi/fastutil/objects/ObjectListIterator;")
	@Expression("this.affectedBlocks.iterator()")
	@ModifyExpressionValue(method = "affectWorld", at = @At("MIXINEXTRAS:EXPRESSION"))
	public ObjectListIterator intersectsWithStructure(ObjectListIterator original, @Share("filtered")LocalBooleanRef wasFiltered) {
		if (this.world instanceof ServerWorld serverWorld) {

			return new ObjectListIterator(){


				Iterator filtered1 = Iterators.filter(original, pos -> {
					boolean e = serverWorld.getStructureManager().getStructureStartAt(
						(BlockPos) pos,
						RegistryKey.of(RegistryKeys.STRUCTURE_FEATURE,
							new Identifier("quilted_arrow:waystone_tower"))
					) == StructureStart.DEFAULT;
					if(!e) wasFiltered.set(true);
					return e;
				});


				@Override
				public boolean hasNext() {
					return filtered1.hasNext();
				}

				@Override
				public Object next() {
					return filtered1.next();
				}

				@Override
				public Object previous() {return null;}
				@Override
				public int nextIndex() {return 0;}
				@Override
				public int previousIndex() {return 0;}
				@Override
				public boolean hasPrevious() {return false;}

			};
		}
		return original;
	}

	@Inject(method = "affectWorld", at = @At("TAIL"))
	public void onExplosionWithinBounds(boolean particles, CallbackInfo ci, @Share("filtered") LocalBooleanRef wasFiltered){
		if(wasFiltered.get()){
			if(this.world.isClient) this.world.playSound(
				this.x, this.y, this.z,
				SoundEvents.BLOCK_FIRE_EXTINGUISH,
				SoundCategory.BLOCKS,
				4.0F,
				(1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F,
				false
			);
			if(this.getCausingEntity() instanceof PlayerEntity player){
				player.sendSystemMessage(Text.of("&l&cThe explosion seems to not affect the tower."));
			}
		}
	}
}

