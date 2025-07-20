package dreadbloonsurv.util;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;

@SpirePatch(
        clz = AbstractCreature.class,
        method = "applyStartOfTurnPowers"
)
public class applyStartOfTurnPowers {
    @SpirePostfixPatch
    public static void Postfix (AbstractCreature ___instance) {
        //CascadeHandler s = new CascadeHandler();
        //s.cascade(___instance);
    }
}

