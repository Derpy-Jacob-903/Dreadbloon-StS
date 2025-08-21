package dreadbloonsurv.orbs;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class ArbitrOrb extends BasicBloon {
    public ArbitrOrb(int health, int delay, int shield, int armor) {
        super(health, delay, shield, armor, "ARBITR");
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public ArbitrOrb(int health, int delay, int shield, int armor, String name) {
        super(health, delay, shield, armor, name);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public ArbitrOrb(int health, int delay, int shield, int armor, String name, Color color) {
        super(health, delay, shield, armor, name, color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public ArbitrOrb(int health, int delay, int shield, int armor, Color color) {
        super(health, delay, shield, armor, "ARBITR", color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
}

