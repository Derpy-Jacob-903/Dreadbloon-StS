package dreadbloonsurv.relics;

import dreadbloonsurv.ModFile;
import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class PickleJar extends AbstractEasyRelic {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public PickleJar() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, ModFile.Enums.DREAD_COLOR);
    }
}
