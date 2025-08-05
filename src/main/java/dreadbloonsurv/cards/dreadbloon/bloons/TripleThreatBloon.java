package dreadbloonsurv.cards.dreadbloon.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.TripleThreatBloonPower;

import static dreadbloonsurv.ModFile.makeID;

public class TripleThreatBloon extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public TripleThreatBloon() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, "YellowBloon_CardArt");
        baseDamage = 16;
        baseDelay = delay = 2;
        baseMagicNumber = magicNumber = 12;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        addToBot(new ApplyPowerAction(m, p, new TripleThreatBloonPower(m, delay, damage,  magicNumber, 0, false, "Triple Threat Bloon"), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp()
    {
        upgradeDamage(4);
    }
}