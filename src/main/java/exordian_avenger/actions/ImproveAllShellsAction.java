package exordian_avenger.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import exordian_avenger.cards.Shell;

public class ImproveAllShellsAction extends AbstractGameAction
{
    public ImproveAllShellsAction()
    {
    }

    public void update()
    {
        for (AbstractCard c: AbstractDungeon.player.hand.group)
        {
            if(c.cardID.equals("exordian_avenger:shell"))
            {
                c.baseBlock += 1;
            }
        }
        this.isDone = true;
        tickDuration();
    }
}

