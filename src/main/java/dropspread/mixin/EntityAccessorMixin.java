package dropspread.mixin;

import dropspread.EntityAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessorMixin extends EntityAccessor {
    @Accessor("level")
    @Override
    Level getLevel();
}