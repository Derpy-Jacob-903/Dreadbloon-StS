package dreadbloonsurv.cards.dreadbloon.abilities;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractPowerCostCard;
import dreadbloonsurv.cards.cardvars.CardTags;
import dreadbloonsurv.cards.colorless.bloons.RockBloon;
import dreadbloonsurv.orbs.BasicBloon;
import dreadbloonsurv.orbs.MustHitBloon;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.atb;
import static dreadbloonsurv.util.Wiz.makeInHand;

public class LetUsRock extends AbstractPowerCostCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public LetUsRock() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "RapidShot_CardArt");
        baseDamage = 9;
        baseDelay = delay = delay = 3;
        baseSecondMagic = 2;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ABILITY_DREADMOD);
        this.setCardBack(cardSubType.ABILITY);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new ChannelAction(new MustHitBloon(9, 3, 0, 0, "Rock Bloon", Color.ORANGE)));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}