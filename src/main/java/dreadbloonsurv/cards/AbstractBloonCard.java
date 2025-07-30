package dreadbloonsurv.cards;

import basemod.AutoAdd;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.CharacterFile;
import dreadbloonsurv.powers.BloontoniumPower;
import dreadbloonsurv.powers.bloons.BasicBloonPower;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.ModFile.modID;

@AutoAdd.Ignore
public abstract class AbstractBloonCard extends AbstractEasyCard {
    public final static String ID = makeID("AbstractBloonCard");
    //public //Type of AbstactBloonPower?
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AbstractBloonCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, ModFile.Enums.DREADBLOON_COLOR, cardID.replace(modID + ":", ""));
    }

    public AbstractBloonCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final String cardArt) {
        this(cardID, cost, type, rarity, target, ModFile.Enums.DREADBLOON_COLOR, cardArt);
    }

    public AbstractBloonCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        this(cardID, cost, type, rarity, target, color, cardID.replace(modID + ":", ""));
    }

    public AbstractBloonCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, final AbstractPower AltCostPower) {
        this(cardID, cost, type, rarity, target, color, cardID.replace(modID + ":", ""));
    }

    public AbstractBloonCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, final String cardArt, final AbstractPower AltCostPower) {
        this(cardID, cost, type, rarity, target, color, cardID.replace(modID + ":", ""));
    }

    public AbstractBloonCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, final String cardArt) {
        super(cardID, cost, type, rarity, target, color, myGetCardTextureString(cardArt, type));
    }

    @Override
    public void upp()
    {
        upgradeDamage(6 * baseDelay);
    }

    public void preBloon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
    {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
    {
        //i want to change new `BasicBloonPower` without having to write all this out
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractMonster, abstractPlayer, new BasicBloonPower(abstractMonster, delay, damage, secondMagic, secondMagic, bloonName), 1, true, AbstractGameAction.AttackEffect.NONE));
        bloonton();
    }

    public void postBloon(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
    {

    }
}