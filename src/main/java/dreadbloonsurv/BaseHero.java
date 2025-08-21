package dreadbloonsurv;

import basemod.AutoAdd;
import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dreadbloonsurv.cards.dreadbloon.Defend;
import dreadbloonsurv.cards.dreadbloon.Strike;
import dreadbloonsurv.orbs.AbstractBloon;
import dreadbloonsurv.orbs.MustHitBloon;

import java.util.ArrayList;

import static dreadbloonsurv.ModFile.*;

@AutoAdd.Ignore
public abstract class BaseHero extends CustomPlayer {

    static final String ID = makeID("Hero");
    /*static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;*/

    public ArrayList<AbstractOrb> OrbUpkeep(ArrayList<AbstractOrb> orbs) {
        
        return orbs;
    }
    
    public int getOrbDelay(AbstractOrb orb) {
        if (orb instanceof AbstractBloon)
        {
            AbstractBloon bloon = (AbstractBloon)orb;
            return bloon.delay;
        } else if (orb instanceof EmptyOrbSlot) {
            return -2;
        }
        return -3; //any non bloon, non empty orb.
    }

    public BaseHero(String name, PlayerClass setClass, CustomEnergyOrb customEnergyOrb, SpriterAnimation spriterAnimation) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, makeCharacterPath("mainChar/orb/vfx.png"), null), new SpriterAnimation(makeCharacterPath("mainChar/static.scml")));
    }

    public int getMyThreat() {
        int i = 5;
        //int i3 = 32;
        if (getPower(StrengthPower.POWER_ID) != null) {
            i += getPower(StrengthPower.POWER_ID).amount;
            //i3 += getPower(StrengthPower.POWER_ID).amount;
        }
        int e = energy.energyMaster;
        return i * e;
    }

    @Override
    public void damage(DamageInfo info) {
        if (info.type != DamageInfo.DamageType.NORMAL) { super.damage(info); return; }
        AbstractBloon highestThreat = null;
        boolean mustHitOnly = false;
        // First pass: check if any MustHitBloon exists
        for (AbstractOrb orb : this.orbs) {
            if (orb instanceof MustHitBloon) {
                mustHitOnly = true;
                break;
            }
        }
        // Second pass: find the highest threat bloon (respecting must-hit if present)
        for (AbstractOrb orb : this.orbs) {
            if (!(orb instanceof AbstractBloon)) continue;
            if (mustHitOnly && !(orb instanceof MustHitBloon)) continue;
            AbstractBloon bloon = (AbstractBloon) orb;
            float bloonThreat = bloon.getMyThreat();
            float currentThreat = highestThreat == null ? this.getMyThreat() : highestThreat.getMyThreat();
            if (bloonThreat > currentThreat) {
                highestThreat = bloon;
            }
        }
        if (highestThreat != null) {
            highestThreat.damage(info);
        } else {
            super.damage(info);
        }
    }

    //IDK but needs orb slots
    /*@Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                75, 75, 8, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }*/

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            retVal.add(Strike.ID);
        }
        for (int i = 0; i < 5; i++) {
            retVal.add(Defend.ID);
        }
        return retVal;
    }

    private static final String[] orbTextures = {
            makeCharacterPath("mainChar/orb/layer1.png"),
            makeCharacterPath("mainChar/orb/layer2.png"),
            makeCharacterPath("mainChar/orb/layer3.png"),
            makeCharacterPath("mainChar/orb/layer4.png"),
            makeCharacterPath("mainChar/orb/layer4.png"),
            makeCharacterPath("mainChar/orb/layer6.png"),
            makeCharacterPath("mainChar/orb/layer1d.png"),
            makeCharacterPath("mainChar/orb/layer2d.png"),
            makeCharacterPath("mainChar/orb/layer3d.png"),
            makeCharacterPath("mainChar/orb/layer4d.png"),
            makeCharacterPath("mainChar/orb/layer5d.png"),
    };
}
