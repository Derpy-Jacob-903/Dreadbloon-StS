package dreadbloonsurv.cards.colorless;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.powers.ArmoredPower;

public class BombStatus extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BombStatus() {
        super(ID, 0, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS, "Bomb_CardArt");
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.POWER_DREADMOD);
        AutoplayField.autoplay.set(this, true);
        magicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveSpecificPowerAction(p, p, ArmoredPower.POWER_ID));
        addToBot(new DamageAction(p, new DamageInfo(p, 4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}