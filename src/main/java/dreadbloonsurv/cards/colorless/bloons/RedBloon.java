package dreadbloonsurv.cards.colorless.bloons;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.cardvars.CardTags;
import dreadbloonsurv.powers.bloons.BasicBloonPower;

import static dreadbloonsurv.ModFile.makeID;

public class RedBloon extends AbstractEasyCard {
    public final static String ID = makeID("RedBloon");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public RedBloon() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS, "RedBloon_CardArt");
        baseDamage = 12;
        baseDelay = 2;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC_DREADMOD);
        PersistFields.setBaseValue(this, 2);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new BasicBloonPower(m, 2, damage, "Red Bloon"), 1, true, AbstractGameAction.AttackEffect.NONE));
        bloonton();
    }

    @Override
    public void upp() {
        upgradeDelay(-1);
    }
}