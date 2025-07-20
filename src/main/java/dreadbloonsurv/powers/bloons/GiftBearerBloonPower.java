package dreadbloonsurv.powers.bloons;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.att;

public class GiftBearerBloonPower extends BasicBloonPower {
        public static final String POWER_ID = makeID("GiftBearerBloonPower");

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public AbstractCard GIFT = new Shiv();

        public GiftBearerBloonPower(AbstractCreature owner, int delay, int health, AbstractCard gift) {
            super(POWER_ID, owner, delay, health, 0, 0, "Nested Bloon");
        }

    public GiftBearerBloonPower(AbstractCreature owner, int delay, int health, String name, AbstractCard gift) {
        super(POWER_ID, owner, delay, health, 0, 0, name);
    }
    public GiftBearerBloonPower(AbstractCreature owner, int delay, int health, int armor, String name, boolean immobile, AbstractCard gift) {
        super(POWER_ID, owner, delay, health, 0, armor, immobile, name);
    }

    public AbstractPower makeCopy() {
            return new GiftBearerBloonPower(this.owner, this.amount, this.amount2, this.GIFT);
        }
}
