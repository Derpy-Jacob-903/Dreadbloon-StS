package dreadbloonsurv.cards.colorless.curses;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;

public class AvalancheToken extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public AvalancheToken() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS, "Avalanche_CardArt");
        this.setCardBack(cardSubType.POWER);
        this.retain = true;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    public void atTurnStartPreDraw() {
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, AbstractDungeon.actNum, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void upp() {
    }
}