package dreadbloonsurv.cards.dreadbloon.bloons;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.orbs.BasicBloon;
import dreadbloonsurv.powers.BloontoniumPower;

public class BloontoniumGasBloon extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BloontoniumGasBloon() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF, ModFile.Enums.DREAD_COLOR, "BloontoniumGasBloon_CardArt");
        baseDamage = 25;
        baseDelay = delay = 4;
        bloonName = "Bloontonium Gas Bloon";
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ADVANCED_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        chan(new BasicBloon(damage, delay, 0, secondMagic, bloonName, Color.PURPLE) {
            public void onDamaged(AbstractCreature attacker) {
                addToBot(new ApplyPowerAction(p, p, new BloontoniumPower(p, 1)));
            }
        });
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}