package dreadbloonsurv.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

public abstract class LambdaPower extends AbstractEasyPower {
    public LambdaPower(String ID, String name, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount)
    {
        super(ID, name, powerType, isTurnBased, owner, amount);
    }

    public abstract void updateDescription();
}
