package dreadbloonsurv.cards.dreadbloon.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.ARBITRPower;
import dreadbloonsurv.powers.bloons.BasicBloonPower;

public class ARBITRCard extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ARBITRCard() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, ModFile.Enums.DREAD_COLOR, "YellowBloon_CardArt");
        baseDamage = 25;
        baseDelay = 999;
        bloonName = "ARBITR";
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ADVANCED_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        addToBot(new ApplyPowerAction(m, p, new ARBITRPower(m, delay, damage,0, 0, true, bloonName), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}