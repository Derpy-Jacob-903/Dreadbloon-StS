package dreadbloonsurv.cards.dreadbloon.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.cardvars.CardTags;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.makeInHand;
import static dreadbloonsurv.util.Wiz.returnTrulyRandomPrediCardInCombat;

public class BloonReinforcements extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public BloonReinforcements() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, "BloonReinforcements_Cardart");
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.POWER_DREADMOD);
        baseMagicNumber = magicNumber = 2;
        this.setCardBack(cardSubType.POWER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard eligibleCardsList = returnTrulyRandomPrediCardInCombat(c -> c.hasTag(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD)
                    && c.hasTag(dreadbloonsurv.cards.cardvars.CardTags.BASIC_DREADMOD));
            makeInHand(eligibleCardsList);
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}