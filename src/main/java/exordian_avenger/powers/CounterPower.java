package exordian_avenger.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;


public class CounterPower extends AbstractPower {
	public static final String POWER_ID = "exordian_avenger:counter";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Creative AI"); 
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
			public CounterPower(AbstractCreature owner, int amt)
			
	  {
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    updateDescription();
	    loadRegion("counter");
	  }	
	public void atStartofturn()
	{
		
	}

}
