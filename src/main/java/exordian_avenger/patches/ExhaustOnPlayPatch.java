package exordian_avenger.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

@SpirePatch(clz= AbstractCard.class, method="use", paramtypez={
        AbstractPlayer.class,
        AbstractMonster.class
})
public class ExhaustOnPlayPatch {
    @SpirePostfixPatch
    public static void Patch(AbstractCard __instance, AbstractCreature owner, int amount)
    {
        if(AbstractDungeon.player.hasPower("exordian_avenger:reckless") && __instance.type == AbstractCard.CardType.ATTACK)
        {
           __instance.exhaust = true;
        }
    }
}
