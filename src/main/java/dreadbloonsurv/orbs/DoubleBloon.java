package dreadbloonsurv.orbs;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class DoubleBloon extends BasicBloon {
    public DoubleBloon(int health, int delay, int shield, int armor) {
        super(health, delay, shield, armor, "Double Bloon");
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public DoubleBloon(int health, int delay, int shield, int armor, String name) {
        super(health, delay, shield, armor, name);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public DoubleBloon(int health, int delay, int shield, int armor, String name, Color color) {
        super(health, delay, shield, armor, name, color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }
    public DoubleBloon(int health, int delay, int shield, int armor, Color color) {
        super(health, delay, shield, armor, "Double Bloon", color);
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
    }

    @Override
    protected String getEvokeAmountString() {
        this.evokeAmount = this.health;
        return this.evokeAmount + "x2";
    }

    @Override
    public void damageTarget(AbstractCreature target, DamageInfo info) {
        addToBot(new DamageAction(target, info, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new DamageAction(target, info, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public AbstractOrb makeCopy() {
        return new DoubleBloon(health, delay, shield, armor, name, bloonColor);
    }
}

