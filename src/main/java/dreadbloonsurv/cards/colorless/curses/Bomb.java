package dreadbloonsurv.cards.colorless.curses;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.cardvars.CardTags;
import dreadbloonsurv.powers.bloons.BasicBloonPower;

import static dreadbloonsurv.ModFile.makeID;

public class Bomb extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Bomb() {
        super(ID, 0, CardType.ATTACK, CardRarity.CURSE, CardTarget.SELF, CardColor.CURSE, "Bomb_CardArt");
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.POWER_DREADMOD);
        AutoplayField.autoplay.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(p, new DamageInfo(p, 10, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new DrawCardAction(1));
    }

    @Override
    public void upp() {
    }
}