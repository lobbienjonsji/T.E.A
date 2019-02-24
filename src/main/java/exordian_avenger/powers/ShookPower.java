package exordian_avenger.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;

public class ShookPower extends AbstractPower {
    public static final String POWER_ID = "exordian_avenger:shook";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("exordian_avenger:shook");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final Texture TEXTURE = new com.badlogic.gdx.graphics.Texture("tea/img/powers/shook.png");


    public ShookPower(AbstractCreature owner, int amt)

    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amt;

        this.isTurnBased = true;
        this.img = TEXTURE;
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if (card.type == AbstractCard.CardType.ATTACK)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, AbstractDungeon.player, new StrengthPower(owner, -1), -1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, AbstractDungeon.player, new GainStrengthPower(owner, 1), 1));
        }
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    public void updateDescription()
    {
        if (this.amount == 1)
        {
            this.description = (DESCRIPTIONS[0]);
        }
        else
            {
            this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
        }
    }
    public void atEndOfRound()
    {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "exordina_avenger:shook"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "exordina_avenger:shook", 1));
        }
    }
}
