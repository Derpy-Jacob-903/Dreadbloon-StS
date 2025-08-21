package dreadbloonsurv.cards.scrapborn;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.AbstractPowerCostCard;

public class ConcussiveShell extends AbstractPowerCostCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.SCRAP_COLOR);
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ConcussiveShell() {
        super(ID, 3, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, ModFile.Enums.SCRAP_COLOR);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}