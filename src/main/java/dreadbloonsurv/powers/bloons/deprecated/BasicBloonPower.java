package dreadbloonsurv.powers.bloons.deprecated;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
@Deprecated
public class BasicBloonPower extends BaseBloon {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

        public static String generatePowerID(Class<?> clazz) {
            return makeID(clazz.getSimpleName());
        }

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public BasicBloonPower(AbstractCreature owner, int delay, int health) {
            super(POWER_ID, "Basic Bloon", TYPE, TURN_BASED, owner, delay, health);
        }

        public BasicBloonPower(AbstractCreature owner, int delay, int health, String name) {
            super(POWER_ID, name, TYPE, TURN_BASED, owner, delay, health);
        }

        public BasicBloonPower(AbstractCreature owner, int delay, int health, int shield) {
            super(POWER_ID, "Basic Bloon", TYPE, TURN_BASED, owner, delay, health, shield, 0, false);
        }

        public BasicBloonPower(AbstractCreature owner, int delay, int health, int shield, String name) {
            super(POWER_ID, name, TYPE, TURN_BASED, owner, delay, health, shield, 0, false);
        }

        public BasicBloonPower(AbstractCreature owner, int delay, int health, int shield, int armor) {
            super(POWER_ID, "Basic Bloon", TYPE, TURN_BASED, owner, delay, health, shield, armor, false);
        }

        public BasicBloonPower(AbstractCreature owner, int delay, int health, int shield, int armor, String name) {
            super(POWER_ID, name, TYPE, TURN_BASED, owner, delay, health, shield, armor, false);
        }

        public BasicBloonPower(String PowerId, AbstractCreature owner, int delay, int health, int shield, int armor, String name) {
            super(PowerId, name, TYPE, TURN_BASED, owner, delay, health, shield, armor, false);
        }

        public BasicBloonPower(String id, String name, AbstractCreature owner, int delay, int health) {
            super(id, name, TYPE, TURN_BASED, owner, delay, health);
        }

        public BasicBloonPower(String id, AbstractCreature owner, int delay, int health, int shield, int armor, boolean immobile) {
            super(id, "Basic Bloon", TYPE, TURN_BASED, owner, delay, health, shield, armor, immobile);
        }

        public BasicBloonPower(String id, AbstractCreature owner, int delay, int health, int shield, int armor, boolean immobile, String name) {
            super(id, name, TYPE, TURN_BASED, owner, delay, health, shield, armor, immobile);
        }

    public AbstractPower makeCopy() {
            return new BasicBloonPower(this.owner, this.amount, this.amount2, this.amountShield, this.amountArmor, this.name);
        }
}
