package dreadbloonsurv.cards.dreadbloon.powers;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.atb;

@AutoAdd.Ignore
public class SupplyDrop extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SupplyDrop() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "SupplyDrop_CardArt");
        baseMagicNumber = magicNumber = 2;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.POWER_DREADMOD);
        this.setCardBack(cardSubType.POWER);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}