package dreadbloonsurv.orbs;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dreadbloonsurv.powers.bloons.ToxinPower;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

@Deprecated
public class ToxicBloon extends AbstractBloon {
    public ToxicBloon(int health, int delay, int shield, int armor) {
        super(health, delay, shield, armor, "Toxic Bloon");
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public ToxicBloon(int health, int delay, int shield, int armor, String name) {
        super(health, delay, shield, armor, name);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public ToxicBloon(int health, int delay, int shield, int armor, String name, Color color) {
        super(health, delay, shield, armor, name, color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public ToxicBloon(int health, int delay, int shield, int armor, Color color) {
        super(health, delay, shield, armor, "Toxic Bloon", color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }

    @Override
    public void onLeaked(AbstractCreature target) {
        addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new ToxinPower(target, 2)));
    }
}

