package dreadbloonsurv.cards.bloons;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.BasicBloonPower;

import static dreadbloonsurv.ModFile.makeID;

public class VolatileBloon extends AbstractEasyCard {
    public final static String ID = makeID("VolatileBloon");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public VolatileBloon() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS, "VolatileBloon_CardArt");
        baseDamage = 12;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON); // here :3
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC);
        PersistFields.setBaseValue(this, 2);
        ExhaustiveVariable.setBaseValue(this, 2);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new BasicBloonPower(m, 2, damage, "Volatile Bloon"), 1, true, AbstractGameAction.AttackEffect.NONE));
        bloonton();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}