package dreadbloonsurv.cards.scrapborn;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.AbstractPowerCostCard;

public class ArrowShot extends AbstractPowerCostCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.SCRAP_COLOR);
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ArrowShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, ModFile.Enums.SCRAP_COLOR);
        baseDamage = 4;
    }

    @Override
    public boolean canPlay(AbstractCard card) {
        int resource = -1;
        if (card == this)
        {
            if (AbstractDungeon.player.hasPower(PowerCostId)) {
                if (AbstractDungeon.player.getPower(PowerCostId).amount > 0) {
                    resource = AbstractDungeon.player.getPower(PowerCostId).amount;
                }
            }
        }
        return resource > 0;
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        int resource = -1;
        if (AbstractDungeon.player.hasPower(PowerCostId)) {
            if (AbstractDungeon.player.getPower(PowerCostId).amount > 0) {
                resource = AbstractDungeon.player.getPower(PowerCostId).amount;
            }
        }
        if (resource > costToSpend) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, PowerCostId, costToSpend));
            costToSpend = 0;
        } else if (resource > 0) {
            costToSpend = 999;
        }
        return costToSpend;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    protected void upgradeName() {
        super.upgradeName();
    }

    @Override
    public void upp() {
        tags.add(CardTags.STRIKE);
        upgradeDamage(3);
    }
}