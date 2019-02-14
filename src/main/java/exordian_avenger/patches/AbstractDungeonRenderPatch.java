package exordian_avenger.patches;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import exordian_avenger.Exordian_avenger;

@SpirePatch(clz = AbstractDungeon.class, method = "render")
public class AbstractDungeonRenderPatch {
	final static Logger logger = (Logger) LogManager.getLogger(Exordian_avenger.class.getName()); 
	@SpirePostfixPatch
	public static void Patch(AbstractDungeon __instance, SpriteBatch sb) {
		if (AbstractDungeon.screen == RecurrentScreenEnum.RECURRENT_VIEW) {
			//	logger.info("Rendered. Now Showing...");
			//	AbstractCard card = CombatUpdatePatch.recurrentPile.getNCardFromTop(0);
			//	String cardid = card.cardID;
			//logger.info(cardid);
			AbstractDungeonUpdatePatch.viewscreen.render(sb);
		}
		//logger.info("Rendered Pile");
	}

}
