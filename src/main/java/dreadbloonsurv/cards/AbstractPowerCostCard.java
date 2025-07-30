package dreadbloonsurv.cards;

import basemod.AutoAdd;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.CharacterFile;
import dreadbloonsurv.powers.BloontoniumPower;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.ModFile.modID;

@AutoAdd.Ignore
public abstract class AbstractPowerCostCard extends AbstractEasyCard implements AlternateCardCostModifier {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    public String PowerCostId;
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public AbstractPowerCostCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, ModFile.Enums.DREADBLOON_COLOR, cardID.replace(modID + ":", ""));
        PowerCostId = BloontoniumPower.POWER_ID;
    }

    public AbstractPowerCostCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final String cardArt) {
        this(cardID, cost, type, rarity, target, ModFile.Enums.DREADBLOON_COLOR, cardArt);
        PowerCostId = BloontoniumPower.POWER_ID;
    }

    public AbstractPowerCostCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        this(cardID, cost, type, rarity, target, color, cardID.replace(modID + ":", ""));
        PowerCostId = BloontoniumPower.POWER_ID;
    }

    public AbstractPowerCostCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, final AbstractPower AltCostPower) {
        this(cardID, cost, type, rarity, target, color, cardID.replace(modID + ":", ""));
        PowerCostId = AltCostPower.ID;
    }

    public AbstractPowerCostCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, final String cardArt, final AbstractPower AltCostPower) {
        this(cardID, cost, type, rarity, target, color, cardID.replace(modID + ":", ""));
        PowerCostId = AltCostPower.ID;
    }

    public AbstractPowerCostCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, final String cardArt) {
        super(cardID, cost, type, rarity, target, color, myGetCardTextureString(cardArt, type));
        setOrbTexture("dreadbloonsurvResources/images/512/bloontonium.png", "dreadbloonsurvResources/images/1024/bloontonium.png");
        PowerCostId = BloontoniumPower.POWER_ID;
    }

    public void upp() {}

    @Override
    public int getAlternateResource(AbstractCard abstractCard) {
        if (abstractCard == this)
        {
            if (AbstractDungeon.player.hasPower(((AbstractPowerCostCard)abstractCard).PowerCostId)) {
                return AbstractDungeon.player.getPower(((AbstractPowerCostCard)abstractCard).PowerCostId).amount;
            }
            else return 0;
        }
        else return 0;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }

    @Override
    public boolean canSplitCost(AbstractCard card) {
        return false;
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        int resource = -1;
        if (AbstractDungeon.player.hasPower(PowerCostId)) {
            if (AbstractDungeon.player.getPower(PowerCostId).amount > 0) {
                resource = AbstractDungeon.player.getPower(PowerCostId).amount;
            }
        }
        if (resource > costToSpend) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, PowerCostId, costToSpend));
            costToSpend = 0;
        } else if (resource > 0) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, PowerCostId, resource));
            costToSpend -= resource;
        }
        return costToSpend;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}
}