package dropspread;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class DropSpread implements ModInitializer {
	public static GameRule<Double> DROP_SPREAD = GameRuleBuilder.forDouble(0.1)
			.category(GameRuleCategory.DROPS)
			.range(0d, 10d)
			.buildAndRegister(Identifier.fromNamespaceAndPath("drop_spread", "drop_spread"));

	@Override
	public void onInitialize() {

	}
}