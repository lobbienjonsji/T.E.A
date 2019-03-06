package exordian_avenger.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import exordian_avenger.patches.AbstractCardEnum;
import exordian_avenger.patches.CombatUpdatePatch;

public class ChargeUpSpit extends CustomCard {
    public static final String ID = "exordian_avenger:chargeupspit";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "tea/img/cards/betaskill.png";
    private static final int COST = 1;

    public ChargeUpSpit() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.EX_DARK_RED,
                AbstractCard.CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeMagicNumber(+1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
       for(int i = 0; i < this.magicNumber; i++)
       {
           CombatUpdatePatch.recurrentPile.addToBottom(new Spit());
           CombatUpdatePatch.counter.add(1);
       }
    }
}
