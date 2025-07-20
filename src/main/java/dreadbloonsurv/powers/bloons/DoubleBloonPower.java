package dreadbloonsurv.powers.bloons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.atb;
import static dreadbloonsurv.util.Wiz.removePower;

public class DoubleBloonPower extends BasicBloonPower {
        public static final String POWER_ID = makeID("DoubleBloonPower");

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public DoubleBloonPower(AbstractCreature owner, int delay, int health) {
            super(POWER_ID, owner, delay, health, 0, 0 , false, "Double Bloon");
        }

        public DoubleBloonPower(AbstractCreature owner, int delay, int health, String name) {
            super(POWER_ID, owner, delay, health, 0, 0 , false, name);
        }

        public DoubleBloonPower(AbstractCreature owner, int delay, int health, int shield, int armor, boolean immobile, String name) {
            super(POWER_ID, owner, delay, health, shield, armor, immobile, name);
        }


        public DoubleBloonPower(String id, String name, AbstractCreature owner, int delay, int health) {
            super(id, owner, delay, health, 0, 0 , false, name);
        }

    @Override
    public void atStartOfTurn()
    {
        if (this.amount < 1)
        {
            if (amount2 > 74.5)
            {
                atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
            else
            {
                atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
            this.onLeaked();
            this.onDestroyed();
            removePower(this);
        }
        else {this.amount--;}
    }
    public AbstractPower makeCopy() {
            return new DoubleBloonPower(this.owner, this.amount, this.amount2);
        }
}
