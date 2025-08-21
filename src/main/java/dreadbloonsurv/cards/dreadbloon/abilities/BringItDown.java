package dreadbloonsurv.cards.dreadbloon.abilities;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractPowerCostCard;
import dreadbloonsurv.cards.colorless.bloons.RockBloon;
import dreadbloonsurv.cards.colorless.Avalanche;
import dreadbloonsurv.orbs.BasicBloon;
import dreadbloonsurv.orbs.MustHitBloon;

import static dreadbloonsurv.util.Wiz.*;

public class BringItDown extends AbstractPowerCostCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public BringItDown() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "RapidShot_CardArt");
        baseDamage = 9;
        baseDelay = delay = delay = 3;
        baseSecondMagic = 0;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ABILITY_DREADMOD);
        this.setCardBack(cardSubType.ABILITY);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        RockBloon r = new RockBloon();
        atb(new ChannelAction(new MustHitBloon(9, 3, 0, 0, "Rock Bloon", Color.ORANGE)));
        shuffleIn(new Avalanche());
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}