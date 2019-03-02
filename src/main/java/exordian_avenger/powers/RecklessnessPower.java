package exordian_avenger.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RecklessnessPower extends AbstractPower {
    public static final String POWER_ID = "exordian_avenger:reckless";
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings("exordian_avenger:reckless");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final Texture TEXTURE = new com.badlogic.gdx.graphics.Texture("tea/img/powers/reckless.png");

    public RecklessnessPower(AbstractCreature owner, int Amount)

    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = Amount;
        this.isTurnBased = false;
        this.img = TEXTURE;
        this.type = POWER_TYPE;
        updateDescription();
    }
    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = (DESCRIPTIONS[0]);
        } else {
            this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
        }

    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK)
        {
            flash();
            card.exhaust = true;
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.owner, this.amount));
        }
    }
}
