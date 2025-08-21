package dreadbloonsurv.cards.dreadbloon.bloons;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.orbs.BasicBloon;

public class LeadZeppelin extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public LeadZeppelin() {
        super(ID, 4, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, ModFile.Enums.DREAD_COLOR, "LeadZeppelin_CardArt");
        baseDamage = 75;
        baseSecondMagic = secondMagic = 4;
        baseDelay = delay = delay = 5;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        chan(new BasicBloon(damage, delay, 0, secondMagic, bloonName, Color.GRAY));
    }

    @Override
    public void upp()
    {
        upgradeDamage(4);
        upgradeSecondMagic(4);
    }
}