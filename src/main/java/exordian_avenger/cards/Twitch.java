package exordian_avenger.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import exordian_avenger.actions.AddToRecPile;
import exordian_avenger.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

public class Twitch extends CustomCard {
    public static final String ID = "exordian_avenger:twitch";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "tea/img/cards/betaskill.png";
    private static final int COST = 1;

    public Twitch() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.EX_DARK_RED,
                AbstractCard.CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 9;
    }

    @Override
    public void upgrade() {
        upgradeBlock(+3);
        upgradeName();
        this.rawDescription = Twitch.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new AddToRecPile(p, p, 1, false, false, false, 2, false));
        } else {
            AbstractDungeon.actionManager.addToBottom(new AddToRecPile(p, p, 1, true, false, false, 2, false));
        }

    }
}