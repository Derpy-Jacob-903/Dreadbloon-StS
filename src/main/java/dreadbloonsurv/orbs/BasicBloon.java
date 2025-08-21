package dreadbloonsurv.orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class BasicBloon extends AbstractBloon {
    public BasicBloon(int health, int delay, int shield, int armor) {
        super(health, delay, shield, armor, "Basic Bloon");
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public BasicBloon(int health, int delay, int shield, int armor, String name) {
        super(health, delay, shield, armor, name);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public BasicBloon(int health, int delay, int shield, int armor, String name, Color color) {
        super(health, delay, shield, armor, name, color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public BasicBloon(int health, int delay, int shield, int armor, Color color) {
        super(health, delay, shield, armor, "Basic Bloon", color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public AbstractOrb makeCopy() {
        return new BasicBloon(health, delay, shield, armor, name, bloonColor);
    }
}

