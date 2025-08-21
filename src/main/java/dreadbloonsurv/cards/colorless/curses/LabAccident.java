package dreadbloonsurv.cards.colorless.curses;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;

public class LabAccident extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public LabAccident() {
        super(ID, -2, CardType.CURSE, CardRarity.CURSE, CardTarget.ALL_ENEMY, CardColor.CURSE, "BloontoniumLeak_CardArt");
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.SPELL_DREADMOD);
        AutoplayField.autoplay.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upp() {
    }
}