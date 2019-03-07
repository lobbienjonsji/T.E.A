package exordian_avenger.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class DrainedPower extends AbstractPower {
    public static final String POWER_ID = "exordian_avenger:drained";
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("exordian_avenger:drained");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final Texture TEXTURE = new com.badlogic.gdx.graphics.Texture("tea/img/powers/drained.png");


    public DrainedPower(AbstractCreature owner, int amt)

    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amt;

        this.isTurnBased = false;
        this.img = TEXTURE;
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    public void atEndOfRound()
    {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "exordian_avenger:drained"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "exordian_avenger:drained", 1));
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + Math.round(100.0 - 100.0*Math.pow(0.85, this.amount)) +DESCRIPTIONS[1]);
    }
    public float atDamageGive(float DamageAmount)
    {
        return DamageAmount * (float)Math.pow(0.85, this.amount);
    }
}
