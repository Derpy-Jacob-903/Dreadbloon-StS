package dreadbloonsurv.cards.cardvars;

import com.megacrit.cardcrawl.cards.AbstractCard;
import dreadbloonsurv.cards.AbstractEasyCard;

import static dreadbloonsurv.ModFile.makeID;

public class BloonDelay extends AbstractEasyDynamicVariable {

    @Override
    public String key() {
        return makeID("BD");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).isDelayModified;
        }
        return false;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractEasyCard) {
            ((AbstractEasyCard) card).isDelayModified = v;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).delay;
        }
        return -1;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).baseDelay;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).upgradedSecondDamage;
        }
        return false;
    }
}