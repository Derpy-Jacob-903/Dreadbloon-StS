package dreadbloonsurv.cards.dreadbloon.bloons;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.orbs.ArbitrOrb;
import dreadbloonsurv.orbs.BasicBloon;
import dreadbloonsurv.powers.bloons.deprecated.ARBITRPower;

public class ARBITRCard extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ARBITRCard() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, ModFile.Enums.DREAD_COLOR, "ARBITR_CardArt");
        baseBlock = 25;
        baseDelay = delay = delay = 999;
        bloonName = "ARBITR";
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.ADVANCED_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        bloonton();
        chan(new ArbitrOrb(block, -1, 0, secondMagic, bloonName, Color.SKY));
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}