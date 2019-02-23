package exordian_avenger.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.PoisonPower;


public class HemotoxicityPower extends AbstractPower {
    public static final String POWER_ID = "exordian_avenger:hemtox";
    public static final Texture TEXTURE = new com.badlogic.gdx.graphics.Texture("tea/img/powers/hemtox.png");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("exordian_avenger:hemtox");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static PowerType POWER_TYPE = PowerType.BUFF;


    public HemotoxicityPower(AbstractCreature owner, int amt)

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
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if ((damageAmount > 0))
        {
            flash();
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if ((!m.isDead) && (!m.isDying)) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this.owner, new PoisonPower(m, this.owner, this.amount), this.amount));
                }
            }
        }
        return damageAmount;
    }

    public void updateDescription() {

        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

}
