package dreadbloonsurv.cards.colorless;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;

public class Avalanche extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Avalanche() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS, "Avalanche_CardArt");
        this.setCardBack(cardSubType.POWER);
        damage = 0;
        this.retain = true;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    public void atTurnStartPreDraw() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, AbstractDungeon.actNum + damage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new PutOnDeckAction(p, p, 1, true));
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }
}