package dreadbloonsurv.cards.colorless.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.AbstractPowerCostCard;
import dreadbloonsurv.powers.bloons.MustHitBloonPower;

public class RockBloon extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public RockBloon() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, CardColor.COLORLESS, "RockBloon_CardArt");
        baseDamage = 12;
        baseDelay = 3;
        baseSecondMagic = 0;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        this.exhaust = true;
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        addToBot((AbstractGameAction)new ApplyPowerAction(m, p, new MustHitBloonPower(m, delay, damage, baseSecondMagic,  "Rock Bloon", false), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp()
    {
        upgradeDamage(4);
        upgradeSecondMagic(1);
    }
}