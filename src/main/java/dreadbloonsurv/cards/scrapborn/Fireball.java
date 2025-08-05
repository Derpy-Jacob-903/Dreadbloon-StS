package dreadbloonsurv.cards.scrapborn;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractPowerCostCard;

public class Fireball extends AbstractPowerCostCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Fireball() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, ModFile.Enums.SCRAP_COLOR);
        baseDamage = 4;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    protected void upgradeName() {

        tags.add(CardTags.STRIKE);
        super.upgradeName();
    }

    @Override
    public void upp() {
        tags.add(CardTags.STRIKE);
        upgradeDamage(3);
    }
}