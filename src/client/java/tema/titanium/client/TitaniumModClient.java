package tema.titanium.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TitaniumModClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("titanium-mod"); // DEBUG!!!!!!!!!
	@Override
	public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(client.level == null || client.player == null) return;

            double x = client.player.getX();
            double y = client.player.getY();
            double z = client.player.getZ();
            AABB box = AABB.ofSize(new Vec3(x, y, z), 32, 32, 32);

            List<ItemEntity> titanium_items = client.level.getEntitiesOfClass(ItemEntity.class, box, entity ->
            {
                ItemStack item = ((ItemEntity)entity).getItem();
                return item.is(Items.IRON_INGOT); // && item.getItemName().getString().equals("Titanium Ingot");
            });

            for(ItemEntity titanium : titanium_items) {
                LOGGER.info("OH MY GOD ITS TITANIUM!!!!!!!");
                titanium.setGlowingTag(true);
            }
        });
	}
}