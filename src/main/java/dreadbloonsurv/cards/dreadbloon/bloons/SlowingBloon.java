package dreadbloonsurv.cards.dreadbloon.bloons;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.orbs.BasicBloon;

public class SlowingBloon extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SlowingBloon() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, ModFile.Enums.DREAD_COLOR, "YellowBloon_CardArt");
        baseDamage = 12;
        baseDelay = delay = 3;
        baseMagicNumber = magicNumber = 0;
        bloonName = "Slowing Bloon";
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ADVANCED_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        chan(new BasicBloon(damage, delay, 0, secondMagic, bloonName, Color.YELLOW.cpy()));
        if (magicNumber > 0) { addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false))); }
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 2, false)));
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
        upgradeMagicNumber(1);
    }
}