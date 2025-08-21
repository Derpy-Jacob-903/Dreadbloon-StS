package dreadbloonsurv.powers.bloons;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.powers.AbstractEasyPower;

import static dreadbloonsurv.ModFile.makeID;

public class ToxinPower extends AbstractEasyPower implements HealthBarRenderPower {
        public static final String POWER_ID = makeID("ToxinPower");

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = false;

        public ToxinPower(AbstractCreature owner, int amount) {
            super(POWER_ID, "ToxinPower", TYPE, false, owner, amount);
        }

        public void atStartOfTurn()
        {
            addToBot(new LoseHPAction(this.owner, this.owner, this.amount, AbstractGameAction.AttackEffect.POISON));
        }

    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new LoseHPAction(this.owner, this.owner, this.amount, AbstractGameAction.AttackEffect.POISON));
    }

        public void updateDescription()
        {
            this.description = this.DESCRIPTIONS[0] + this.amount + this.DESCRIPTIONS[1];
        }

        public AbstractPower makeCopy() {
            return new ToxinPower(this.owner, this.amount);
        }

        public int getHealthBarAmount() {
            return this.amount;
        }

        public Color getColor() {
            return new Color(0, 0 ,0 , 1);
        }
    }
