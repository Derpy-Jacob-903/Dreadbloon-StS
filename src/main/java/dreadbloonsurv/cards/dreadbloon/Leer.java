package dreadbloonsurv.cards.dreadbloon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import dreadbloonsurv.cards.AbstractPowerCostCard;

import static dreadbloonsurv.ModFile.makeID;

public class Leer extends AbstractPowerCostCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Leer() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(FrailPower.POWER_ID))
        {
            addToBot(new ApplyPowerAction(m, p, new FrailPower(m, 1, false), 1));
        }
        else
        {
            addToBot(new ApplyPowerAction(m, p, new DexterityPower(m, -1), -1));
        }
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}