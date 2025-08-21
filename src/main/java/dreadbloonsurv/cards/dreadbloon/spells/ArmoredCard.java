package dreadbloonsurv.cards.dreadbloon.spells;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.colorless.BombStatus;
import dreadbloonsurv.powers.powers.ArmoredPower;

import static dreadbloonsurv.util.Wiz.*;

public class ArmoredCard extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ArmoredCard() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, "Armored_Cardart");
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.SPELL_DREADMOD);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
        this.setCardBack(cardSubType.POWER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new ArmoredPower(p, 2 + AbstractDungeon.actNum)));
        addToBot(new MakeTempCardInDiscardAction(new BombStatus(), 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}