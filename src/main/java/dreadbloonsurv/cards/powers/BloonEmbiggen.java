package dreadbloonsurv.cards.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import dreadbloonsurv.CharacterFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.BaseBloon;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.makeInHand;

public class BloonEmbiggen extends AbstractEasyCard {
    public final static String ID = makeID("BloonEmbiggen");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BloonEmbiggen() {
        super(ID,0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY, CharacterFile.Enums.DREADBLOON_COLOR, "BloonEmbiggen_CardArt");
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
        exhaust = true;
        this.setCardBack(cardSubType.POWER);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for (AbstractPower power : m.powers) {
            if (power instanceof BaseBloon)
            {
                ((BaseBloon) power).amount2 += magicNumber;
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}