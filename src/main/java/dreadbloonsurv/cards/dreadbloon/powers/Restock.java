package dreadbloonsurv.cards.dreadbloon.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.atb;

public class Restock extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Restock() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, "Restock_CardArt");
        baseMagicNumber = magicNumber = 3;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.POWER_DREADMOD);
        this.setCardBack(cardSubType.POWER);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}