package dreadbloonsurv.cards.dreadbloon.spells;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.deprecated.BaseBloon;

import static dreadbloonsurv.util.Wiz.makeInHand;

public class BloonEmbiggen extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BloonEmbiggen() {
        super(ID,1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY, ModFile.Enums.DREAD_COLOR, "BloonEmbiggen_CardArt");
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
        exhaust = true;
        this.setCardBack(cardSubType.POWER);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            for (AbstractPower power : mo.powers) {
                if (power instanceof BaseBloon) {
                    ((BaseBloon) power).amount2 += magicNumber;
                }
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}