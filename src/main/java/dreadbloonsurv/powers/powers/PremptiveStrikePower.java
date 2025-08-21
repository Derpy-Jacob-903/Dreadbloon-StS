package dreadbloonsurv.powers.powers;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.powers.AbstractEasyPower;

import static dreadbloonsurv.ModFile.makeID;

public class PremptiveStrikePower extends AbstractEasyPower {
        public static final String POWER_ID = makeID("PremptiveStrikePower");

        private static final PowerType TYPE = PowerType.BUFF;

        private static final boolean TURN_BASED = false;

        public PremptiveStrikePower(AbstractCreature owner, int amount) {
            super(POWER_ID, "Shield", TYPE, false, owner, amount);
            this.canGoNegative = false;
        }

    public void atStartOfTurn() {
        addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    public void updateDescription() {
            this.description = this.DESCRIPTIONS[0] + this.amount + this.DESCRIPTIONS[1];
        }

        public AbstractPower makeCopy() {
            return new PremptiveStrikePower(this.owner, this.amount);
        }
    }
