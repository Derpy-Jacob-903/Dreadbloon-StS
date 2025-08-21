package dreadbloonsurv.powers.bloons.deprecated;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
@Deprecated
public class VolatileBloonPower extends BasicBloonPower {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public VolatileBloonPower(AbstractCreature owner, int delay, int health) {
            super(POWER_ID, owner, delay, health, 0, 0, "Nested Bloon");
        }

    public VolatileBloonPower(AbstractCreature owner, int delay, int health, String name) {
        super(POWER_ID, owner, delay, health, 0, 0, name);
    }
    public VolatileBloonPower(AbstractCreature owner, int delay, int health, int armor, String name, boolean immobile) {
        super(POWER_ID, owner, delay, health, 0, armor, immobile, name);
    }

    public void onPopped() {
        addToTop(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, 10, DamageInfo.DamageType.THORNS)));
        addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, 10, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    public AbstractPower makeCopy() {
            return new VolatileBloonPower(this.owner, this.amount, this.amount2);
        }
}
