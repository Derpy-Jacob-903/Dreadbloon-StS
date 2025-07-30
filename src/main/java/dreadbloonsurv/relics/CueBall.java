package dreadbloonsurv.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.CharacterFile;
import dreadbloonsurv.cards.colorless.bloons.RedBloon;
import dreadbloonsurv.cards.cardvars.CardTags;

import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.Wiz.makeInHand;

public class CueBall extends AbstractEasyRelic {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    public CueBall() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, ModFile.Enums.DREADBLOON_COLOR);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.hasTag(CardTags.LARGE_DREADMOD)) {
            this.flash();
            makeInHand(new RedBloon());
        }
    }
}
