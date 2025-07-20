package dreadbloonsurv.powers;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.util.ProAudio;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.playAudio;

public class BloontoniumPower extends AbstractEasyPower {
        public static final String POWER_ID = makeID("BloontoniumPower");

        private static final PowerType TYPE = PowerType.BUFF;

        private static final boolean TURN_BASED = false;

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount > 6)
            this.amount = 6;
        if (this.amount <= 0)
            this.amount = 6;
    }

        public BloontoniumPower(AbstractCreature owner, int amount) {
            super(POWER_ID, "Bloontonium", TYPE, false, owner, amount);
            this.canGoNegative = false;
        }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof BloontoniumPower){
            playAudio(ProAudio.BloontoniumGained);}
    }

    public void updateDescription()
        {
            this.description = this.DESCRIPTIONS[0];
        }

        public AbstractPower makeCopy() {
            return new BloontoniumPower(this.owner, this.amount);
        }
    }
