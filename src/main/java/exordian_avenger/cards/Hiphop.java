package exordian_avenger.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import exordian_avenger.actions.AddToRecPile;
import exordian_avenger.actions.HiphopAction;
import exordian_avenger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

public class Hiphop extends CustomCard {
    public static final String ID = "exordian_avenger:hiphop";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "tea/img/cards/betaskill.png";
    private static final int COST = 1;

    public Hiphop() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.EX_DARK_RED,
                AbstractCard.CardRarity.COMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new HiphopAction(false));
    }
}