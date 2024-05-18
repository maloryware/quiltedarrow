package io.github.maloryware.quilted_arrow.item;

import io.github.maloryware.quilted_arrow.QuiltedArrow;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

// despite this throwing a warning, it's the only way i really managed to get it to work;
// using a method reference (QArrowItems.WOVEN_LEATHER_STRIP::getDefaultStack()) caused a crash
// and so did attempting to just directly pass a Supplier<ItemStack> instance/object

// so yeah. from now on, whenever using QuiltItemGroup#createWithIcon, use "() -> Class.ItemName.getDefaultStack
// when passing the Supplier<ItemStack> parameter

// the @SuppressWarnings decorator below simply tells the IDE to shut the fuck up

@SuppressWarnings("Convert2MethodRef")
public class QArrowItemTab {
	public static final ItemGroup QUILTEDARROWCOMBAT = QuiltItemGroup.createWithIcon(new Identifier(QuiltedArrow.ID, "combat"),
		() -> QArrowItems.WOVEN_LEATHER_PADDING.getDefaultStack());

	public static final ItemGroup QUILTEDARROWCORE = QuiltItemGroup.createWithIcon(new Identifier(QuiltedArrow.ID, "core"),
		() -> QArrowItems.QUILTED_ARROW.getDefaultStack());
}

