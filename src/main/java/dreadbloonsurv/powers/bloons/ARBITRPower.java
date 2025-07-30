package dreadbloonsurv.powers.bloons;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.cards.cardvars.CardTags;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.cards.AbstractEasyCard.autoID;

public class ARBITRPower extends BasicBloonPower {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

    private static final PowerType TYPE = PowerType.DEBUFF;

    private static final boolean TURN_BASED = true;

    public ARBITRPower(AbstractCreature owner, int delay, int health) {
        super(POWER_ID, owner, delay, health, 0, 0, true, "ARBITR");
    }

    public ARBITRPower(AbstractCreature owner, int delay, int health, int shield, int armor) {
        super(POWER_ID, owner, delay, health, shield, armor, true, "ARBITR");
    }

    public ARBITRPower(AbstractCreature owner, int delay, int health, int shield, int armor, boolean immobile, String name) {
        super(POWER_ID, owner, delay, health, shield, armor, immobile, name);
    }

    public AbstractPower makeCopy() {
        return new ARBITRPower(this.owner, this.amount, this.amount2, this.amountShield, this.amountArmor);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (owner.hasPower(ARBITRPower.POWER_ID)) { return damage; }
        if (type == DamageInfo.DamageType.NORMAL)
        {
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ARBITRVigorPower(this.owner, (int)damage), (int)damage));
        }
        return 0;
    }
}
