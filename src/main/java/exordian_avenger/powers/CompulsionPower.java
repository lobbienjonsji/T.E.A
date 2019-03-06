package exordian_avenger.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CompulsionPower extends AbstractPower {
    public static final String POWER_ID = "exordian_avenger:compcounter";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("exordian_avenger:compulsion");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final Texture TEXTURE = new com.badlogic.gdx.graphics.Texture("tea/img/powers/compulsion.png");


    public CompulsionPower(AbstractCreature owner, int amt)

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

    public void updateDescription() {

        this.description = (DESCRIPTIONS[0] + (3 - this.amount) + DESCRIPTIONS[1]);
    }
    public void onExhaust(AbstractCard card)
    {
        flash();
        if(AbstractDungeon.player.hasPower("exordian_avenger:compcounter") && AbstractDungeon.player.getPower("exordian_avenger:compcounter").amount < 3)
        {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "exordian_avenger:compcounter"));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new CompulsionCounter(AbstractDungeon.player, 1), 1));
        }
    }
}

