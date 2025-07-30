package dreadbloonsurv.cards.colorless.curses;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;

public class AvalancheStatus extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public AvalancheStatus() {
        super(ID, 1, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS, "Avalanche_CardArt");
        this.setCardBack(cardSubType.POWER);
        this.retain = true;
    }

    public void atTurnStartPreDraw() {
        addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, AbstractDungeon.actNum , DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public void upp() {
    }
}