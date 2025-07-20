package dreadbloonsurv.powers;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static dreadbloonsurv.ModFile.makeID;

public class OnFire extends AbstractEasyPower implements HealthBarRenderPower {
        public static final String POWER_ID = makeID("OnFire");

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = false;

        public OnFire(AbstractCreature owner, int amount) {
            super(POWER_ID, "On Fire", TYPE, false, owner, amount);
        }

    public void atStartOfTurn()
        {
            addToBot((AbstractGameAction)new LoseHPAction(this.owner, this.owner, this.amount, AbstractGameAction.AttackEffect.FIRE));
        }

        public void updateDescription()
        {
            this.description = this.DESCRIPTIONS[0] + this.amount + this.DESCRIPTIONS[1];
        }

        public AbstractPower makeCopy() {
            return new OnFire(this.owner, this.amount);
        }

        public int getHealthBarAmount() {
            return this.amount;
        }

        public Color getColor() {
            return Color.FIREBRICK;
        }
    }
