package dreadbloonsurv.cards.colorless.curses;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.BloontoniumPower;

public class BloontoniumLeak extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BloontoniumLeak() {
        super(ID, -2, CardType.CURSE, CardRarity.CURSE, CardTarget.SELF, CardColor.CURSE, "BloontoniumLeak_CardArt");
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.SPELL_DREADMOD);
        AutoplayField.autoplay.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReducePowerAction(p, p, BloontoniumPower.POWER_ID, 999));
    }

    @Override
    public void upp() {
    }
}