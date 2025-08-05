package dreadbloonsurv.cards.dreadbloon.abilities;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dreadbloonsurv.cards.AbstractPowerCostCard;
import dreadbloonsurv.cards.colorless.bloons.RockBloon;

import static dreadbloonsurv.util.Wiz.makeInHand;

public class RockPopAndAwe extends AbstractPowerCostCard implements StartupCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    public boolean oddTurn = false;
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public RockPopAndAwe() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, "RapidShot_CardArt");
        baseDamage = 6;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ABILITY_DREADMOD);
        this.setCardBack(cardSubType.ABILITY);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        RockBloon r = new RockBloon();
        r.costForTurn = 0;
        makeInHand(r);
        if (this.oddTurn) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage)));
        }
        else {
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -1), -1));
        }
    }

    public void atTurnStart() {
        this.oddTurn = !this.oddTurn;
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        this.oddTurn = false;
        return false;
    }
}