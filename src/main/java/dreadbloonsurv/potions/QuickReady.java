package dreadbloonsurv.potions;

import basemod.AutoAdd;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dreadbloonsurv.ModFile;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.adp;
import static dreadbloonsurv.util.Wiz.applyToSelf;

@AutoAdd.Ignore
public class QuickReady extends AbstractEasyPotion {
    public static String ID = makeID("DemoPotion");

    public QuickReady() {
        super(ID, PotionRarity.COMMON, PotionSize.ANVIL, new Color(0.2f, 0.4f, 0.9f, 1f), new Color(0.6f, 0.8f, 1.0f, 1f), null, ModFile.Enums.DREADBLOON, ModFile.DREADcharacterColor);
    }

    public int getPotency(int ascensionlevel) {
        return 10;
    }

    public void use(AbstractCreature creature) {
        applyToSelf(new StrengthPower(adp(), potency));
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1];
    }

    /*public void addAdditionalTips() {
        tips.add(new PowerTip(BaseMod.getKeywordTitle(makeID("todo")), BaseMod.getKeywordDescription(makeID("todo"))));
    }*/
}