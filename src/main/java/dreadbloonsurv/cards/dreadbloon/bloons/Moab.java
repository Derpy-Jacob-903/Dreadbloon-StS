package dreadbloonsurv.cards.dreadbloon.bloons;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.CharacterFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.BasicBloonPower;

import static dreadbloonsurv.ModFile.makeID;

public class Moab extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Moab() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, ModFile.Enums.DREADBLOON_COLOR, "MOAB_CardArt");
        baseDamage = 50;
        baseDelay = delay = 4;
        baseMagicNumber = magicNumber = 1;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.LARGE_DREADMOD);
        PersistFields.setBaseValue(this, 2);
        ExhaustiveVariable.setBaseValue(this, 2);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction(m, p, new BasicBloonPower(m, 4, damage, "MOAB"), 1, true, AbstractGameAction.AttackEffect.NONE));
        bloonton();
    }

    @Override
    public void upp()
    {
        upgradeDamage(10);
    }
}