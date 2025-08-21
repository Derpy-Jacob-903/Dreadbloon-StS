package dreadbloonsurv.relics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.RelicWithButton;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.colorless.bloons.SentinelBloon;
import dreadbloonsurv.orbs.BasicBloon;
import dreadbloonsurv.orbs.MustHitBloon;
import dreadbloonsurv.powers.BloontoniumPower;

import java.util.ArrayList;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.Wiz.atb;
import static dreadbloonsurv.util.Wiz.makeInHand;

public class DreadStarterUpgrade extends AbstractEasyRelic implements RelicWithButton {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public DreadStarterUpgrade() {
        super(ID, RelicTier.BOSS, LandingSound.HEAVY, ModFile.Enums.DREAD_COLOR);
    }

    private static final String starterReplaceID = DreadStarter.ID;
    @Override
    public void obtain() {
        // Replace the starter relic, or just give the relic if starter isn't found
        if (AbstractDungeon.player.hasRelic(starterReplaceID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(starterReplaceID)) {
                    ((DreadStarter)AbstractDungeon.player.relics.get(i)).notUpgrading = false;
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

    @Override
    public Texture getTexture() {
        return this.img;
    }

    @Override
    public void onButtonPress() {
        this.flash();
        atb(new ChannelAction(new MustHitBloon(5, 1, 0, 0, "Sentinel Bloon", Color.SCARLET)));
    }

    @Override
    public boolean isButtonDisabled() {
        return AbstractDungeon.player.getPower(BloontoniumPower.POWER_ID).amount >= 2;
    }

    @Override
    public ArrayList<PowerTip> getHoverTips() {
        return new ArrayList<PowerTip>();
    }
}
