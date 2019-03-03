package exordian_avenger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MoveSkillstoDiscardPileAction
        extends AbstractGameAction
{


    public MoveSkillstoDiscardPileAction()
    {

    }

    public void update()
    {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        tmp.group.addAll(AbstractDungeon.player.drawPile.group);
        for (AbstractCard c : tmp.group) {
            if(c.type != AbstractCard.CardType.ATTACK)
            {
                AbstractCard b = c;
                AbstractDungeon.player.drawPile.removeCard(b);
                AbstractDungeon.player.discardPile.addToBottom(b);
            }
        }
        tmp.clear();
        this.isDone = true;
    }
}
