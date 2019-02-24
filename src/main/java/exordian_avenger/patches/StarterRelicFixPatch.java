package exordian_avenger.patches;

//Thanks for the code

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import javassist.CtBehavior;

@SpirePatch(clz = CharacterOption.class, method = "renderRelics", paramtypez = {SpriteBatch.class})
public class StarterRelicFixPatch {

    @SpireInsertPatch(locator = RenderSmartTextLocator.class, localvars = {"relicString"})
    public static void Insert(CharacterOption characterOption, SpriteBatch sb, String relicString) {
        float infoX = (float)ReflectionHacks.getPrivate(characterOption, CharacterOption.class, "infoX");
        float infoY = (float)ReflectionHacks.getPrivate(characterOption, CharacterOption.class, "infoY");
        FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, relicString, infoX + 44.0F * Settings.scale, infoY - 66.0F * Settings.scale, 10000.0F, 30.0F * Settings.scale, Settings.CREAM_COLOR);
        SpireReturn.Return(null);
    }

    private static class RenderSmartTextLocator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior method) throws Exception {
            Matcher matcher = new Matcher.MethodCallMatcher(FontHelper.class, "renderSmartText");
            return new int[]{LineFinder.findAllInOrder(method, matcher)[1]};
        }
    }
}