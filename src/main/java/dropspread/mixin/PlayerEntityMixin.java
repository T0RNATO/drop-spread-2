package dropspread.mixin;

import dropspread.DropSpread;
import dropspread.EntityAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends Entity {
	public PlayerEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}
	@ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;setDeltaMovement(DDD)V"),
			method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;")
	private void init(Args args) {
		Level level = ((EntityAccessor) this).getLevel();
		double spread = level.getGameRules().getRule(DropSpread.DROP_SPREAD).get();
		for(int i = 0; i < args.size(); i++) args.set(i, (double)args.get(i) * spread);
	}
}