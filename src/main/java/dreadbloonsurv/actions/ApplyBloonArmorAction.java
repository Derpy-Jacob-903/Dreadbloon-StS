package dreadbloonsurv.actions;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.powers.AbstractEasyPower;

///
public class ApplyBloonArmorAction extends ReducePowerAction {
    private String powerID;
    private final AbstractPower powerInstance;

    public ApplyBloonArmorAction(AbstractCreature target, AbstractCreature source, AbstractPower power, int amount) {
        super(target, source, power, amount);
        this.powerInstance = power;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            AbstractEasyPower addToMe = null;
            if (this.powerInstance != null) {
                addToMe = (AbstractEasyPower) this.powerInstance;
            }
            if (addToMe != null) { //help
                addToMe.amountArmor = Math.max(0, addToMe.amountArmor + this.amount);
                addToMe.updateDescription();
                AbstractDungeon.onModifyPower();
            }
        }
        this.tickDuration();
    }
}

