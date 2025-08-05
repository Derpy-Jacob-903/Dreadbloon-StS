package dreadbloonsurv.cards.scrapborn;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;

public class Strike extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff attack, enemy, basic, 6, 3,  , , , 

    public Strike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, ModFile.Enums.SCRAP_COLOR);
        baseDamage = 6;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}