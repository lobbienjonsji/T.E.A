package exordian_avenger.patches;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import basemod.BaseMod;
import exordian_avenger.Exordian_avenger;

@SpirePatch(clz = GameActionManager.class, method = "getNextAction")

public class GameActionManagerAddRecurrentCardsPatch {
	public static CardGroup NewRecurrentPile = new CardGroup(RecurrentCardEnum.NEW_RECURRENT_CARDS);
	public static ArrayList<Integer> NewCounter = new ArrayList<Integer>(0);
	@SpireInsertPatch(rloc = 205)
	public static void patch(GameActionManager __instance) {
		final Logger logger = (Logger) LogManager.getLogger(Exordian_avenger.class.getName()); 
		
		for (int i = 0; i < CombatUpdatePatch.recurrentPile.size(); i++) {
			logger.info(CombatUpdatePatch.counter.get(i));
			CombatUpdatePatch.counter.set(i,  CombatUpdatePatch.counter.get(i) -1);
			AbstractCard card = CombatUpdatePatch.recurrentPile.getNCardFromTop(i);
			logger.info(i);
			logger.info(CombatUpdatePatch.counter.get(i));
			String cardid = card.cardID;
			logger.info(cardid);
			if (CombatUpdatePatch.counter.get(i) == 0) {

				if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {

					AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(card));
					card.unfadeOut();

				} else {
					AbstractDungeon.player.drawPile.addToTop(card);
					card.unfadeOut();
				}
			}
			else
			{
				NewRecurrentPile.addToBottom(card);
				NewCounter.add(CombatUpdatePatch.counter.get(i));
			}
			
			AbstractDungeon.player.hand.refreshHandLayout();
		}
		CombatUpdatePatch.recurrentPile.clear();
		CombatUpdatePatch.counter.clear();
		for (int i = 0; i < NewCounter.size(); i++)
		{
			CombatUpdatePatch.recurrentPile.addToBottom(NewRecurrentPile.getNCardFromTop(i));;
			CombatUpdatePatch.counter.add(NewCounter.get(i));
		}
		
		NewCounter.clear();
		NewRecurrentPile.clear();
		AbstractDungeon.player.hand.refreshHandLayout();
	}

}
