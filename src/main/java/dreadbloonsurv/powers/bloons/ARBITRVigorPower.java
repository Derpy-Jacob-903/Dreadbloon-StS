package dreadbloonsurv.powers.bloons;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.orbs.ArbitrOrb;
import dreadbloonsurv.orbs.MustHitBloon;
import dreadbloonsurv.powers.bloons.deprecated.ARBITRPower;

import static dreadbloonsurv.ModFile.makeID;

public class ARBITRVigorPower extends AbstractPower {
    public static final String POWER_ID = makeID("Vigor");
    private static final PowerStrings powerStrings;

    public ARBITRVigorPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("vigor");
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        Boolean hasArbitrOrb = false;
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (orb instanceof ArbitrOrb) {
                hasArbitrOrb = true;
                break;
            }
        }
        if (owner.hasPower(ARBITRPower.POWER_ID) || hasArbitrOrb) { return damage; }
        float vigorAmount = 0;
        if (type == DamageType.NORMAL)
        {
            this.flash();
            vigorAmount = (float)this.amount;
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        return damage + vigorAmount;
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Vigor");
    }
}
