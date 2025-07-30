package dreadbloonsurv.relics;

import dreadbloonsurv.CharacterFile;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class Ratchet extends AbstractEasyRelic {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public Ratchet() {
        super(ID, RelicTier.BOSS, LandingSound.SOLID, ModFile.Enums.DREADBLOON_COLOR);
    }
}
