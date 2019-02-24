package exordian_avenger.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import exordian_avenger.patches.AbstractCardEnum;
import exordian_avenger.patches.CombatUpdatePatch;

public class Remedy extends CustomCard {
    public static final String ID = "exordian_avenger:remedy";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "tea/img/cards/betaskill.png";
    private static final int COST = 1;

    public Remedy() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCardEnum.EX_DARK_RED,
                CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.exhaust =true;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        upgradeName();
        this.rawDescription = Remedy.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber), this.magicNumber));
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        }
        AbstractCard Copy = this.makeStatEquivalentCopy();
        Copy.unfadeOut();
        CombatUpdatePatch.recurrentPile.addToBottom(Copy);
        CombatUpdatePatch.counter.add(1);
    }
}