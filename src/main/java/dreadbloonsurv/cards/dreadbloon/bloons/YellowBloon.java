package dreadbloonsurv.cards.dreadbloon.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.BasicBloonPower;

import static dreadbloonsurv.ModFile.makeID;

public class YellowBloon extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public YellowBloon() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, "YellowBloon_CardArt");
        baseDamage = 13;
        baseDelay = delay = 1;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        addToBot((AbstractGameAction)new ApplyPowerAction(m, p, new BasicBloonPower(m, delay, damage, "Yellow Bloon"), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}