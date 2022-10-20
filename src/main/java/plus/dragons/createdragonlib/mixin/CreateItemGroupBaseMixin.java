package plus.dragons.createdragonlib.mixin;

import com.simibubi.create.foundation.item.CreateItemGroupBase;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import plus.dragons.createdragonlib.api.event.FillCreateItemGroupEvent;

@Mixin(CreateItemGroupBase.class)
public class CreateItemGroupBaseMixin {

    @Inject(method = "fillItemList", at = @At("TAIL"))
    private void postFillCreateItemGroupEvent(NonNullList<ItemStack> items, CallbackInfo ci) {
//        var event = new FillCreateItemGroupEvent((CreateItemGroupBase) (Object) this, items);
//        MinecraftForge.EVENT_BUS.post(event);
//        event.apply();

        InteractionResult result = FillCreateItemGroupEvent
                .FillCreateItemGroupCallBack
                .EVENT
                .invoker()
                .interact((CreateItemGroupBase) (Object) this, items);

        if (result == InteractionResult.FAIL) {
            ci.cancel();
        }
    }

}
