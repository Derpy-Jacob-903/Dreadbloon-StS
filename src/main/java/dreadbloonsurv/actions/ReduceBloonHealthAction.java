package dreadbloonsurv.actions;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.powers.AbstractEasyPower;
import dreadbloonsurv.powers.bloons.deprecated.BaseBloon;

///
public class ReduceBloonHealthAction extends ReducePowerAction {
    private String powerID;
    private final AbstractPower powerInstance;

    public ReduceBloonHealthAction(AbstractCreature target, AbstractCreature source, AbstractPower power, int amount) {
        super(target, source, power, amount);
        this.powerInstance = power;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            AbstractEasyPower reduceMe = null;
            if (this.powerInstance != null) {
                reduceMe = (AbstractEasyPower) this.powerInstance;
            }
            if (reduceMe != null) { //help
                if (this.amount < reduceMe.amount2) {
                    reducePower2(this.amount, reduceMe);
                    reduceMe.updateDescription();
                    AbstractDungeon.onModifyPower();
                } else {
                    if (this.powerInstance instanceof BaseBloon) {
                        ((BaseBloon)this.powerInstance).onDestroyed();
                        ((BaseBloon)this.powerInstance).onPopped();
                    }
                    this.addToTop(new RemoveSpecificPowerAction(this.target, this.source, reduceMe));
                }
            }
        }
        this.tickDuration();
    }

    public void reducePower2(int reduceAmount, AbstractEasyPower reduceMe) {
        int reduceAmount2 = reduceAmount;
        reduceAmount2 -= reduceMe.amountArmor;

        if (!(reduceAmount2 > 0)) { return; }

        //Shield calc
        if (reduceMe.amountShield < reduceAmount2) {
            reduceAmount2 -= reduceMe.amountShield;
            reduceMe.amountShield = 0;
        } else {
            reduceMe.amountShield -= reduceAmount2;
            return;
        }

        if (reduceMe.amount2 - reduceAmount2 <= 0) {
            //reduceMe.fontScale = 8.0F;
            reduceMe.amount2 = 0;
        } else {
            //reduceMe.fontScale = 8.0F;
            reduceMe.amount2 -= reduceAmount2;
        }
    }
}

