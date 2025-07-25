package dreadbloonsurv.cards.cardvars;

import com.megacrit.cardcrawl.cards.AbstractCard;
import dreadbloonsurv.cards.AbstractEasyCard;

import static dreadbloonsurv.ModFile.makeID;

public class SecondDamage extends AbstractEasyDynamicVariable {

    @Override
    public String key() {
        return makeID("sd");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).isSecondDamageModified;
        }
        return false;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractEasyCard) {
            ((AbstractEasyCard) card).isSecondDamageModified = v;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).secondDamage;
        }
        return -1;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractEasyCard) {
            return ((AbstractEasyCard) card).baseSecondDamage;
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