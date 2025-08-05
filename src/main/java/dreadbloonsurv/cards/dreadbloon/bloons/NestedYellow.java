package dreadbloonsurv.cards.dreadbloon.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.BasicBloonPower;
import dreadbloonsurv.powers.bloons.NestedBloonPower;

import static dreadbloonsurv.ModFile.makeID;

public class NestedYellow extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public NestedYellow() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, "NestedYellow_CardArt");
        baseDamage = 13;
        baseDelay = 1;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        addToBot(new ApplyPowerAction(m, p, new NestedBloonPower(m, delay, damage, "Nested Yellow", new NestedBloonPower(m, delay, damage, "Nested Green", new NestedBloonPower(m, delay, damage, "Nested Blue", new BasicBloonPower(m, delay, damage, "Red Bloon")))), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}