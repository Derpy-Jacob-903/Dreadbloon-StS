package dreadbloonsurv.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.colorless.bloons.SentinelBloon;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.Wiz.makeInHand;

public class DreadStarterUpgrade extends AbstractEasyRelic {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public DreadStarterUpgrade() {
        super(ID, RelicTier.STARTER, LandingSound.HEAVY, ModFile.Enums.DREAD_COLOR);
    }

    public void atTurnStart() {
        this.flash();
        SentinelBloon r = new SentinelBloon();
        r.costForTurn -= 1;
        makeInHand(r);
    }

    private static final String starterReplaceID = DreadStarter.ID;
    @Override
    public void obtain() {
        // Replace the starter relic, or just give the relic if starter isn't found
        if (AbstractDungeon.player.hasRelic(starterReplaceID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(starterReplaceID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(starterReplaceID);
    }
}
