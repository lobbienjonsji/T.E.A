package exordian_avenger.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.vfx.combat.ThrowShivEffect;
import exordian_avenger.patches.CombatUpdatePatch;
import exordian_avenger.powers.FragilePower;

public class Spit extends CustomCard {
    public static final String ID = "exordian_avenger:spit";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "tea/img/cards/betaskill.png";
    private static final int COST = 0;

    public Spit() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, CardColor.COLORLESS,
                AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY);
        this.exhaust = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeMagicNumber(+1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(
                    new VFXAction(new ThrowShivEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale)));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.baseMagicNumber));
        if (AbstractDungeon.player.hasPower("exordian_avenger:stickysaliva")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -AbstractDungeon.player.getPower("exordian_avenger:stickysaliva").amount), -AbstractDungeon.player.getPower("exordian_avenger:stickysaliva").amount));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new GainStrengthPower(m, AbstractDungeon.player.getPower("exordian_avenger:stickysaliva").amount), AbstractDungeon.player.getPower("exordian_avenger:stickysaliva").amount));
        }
        if (AbstractDungeon.player.hasPower("exordian_avenger:contagioussaliva")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, AbstractDungeon.player.getPower("exordian_avenger:contagioussaliva").amount), AbstractDungeon.player.getPower("exordian_avenger:contagioussaliva").amount));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FragilePower(m, AbstractDungeon.player.getPower("exordian_avenger:contagioussaliva").amount), AbstractDungeon.player.getPower("exordian_avenger:contagioussaliva").amount));
        }
    }
}