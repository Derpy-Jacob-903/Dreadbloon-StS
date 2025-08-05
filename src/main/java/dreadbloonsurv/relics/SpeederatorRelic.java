package dreadbloonsurv.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import dreadbloonsurv.ModFile;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class SpeederatorRelic extends AbstractEasyRelic {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public SpeederatorRelic() {
        super(ID, AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.FLAT, ModFile.Enums.DREAD_COLOR);
    }
}
