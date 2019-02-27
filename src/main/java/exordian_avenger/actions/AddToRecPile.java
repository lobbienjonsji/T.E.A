package exordian_avenger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import exordian_avenger.patches.CombatUpdatePatch;


public class AddToRecPile   extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean anyNumber;
    private boolean canPickZero = false;
    public static int numExhausted;
    private static  int turns;
    public AddToRecPile(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, int howlong)
    {
        this.anyNumber = anyNumber;
        this.canPickZero = canPickZero;
        this.p = ((AbstractPlayer)target);
        this.isRandom = isRandom;
        setValues(target, source, amount);
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.EXHAUST;
        this.turns = howlong;

    }
    public void update()
    {
        int i;
        if (this.duration == Settings.ACTION_DUR_FAST)
        {
            if (this.p.hand.size() == 0)
            {
                this.isDone = true;
                return;
            }
            if ((!this.anyNumber) &&
                    (this.p.hand.size() <= this.amount))
            {
                this.amount = this.p.hand.size();
                numExhausted = this.amount;
                int tmp = this.p.hand.size();
                for (int ew = 0; ew < tmp; ew++)
                {
                    AbstractCard c = this.p.hand.getTopCard();
                    CombatUpdatePatch.recurrentPile.addToBottom(c.makeStatEquivalentCopy());
                    CombatUpdatePatch.counter.add(turns);
                }
                CardCrawlGame.dungeon.checkForPactAchievement();
                this.isDone = true;
                return;
            }
            if (this.isRandom)
            {
                for (i = 0; i < this.amount; i++) {
                    CombatUpdatePatch.recurrentPile.addToBottom(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng).makeStatEquivalentCopy());
                    CombatUpdatePatch.counter.add(turns);
                }
                CardCrawlGame.dungeon.checkForPactAchievement();
                this.isDone = true;
                return;
            }
            else
            {
                numExhausted = this.amount;
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
        {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                CombatUpdatePatch.recurrentPile.addToBottom(c.makeStatEquivalentCopy());
                CombatUpdatePatch.counter.add(turns);
            }
            CardCrawlGame.dungeon.checkForPactAchievement();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}

