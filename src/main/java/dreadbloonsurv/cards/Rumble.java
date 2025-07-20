package dreadbloonsurv.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static dreadbloonsurv.ModFile.makeID;

public class Rumble extends AbstractPowerCostCard {
    public final static String ID = makeID("Rumble");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Rumble() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -1), -1));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}