package exordian_avenger.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RepairPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import exordian_avenger.patches.AbstractCardEnum;

public class SplinterStrike extends CustomCard {
    public static final String ID = "exordian_avenger:splinterstrike";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "tea/img/cards/betaattack.png";
    private static final int COST = 1;
    private static final int DAMAGE = 1;

    public SplinterStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCardEnum.EX_DARK_RED,
                CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        upgradeName();
        upgradeDamage(2);
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        AbstractDungeon.actionManager.addToTop(new LoseHPAction(p, p, this.magicNumber));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, 2), 2));

    }

}
