package dreadbloonsurv.orbs;

import com.badlogic.gdx.graphics.Color;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class TripleThreatBloon extends TripleBloon {
    public TripleThreatBloon(int health, int delay, int shield, int armor) {
        super(health, delay, shield, armor, "Triple Threat Bloon");
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public TripleThreatBloon(int health, int delay, int shield, int armor, String name) {
        super(health, delay, shield, armor, name);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public TripleThreatBloon(int health, int delay, int shield, int armor, String name, Color color) {
        super(health, delay, shield, armor, name, color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public TripleThreatBloon(int health, int delay, int shield, int armor, Color color) {
        super(health, delay, shield, armor, "Triple Threat Bloon", color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
}
