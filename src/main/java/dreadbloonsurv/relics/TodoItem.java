package dreadbloonsurv.relics;

import dreadbloonsurv.ModFile;

import static dreadbloonsurv.ModFile.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, ModFile.Enums.DREAD_COLOR);
    }
}
