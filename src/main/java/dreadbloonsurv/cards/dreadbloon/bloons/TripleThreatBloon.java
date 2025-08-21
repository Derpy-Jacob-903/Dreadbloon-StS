package dreadbloonsurv.cards.dreadbloon.bloons;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.orbs.DoubleBloon;
import dreadbloonsurv.orbs.TripleBloon;

public class TripleThreatBloon extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public TripleThreatBloon() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, "TripleThreatBloon_CardArt");
        baseDamage = 16;
        baseDelay = delay = delay = 2;
        baseMagicNumber = magicNumber = 12;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    //TODO
    public void use(AbstractPlayer p, AbstractMonster m) {
        chan(new TripleBloon(damage, delay, magicNumber, 0, bloonName, Color.GREEN));
    }

    @Override
    public void upp()
    {
        upgradeDamage(4);
    }
}