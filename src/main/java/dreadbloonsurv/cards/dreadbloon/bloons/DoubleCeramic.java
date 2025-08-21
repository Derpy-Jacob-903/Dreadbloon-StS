package dreadbloonsurv.cards.dreadbloon.bloons;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.orbs.DoubleBloon;

public class DoubleCeramic extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DoubleCeramic() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, ModFile.Enums.DREAD_COLOR, "DoubleCeramicBloon_CardArt");
        baseDamage = 22;
        baseDelay = delay = delay = 2;
        bloonName = "Double Ceramic Bloon";
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ADVANCED_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        chan(new DoubleBloon(damage, delay, 0, 0, bloonName, Color.BROWN));
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}