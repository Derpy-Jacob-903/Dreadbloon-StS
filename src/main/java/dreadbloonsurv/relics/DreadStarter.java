package dreadbloonsurv.relics;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.colorless.bloons.RockBloon;
import dreadbloonsurv.orbs.BasicBloon;
import dreadbloonsurv.orbs.MustHitBloon;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.Wiz.atb;
import static dreadbloonsurv.util.Wiz.makeInHand;

public class DreadStarter extends AbstractEasyRelic {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public Boolean notUpgrading = true;

    public DreadStarter() {
        super(ID, RelicTier.STARTER, LandingSound.HEAVY, ModFile.Enums.DREAD_COLOR);
    }

    public void atTurnStart() {
        this.flash();
        atb(new ChannelAction(new MustHitBloon(9, 3, 0, 0, "Rock Bloon", Color.ORANGE)));
    }

    public void onUnequip() {
        if (notUpgrading) {
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new RockBloon(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new RockBloon(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        }
    }
}
