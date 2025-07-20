package dreadbloonsurv.cards.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.powers.bloons.BasicBloonPower;
import dreadbloonsurv.powers.bloons.MustHitBloonPower;

import static dreadbloonsurv.ModFile.makeID;

public class RockBloon extends AbstractEasyCard {
    public final static String ID = makeID("RockBloon");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public RockBloon() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, "YellowBloon_CardArt");
        baseDamage = 12;
        baseMagicNumber = 2;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        addToBot((AbstractGameAction)new ApplyPowerAction(m, p, new MustHitBloonPower(m, 2, damage, magicNumber,  "Rock Bloon", false), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp()
    {
        upgradeDamage(14);
        upgradeBlock(5);
    }
}