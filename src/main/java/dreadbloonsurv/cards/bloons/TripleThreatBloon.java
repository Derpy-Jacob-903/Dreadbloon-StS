package dreadbloonsurv.cards.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.BasicBloonPower;
import dreadbloonsurv.powers.bloons.TripleThreatBloonPower;

import static dreadbloonsurv.ModFile.makeID;

public class TripleThreatBloon extends AbstractEasyCard {
    public final static String ID = makeID("TripleThreatBloon");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public TripleThreatBloon() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.SELF_AND_ENEMY, "YellowBloon_CardArt");
        baseDamage = 16;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        addToBot((AbstractGameAction)new ApplyPowerAction(m, p, new TripleThreatBloonPower(m, 2, damage,  12, 0, false, "Triple Threat Bloon"), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp()
    {
        upgradeDamage(4);
    }
}