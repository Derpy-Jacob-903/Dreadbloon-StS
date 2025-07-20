package dreadbloonsurv.relics;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.CharacterFile;
import dreadbloonsurv.cards.bloons.RedBloon;
import dreadbloonsurv.cards.cardvars.CardTags;

import static dreadbloonsurv.ModFile.makeID;

public class CueBall extends AbstractEasyRelic {
    public static final String ID = makeID("CueBall");

    public CueBall() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, CharacterFile.Enums.DREADBLOON_COLOR);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.hasTag(CardTags.LARGE)) {
            RedBloon balls = new RedBloon();
            balls.onPlayCard(balls, m);
        }
    }
}
