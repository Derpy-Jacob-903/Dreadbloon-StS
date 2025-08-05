package dreadbloonsurv.cards.dreadbloon.abilities;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractPowerCostCard;
import dreadbloonsurv.cards.cardvars.CardTags;
import dreadbloonsurv.cards.colorless.bloons.RockBloon;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.makeInHand;

public class LetUsRock extends AbstractPowerCostCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public LetUsRock() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "RapidShot_CardArt");
        baseDamage = 6;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ABILITY_DREADMOD);
        this.setCardBack(cardSubType.ABILITY);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        RockBloon r = new RockBloon();
        r.baseSecondMagic += 2;
        r.costForTurn = 0;
        makeInHand(r);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}