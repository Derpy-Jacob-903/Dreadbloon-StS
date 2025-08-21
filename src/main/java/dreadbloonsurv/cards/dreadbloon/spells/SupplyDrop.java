package dreadbloonsurv.cards.dreadbloon.spells;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;

import static dreadbloonsurv.util.Wiz.atb;

@AutoAdd.Ignore
public class SupplyDrop extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SupplyDrop() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "SupplyDrop_CardArt");
        baseMagicNumber = magicNumber = 2;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.SPELL_DREADMOD);
        this.setCardBack(cardSubType.POWER);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new DrawCardAction(magicNumber));
        atb(new GainEnergyAction(1));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}