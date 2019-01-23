package exordian_avenger;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;

import exordian_avenger.relics.*;
import exordian_avenger.cards.*;

import patches.AbstractCardEnum;


@SpireInitializer
public class Exordian_avenger implements EditRelicsSubscriber, EditStringsSubscriber, EditCardsSubscriber  {
	final Logger logger = LogManager.getLogger(Exordian_avenger.class.getName()); 
	public static final String ASSETS_FOLDER = "img";
	
	public static Color DARK_RED = Color.FIREBRICK;
	public Exordian_avenger() {
		 
		 	BaseMod.subscribe(this);
		 	BaseMod.addColor(AbstractCardEnum.EX_DARK_RED,
		 			DARK_RED, 
		 			DARK_RED, 
		 			DARK_RED, 
		 			DARK_RED, 
		 			DARK_RED, 
		 			DARK_RED, 
		 			DARK_RED, 
		 			"tea/img/cardui/bg_attack_darkredattack_512.png", 
		 			"tea/img/cardui/bg_skill_darkredskill_512.png", 
		 			"tea/img/cardui/bg_power_darkredpower_512.png",
		 			"tea/img/cardui/card_darkredorb_512.png",
		 			"tea/img/cardui/bg_attack_darkredattack.png",
		 			"tea/img/cardui/bg_skill_darkredskill.png", 
		 			"tea/img/cardui/bg_power_darkredpower.png",
		 			"tea/img/cardui/darkredorb.png", 
		 			"tea/img/cardui/description_darkredorb.png");
	        	    }
	 	@SuppressWarnings("unused")
	    public static void initialize() {
	    	
	        Exordian_avenger exordian_avenger = new Exordian_avenger();
	       
	    }
	    	    
		@Override
		public void receiveEditRelics() {
			logger.info("Adding Relics");
			BaseMod.addRelic(new exordian_aegis(), RelicType.SHARED);	
		}
		@Override
	    public void receiveEditStrings() {   
	        String relicStrings = Gdx.files.internal("tea/localization/relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
	        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
	        String cardStrings = Gdx.files.internal("tea/localization/cards.json").readString(String.valueOf(StandardCharsets.UTF_8));
	        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
	        String powerStrings = Gdx.files.internal("tea/localization/powers.json").readString(String.valueOf(StandardCharsets.UTF_8));
	        BaseMod.loadCustomStrings(CardStrings.class, powerStrings);
		}

		@Override
		public void receiveEditCards() {
			logger.info("Adding Cards");
			BaseMod.addCard(new Perfectionism());
			BaseMod.addCard(new Curlup());
			BaseMod.addCard(new Defend());
			
		}

}
