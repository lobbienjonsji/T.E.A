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

public class Drawdownpower extends AbstractPower {
    public static final String POWER_ID = "exordian_avenger:drawdown";
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("exordian_avenger:drawdown");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final Texture TEXTURE = new com.badlogic.gdx.graphics.Texture("tea/img/powers/drawdown.png");


    public Drawdownpower(AbstractCreature owner, int amt)

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
    public void onInitialApplication()
    {
        AbstractDungeon.player.gameHandSize -= 1;
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }
    public void atStartOfTurnPostDraw()
    {
        AbstractDungeon.player.gameHandSize += amount;
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "exordian_avenger:drawdown"));
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
}
