package dreadbloonsurv.cards.dreadbloon.abilities;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractPowerCostCard;
import dreadbloonsurv.cards.colorless.bloons.RockBloon;
import dreadbloonsurv.cards.colorless.Avalanche;

import static dreadbloonsurv.util.Wiz.*;

public class BringItDown extends AbstractPowerCostCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public BringItDown() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "RapidShot_CardArt");
        baseDamage = 6;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ABILITY_DREADMOD);
        this.setCardBack(cardSubType.ABILITY);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        RockBloon r = new RockBloon();
        shuffleIn(new Avalanche());
        r.costForTurn = 0;
        makeInHand(r);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}