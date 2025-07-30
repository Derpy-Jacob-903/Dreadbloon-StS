package dreadbloonsurv.cards.dreadbloon.powers;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.util.modifedclasses.ImmunityStance;

import static dreadbloonsurv.util.Wiz.atb;

public class ImmunityCard extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ImmunityCard() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, "Armored_Cardart");
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.POWER_DREADMOD);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
        this.setCardBack(cardSubType.POWER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChangeStanceAction(ImmunityStance.STANCE_ID));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}