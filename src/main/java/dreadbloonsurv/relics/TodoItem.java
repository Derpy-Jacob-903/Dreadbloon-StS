package dreadbloonsurv.relics;

import dreadbloonsurv.CharacterFile;

import static dreadbloonsurv.ModFile.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, CharacterFile.Enums.DREADBLOON_COLOR);
    }
}
