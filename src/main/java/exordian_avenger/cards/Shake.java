package exordian_avenger.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;
import exordian_avenger.patches.AbstractCardEnum;
import exordian_avenger.patches.CombatUpdatePatch;

public class Shake extends CustomCard{
    public static final String ID = "exordian_avenger:imshook";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "tea/img/cards/betaskill.png";
    private static final int COST = 0;
    public Shake() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCardEnum.EX_DARK_RED,
                AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        this.baseMagicNumber = 1;
        this.exhaust =true;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        upgradeName();
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, this.magicNumber), this.baseMagicNumber));
        if (this.upgraded) {
            AbstractCard Copy = this.makeStatEquivalentCopy();
            Copy.unfadeOut();
            CombatUpdatePatch.recurrentPile.addToBottom(Copy);
            CombatUpdatePatch.counter.add(3);
        }
    }
}