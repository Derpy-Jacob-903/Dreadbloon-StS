package dreadbloonsurv.cards.dreadbloon.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.cardvars.CardTags;
import dreadbloonsurv.powers.bloons.BasicBloonPower;

public class CleverGreen extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CleverGreen() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, "CleverGreenBloon_CardArt");
        baseDamage = 7;
        baseDelay = delay = 1;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        addToBot(new ApplyPowerAction(m, p, new BasicBloonPower(m, delay, damage, "Clever Green Bloon"), 1, true, AbstractGameAction.AttackEffect.NONE));
        if (!p.discardPile.isEmpty() || !p.discardPile.getTopCard().hasTag(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD))
        { addToBot(new DrawCardAction(1)); }
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}