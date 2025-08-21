package dreadbloonsurv.orbs;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class MustHitBloon extends AbstractBloon {
    public MustHitBloon(int health, int delay, int shield, int armor) {
        super(health, delay, shield, armor, "Must Hit Bloon");
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public MustHitBloon(int health, int delay, int shield, int armor, String name) {
        super(health, delay, shield, armor, name);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public MustHitBloon(int health, int delay, int shield, int armor, String name, Color color) {
        super(health, delay, shield, armor, name, color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public MustHitBloon(int health, int delay, int shield, int armor, Color color) {
        super(health, delay, shield, armor, "Must Hit Bloon", color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }

    public AbstractOrb makeCopy() {
        return new MustHitBloon(health, delay, shield, armor, name, bloonColor);
    }
}

