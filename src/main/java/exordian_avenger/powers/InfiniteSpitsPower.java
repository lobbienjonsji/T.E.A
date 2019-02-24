package exordian_avenger.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.colorless.Shiv;



public class InfiniteSpitsPower extends AbstractPower {
    public static final String POWER_ID = "exordian_avenger:spits";
    public static AbstractPower.PowerType POWER_TYPE = AbstractPower.PowerType.BUFF;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("exordian_avenger:spits");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final Texture TEXTURE = new com.badlogic.gdx.graphics.Texture("tea/img/powers/spits.png");


    public InfiniteSpitsPower(AbstractCreature owner, int amt)

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
    
    public void atStartOfTurn()
    {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())
        {
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Shiv(), this.amount, false));
        }
    }

    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription()
    {
        if (this.amount > 1) {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
        } else {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2]);
        }
    }
}
