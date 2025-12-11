package dropspread.mixin;

import dropspread.DropSpread;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntity.class)
public abstract class PlayerEntityMixin extends Entity {
	public PlayerEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;setDeltaMovement(DDD)V", ordinal = 0), method = "createItemStackToDrop")
	private void init(Args args) {
		if (!(((LivingEntity)(Object)this) instanceof Player)) return;
		ServerLevel level = (ServerLevel) this.level();
		double spread = level.getGameRules().get(DropSpread.DROP_SPREAD);

		for (int i = 0; i < args.size(); i++)
			args.set(i, (double) args.get(i) * spread);
	}
}