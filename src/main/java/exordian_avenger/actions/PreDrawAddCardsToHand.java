package exordian_avenger.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.BaseMod;
import exordian_avenger.Exordian_avenger;
import exordian_avenger.patches.CombatUpdatePatch;
import exordian_avenger.patches.RecurrentCardEnum;

public class PreDrawAddCardsToHand extends AbstractGameAction {
    public static CardGroup NewRecurrentPile = new CardGroup(RecurrentCardEnum.NEW_RECURRENT_CARDS);
    public static ArrayList<Integer> NewCounter = new ArrayList<Integer>(0);
    public static int Attacksocurredthisturn;
    public static boolean CardImprovedThisTurn;

    @Override
    public void update() {

        final Logger logger = (Logger) LogManager.getLogger(Exordian_avenger.class.getName());
        Attacksocurredthisturn = 0;
        CardImprovedThisTurn = false;
        for (int i = 0; i < CombatUpdatePatch.recurrentPile.size(); i++) {

            logger.info(CombatUpdatePatch.counter.get(i));
            CombatUpdatePatch.counter.set(i, CombatUpdatePatch.counter.get(i) - 1);
            AbstractCard card = CombatUpdatePatch.recurrentPile.getNCardFromTop(i);
            logger.info(i);
            logger.info(CombatUpdatePatch.counter.get(i));
            String cardid = card.cardID;
            logger.info(cardid);
            if (CombatUpdatePatch.counter.get(i) == 0) {
                if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {

                    if (card.type == AbstractCard.CardType.ATTACK) {
                        Attacksocurredthisturn++;
                    }
                    card.unfadeOut();

                    card.unhover();

                    if (AbstractDungeon.player.hasPower("exordian_avenger:proficiency") && (card.baseDamage > 0 || card.baseBlock > 0) && CardImprovedThisTurn == false) {
                        card.baseDamage += AbstractDungeon.player.getPower("exordian_avenger:proficiency").amount;
                        card.baseBlock += AbstractDungeon.player.getPower("exordian_avenger:proficiency").amount;
                        CardImprovedThisTurn = true;
                    }
                    if(card.cardID.equals("exordian_avenger:waitforit")) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 3), 3));
                    }
                    if(card.cardID.equals("exordian_avenger:gnaw") && card.baseMagicNumber > 0) {
                       card.baseMagicNumber -=1;
                       card.magicNumber = card.baseMagicNumber;

                    }
                    AbstractDungeon.player.hand.addToHand(card);
                } else {

                    if (card.type == AbstractCard.CardType.ATTACK) {
                        Attacksocurredthisturn++;
                    }

                    card.unfadeOut();

                    card.unhover();

                    if (AbstractDungeon.player.hasPower("exordian_avenger:proficiency") && (card.baseDamage > 0 || card.baseBlock > 0) && CardImprovedThisTurn == false) {
                        card.baseDamage += AbstractDungeon.player.getPower("exordian_avenger:proficiency").amount;
                        card.baseBlock += AbstractDungeon.player.getPower("exordian_avenger:proficiency").amount;
                        CardImprovedThisTurn = true;
                    }
                    if(card.cardID.equals("exordian_avenger:waitforit")) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 3), 3));
                    }
                    if(card.cardID.equals("exordian_avenger:gnaw") && card.baseMagicNumber > 0) {
                        card.baseMagicNumber -= 1;
                        card.magicNumber = card.baseMagicNumber;
                    }
                    AbstractDungeon.player.drawPile.addToTop(card);
                }
            } else {
                NewRecurrentPile.addToBottom(card);
                NewCounter.add(CombatUpdatePatch.counter.get(i));
            }

            AbstractDungeon.player.hand.refreshHandLayout();
        }
        CombatUpdatePatch.recurrentPile.clear();
        CombatUpdatePatch.counter.clear();
        for (int i = 0; i < NewCounter.size(); i++) {
            CombatUpdatePatch.recurrentPile.addToBottom(NewRecurrentPile.getNCardFromTop(i));
            ;
            CombatUpdatePatch.counter.add(NewCounter.get(i));
        }

        NewCounter.clear();
        NewRecurrentPile.clear();
        AbstractDungeon.player.hand.refreshHandLayout();
        this.isDone = true;
        return;
    }

}

