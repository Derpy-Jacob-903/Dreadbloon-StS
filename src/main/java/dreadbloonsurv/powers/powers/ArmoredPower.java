package dreadbloonsurv.powers.powers;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.powers.AbstractEasyPower;

import static dreadbloonsurv.ModFile.makeID;

public class ArmoredPower extends AbstractEasyPower {
        public static final String POWER_ID = makeID("ArmoredPower");

        private static final PowerType TYPE = PowerType.BUFF;

        private static final boolean TURN_BASED = false;

        public ArmoredPower(AbstractCreature owner, int amount) {
            super(POWER_ID, "Armored", TYPE, false, owner, amount);
            this.canGoNegative = false;
        }

    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractCreature p = this.owner;
        if (info.type != DamageInfo.DamageType.HP_LOSS) {
            if (p.currentBlock >= info.output) {
                // All damage absorbed, nothing leaks into HP → armor doesn't apply
                return damageAmount;
            } else {
                // Damage breaks block → potentially apply armor
                int unblockedDamage = info.output - p.currentBlock;
                return Math.max(unblockedDamage - this.amount, 0) + p.currentBlock;
            }
        }
        // HP_LOSS damage type → armor doesn’t apply
        return damageAmount;
    }


    public void updateDescription() {
            this.description = this.DESCRIPTIONS[0] + this.amount + this.DESCRIPTIONS[1];
        }

        public AbstractPower makeCopy() {
            return new ArmoredPower(this.owner, this.amount);
        }
    }
