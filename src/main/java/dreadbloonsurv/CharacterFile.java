package dreadbloonsurv;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import dreadbloonsurv.cards.Defend;
import dreadbloonsurv.cards.Leer;
import dreadbloonsurv.cards.Rumble;
import dreadbloonsurv.cards.Strike;
import dreadbloonsurv.cards.bloons.RockBloon;
import dreadbloonsurv.powers.bloons.LeadCoatingBloonPower;
import dreadbloonsurv.relics.TodoItem;

import java.util.ArrayList;

import static dreadbloonsurv.CharacterFile.Enums.DREADBLOON_COLOR;
import static dreadbloonsurv.ModFile.*;

public class CharacterFile extends CustomPlayer {

    static final String ID = makeID("Dreadbloon");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;


    public CharacterFile(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, makeCharacterPath("mainChar/orb/vfx.png"), null), new SpriterAnimation(
                makeCharacterPath("mainChar/static.scml")));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                80, 80, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            retVal.add(Strike.ID);
        }
        for (int i = 0; i < 1; i++) {
            retVal.add(Rumble.ID);
        }
        for (int i = 0; i < 1; i++) {
            retVal.add(Leer.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Defend.ID);
        }
        for (int i = 0; i < 2; i++) {
            retVal.add(RockBloon.ID);
        }
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(TodoItem.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
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

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 8;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return DREADBLOON_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return characterColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        System.out.println("YOU NEED TO SET getStartCardForEvent() in your " + getClass().getSimpleName() + " file!");
        return null;
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new CharacterFile(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return characterColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return characterColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass DREADBLOON;
        @SpireEnum(name = "DREADBLOON_COLOR")
        public static AbstractCard.CardColor DREADBLOON_COLOR;
        @SpireEnum(name = "DREADBLOON_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;

        /*
        @SpireEnum
        public static AbstractPlayer.PlayerClass BLOONARIUS;
        @SpireEnum(name = "BLOONARIUS_COLOR")
        public static AbstractCard.CardColor BLOONARIUS_COLOR;
        @SpireEnum(name = "BLOONARIUS_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR_BLOONARIUS;

        @SpireEnum
        public static AbstractPlayer.PlayerClass LYCH;
        @SpireEnum(name = "LYCH_COLOR")
        public static AbstractCard.CardColor LYCH_COLOR;
        @SpireEnum(name = "LYCH_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR_LYCH;

        @SpireEnum
        public static AbstractPlayer.PlayerClass VORTEX;
        @SpireEnum(name = "VORTEX_COLOR")
        public static AbstractCard.CardColor VORTEX_COLOR;
        @SpireEnum(name = "VORTEX_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR_VORTEX;

        @SpireEnum
        public static AbstractPlayer.PlayerClass PHAYZE;
        @SpireEnum(name = "PHAYZE_COLOR")
        public static AbstractCard.CardColor PHAYZE_COLOR;
        @SpireEnum(name = "PHAYZE_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR_PHAYZE;

        @SpireEnum
        public static AbstractPlayer.PlayerClass BLASTAPOPOULOS;
        @SpireEnum(name = "BLASTAPOPOULOS_COLOR")
        public static AbstractCard.CardColor BLASTAPOPOULOS_COLOR;
        @SpireEnum(name = "BLASTAPOPOULOS_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR_BLASTAPOPOULOS;
        */
    }
}
