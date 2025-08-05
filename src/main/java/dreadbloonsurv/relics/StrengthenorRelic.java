package dreadbloonsurv.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dreadbloonsurv.ModFile;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class StrengthenorRelic extends AbstractEasyRelic {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public StrengthenorRelic() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, ModFile.Enums.DREAD_COLOR);
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
