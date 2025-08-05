package dreadbloonsurv.cards.dreadbloon.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.BaseBloon;
import dreadbloonsurv.powers.bloons.DoubleBloonPower;
import dreadbloonsurv.powers.bloons.TripleThreatBloonPower;

public class ImprovedFortification extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ImprovedFortification() {
        super(ID,1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, ModFile.Enums.DREAD_COLOR, "ImprovedFortification_CardArt");
        baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
        exhaust = true;
        this.setCardBack(cardSubType.POWER);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        BaseBloon best = null;
        int bestDelay = 2;
        int bestMult = 1;

        for (AbstractPower pp : m.powers) {
            if (!(pp instanceof BaseBloon)) continue;

            BaseBloon bb = (BaseBloon) pp;
            int delay = bb.immobile ? 2 : bb.amount;
            //if (delay == 0) continue; // Avoid 0-delay bloons if desired

            int mult = 1;
            if (bb instanceof TripleThreatBloonPower) mult = 3;
            else if (bb instanceof DoubleBloonPower) mult = 2;

            // Predicted threat value after applying the upgrade
            double predictedThreat = (((bb.amount2 + magicNumber) * mult +
                    bb.amountShield) / Math.max(0.5, delay + 1 - (double) bb.amountArmor / 2)) - (((bb.amount2) * mult +
                    bb.amountShield) / Math.max(0.5, delay - (double) bb.amountArmor / 2));

            double currentBestThreat = ((best == null) ? -1 :
                    (((best.amount2 + magicNumber) * bestMult +
                            best.amountShield) / Math.max(0.5, bestDelay + 1 - (double) best.amountArmor / 2)) - (((best.amount2) * bestMult +
                    best.amountShield) / Math.max(0.5, bestDelay - (double) best.amountArmor / 2)));

            if (best == null || predictedThreat > currentBestThreat) {
                best = bb;
                bestDelay = delay;
                bestMult = mult;
            }
        }

        // Apply effect (e.g., health + magicNumber, delay +1)
        if (best != null) {
            best.amount2 += magicNumber;
            best.amount += 1;
        }
    }

    public void baseUse(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        BaseBloon E = null;
        int Edelay = 2;
        int EhealthMult = 1;
        for (AbstractPower pp : m.powers) {
            if (pp instanceof BaseBloon)
            {
                if (E == null) { E = (BaseBloon) pp; return;}
                BaseBloon bb = (BaseBloon) pp;
                int delay = bb.immobile ? 2 : bb.amount;
                int healthMult = bb instanceof DoubleBloonPower ? 2 : 1;
                healthMult = bb instanceof TripleThreatBloonPower ? 3 : healthMult;
                if ((bb.amount2 * healthMult + bb.amountShield / Math.max(0.75, delay - (double)bb.amountArmor / 2) > (E.amount2 * EhealthMult + E.amountShield / Math.max(0.75, Edelay - (double)E.amountArmor / 2)))) //Lead has 2 armor but +1 delay and same health and cost as yellow
                {
                    E = bb;
                    Edelay = delay;
                    EhealthMult = healthMult;
                }
            }
        }
        if (E instanceof BaseBloon) {E.amount2 += magicNumber;}
    }

    @Override
    public void upp() {
        upgradeMagicNumber(5);
    }
}