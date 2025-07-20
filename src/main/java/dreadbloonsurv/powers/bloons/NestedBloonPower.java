package dreadbloonsurv.powers.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static dreadbloonsurv.ModFile.makeID;

public class NestedBloonPower extends BasicBloonPower {
        public static final String POWER_ID = makeID("NestedBloonPower");

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public BasicBloonPower NESTED_BLOON = new BasicBloonPower(this.owner, 0, 4, "Opossum");

        public NestedBloonPower(AbstractCreature owner, int delay, int health, BasicBloonPower nestedBloon)
        {
            super(POWER_ID, owner, delay, health, 0, 0, "Nested Bloon");
            NESTED_BLOON = nestedBloon;
        }

    public NestedBloonPower(AbstractCreature owner, int delay, int health, String name, BasicBloonPower nestedBloon) {
        super(POWER_ID, owner, delay, health, 0, 0, name);
        NESTED_BLOON = nestedBloon;
    }
    public NestedBloonPower(AbstractCreature owner, int delay, int health, int armor, String name, boolean immobile, BasicBloonPower nestedBloon) {
        super(POWER_ID, owner, delay, health, 0, armor, immobile, name);
        NESTED_BLOON = nestedBloon;
    }

    public void onDestroyed() {
        addToBot(new ApplyPowerAction(this.owner, AbstractDungeon.player, NESTED_BLOON, 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractPower makeCopy() {
            return new NestedBloonPower(this.owner, this.amount, this.amount2, this.NESTED_BLOON);
        }
}
