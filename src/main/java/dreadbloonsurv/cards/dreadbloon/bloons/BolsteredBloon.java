package dreadbloonsurv.cards.dreadbloon.bloons;

import basemod.AutoAdd;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.orbs.BasicBloon;

@AutoAdd.Ignore
public class BolsteredBloon  extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BolsteredBloon() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, ModFile.Enums.DREAD_COLOR, "BolsteredBloon_CardArt");
        baseDamage = 5;
        baseDelay = delay = 2;
        bloonName = "Ceramic Bloon";
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ADVANCED_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        chan(new BasicBloon(damage, delay, 0, secondMagic, bloonName, Color.GRAY));
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}