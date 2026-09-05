package tema.titanium.client.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class TitaniumMixin {
	@Inject(at = @At("HEAD"), method = "isCurrentlyGlowing")
	private void glowIfTitanium(CallbackInfoReturnable<Boolean> cir) {
		Entity entity = (Entity) (Object) this;
        if(entity.getType() == EntityTypes.ITEM && ((ItemEntity)entity).getItem().is(Items.IRON_INGOT)){
            cir.setReturnValue((true));
        }
	}
}