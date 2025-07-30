package dreadbloonsurv.cards.dreadbloon.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.CharacterFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.cardvars.CardTags;
import dreadbloonsurv.powers.bloons.BasicBloonPower;
import dreadbloonsurv.powers.bloons.DoubleBloonPower;

public class DoubleCeramic extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DoubleCeramic() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, ModFile.Enums.DREADBLOON_COLOR, "DoubleCeramicBloon_CardArt");
        baseDamage = 22;
        baseDelay = delay = 2;
        bloonName = "Double Ceramic Bloon";
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ADVANCED_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        addToBot((AbstractGameAction)new ApplyPowerAction(m, p, new DoubleBloonPower(m, delay, damage,0, 0, false, bloonName), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}