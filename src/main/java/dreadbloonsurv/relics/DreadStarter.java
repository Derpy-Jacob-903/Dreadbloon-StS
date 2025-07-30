package dreadbloonsurv.relics;

import dreadbloonsurv.CharacterFile;
import dreadbloonsurv.cards.colorless.bloons.RockBloon;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.Wiz.makeInHand;

public class DreadStarter extends AbstractEasyRelic {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public DreadStarter() {
        super(ID, RelicTier.STARTER, LandingSound.HEAVY, ModFile.Enums.DREADBLOON_COLOR);
    }

    public void atTurnStart() {
        this.flash();
        RockBloon r = new RockBloon();
        r.costForTurn = 0;
        makeInHand(r);
    }
}
