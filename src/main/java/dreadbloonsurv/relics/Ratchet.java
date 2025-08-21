package dreadbloonsurv.relics;

import basemod.AutoAdd;
import dreadbloonsurv.ModFile;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

@AutoAdd.Ignore
public class Ratchet extends AbstractEasyRelic {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public Ratchet() {
        super(ID, RelicTier.BOSS, LandingSound.SOLID, ModFile.Enums.DREAD_COLOR);
    }
}
