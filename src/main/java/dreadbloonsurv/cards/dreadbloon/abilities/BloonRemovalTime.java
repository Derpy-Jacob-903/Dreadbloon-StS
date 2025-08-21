package dreadbloonsurv.cards.dreadbloon.abilities;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractPowerCostCard;
import dreadbloonsurv.cards.colorless.Avalanche;
import dreadbloonsurv.cards.colorless.bloons.RockBloon;

import static dreadbloonsurv.util.Wiz.makeInHand;
import static dreadbloonsurv.util.Wiz.shuffleIn;

@AutoAdd.Ignore
public class BloonRemovalTime extends AbstractPowerCostCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public BloonRemovalTime() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, "RapidShot_CardArt");
        baseBlock = 6;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ABILITY_DREADMOD);
        this.setCardBack(cardSubType.ABILITY);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        shuffleIn(new Avalanche());
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
