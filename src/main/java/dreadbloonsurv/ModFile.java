package dreadbloonsurv;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.cards.cardvars.AbstractEasyDynamicVariable;
import dreadbloonsurv.potions.AbstractEasyPotion;
import dreadbloonsurv.relics.AbstractEasyRelic;
import dreadbloonsurv.util.ProAudio;
import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ModFile implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber {

    public static final String modID = "dreadbloonsurv"; //TODO: Change this.

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color DREADcharacterColor = new Color(0.482f, 0.290f, 0.192f, 1);
    public static Color SCRAPcharacterColor = new Color(0.482f, 0.482f, 0.482f, 1);

    public static final String SHOULDER1 = makeCharacterPath("mainChar/shoulder.png");
    public static final String SHOULDER2 = makeCharacterPath("mainChar/shoulder2.png");
    public static final String CORPSE = makeCharacterPath("mainChar/corpse.png");
    private static final String ATTACK_S_ART = makeImagePath("512/dreadbloon_attack.png");
    private static final String SKILL_S_ART = makeImagePath("512/dreadbloon_skill.png");
    private static final String POWER_S_ART = makeImagePath("512/dreadbloon_power.png");
    private static final String ATTACK_S_ART_SCRAP = makeImagePath("512/scrapborn_attack.png");
    private static final String SKILL_S_ART_SCRAP = makeImagePath("512/scrapborn_skill.png");
    private static final String POWER_S_ART_SCRAP = makeImagePath("512/scrapborn_power.png");
    private static final String CARD_ENERGY_S = makeImagePath("512/energy.png");
    private static final String TEXT_ENERGY = makeImagePath("512/text_energy.png");
    private static final String ATTACK_L_ART = makeImagePath("1024/dreadbloon_attack.png");
    private static final String SKILL_L_ART = makeImagePath("1024/dreadbloon_skill.png");
    private static final String POWER_L_ART = makeImagePath("1024/dreadbloon_power.png");
    private static final String ATTACK_L_ART_SCRAP = makeImagePath("1024/scrapborn_attack.png");
    private static final String SKILL_L_ART_SCRAP = makeImagePath("1024/scrapborn_skill.png");
    private static final String POWER_L_ART_SCRAP = makeImagePath("1024/scrapborn_power.png");
    private static final String CARD_ENERGY_L = makeImagePath("1024/energy.png");
    private static final String CHARSELECT_BUTTON = makeImagePath("charSelect/charButton.png");
    private static final String CHARSELECT_PORTRAIT = makeImagePath("charSelect/charBG.png");
    private static final String CHARSELECT_BUTTON_SCRAP = makeImagePath("charSelect/charButton.png");
    private static final String CHARSELECT_PORTRAIT_SCRAP = makeImagePath("charSelect/charBG.png");

    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    public ModFile() {
        BaseMod.subscribe(this);

        BaseMod.addColor(Enums.DREAD_COLOR, DREADcharacterColor, DREADcharacterColor, DREADcharacterColor,
                DREADcharacterColor, DREADcharacterColor, DREADcharacterColor, DREADcharacterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);

        BaseMod.addColor(Enums.SCRAP_COLOR, SCRAPcharacterColor, SCRAPcharacterColor, SCRAPcharacterColor,
                SCRAPcharacterColor, SCRAPcharacterColor, SCRAPcharacterColor, SCRAPcharacterColor,
                ATTACK_S_ART_SCRAP, SKILL_S_ART_SCRAP, POWER_S_ART_SCRAP, CARD_ENERGY_S,
                ATTACK_L_ART_SCRAP, SKILL_L_ART_SCRAP, POWER_L_ART_SCRAP,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCharacterPath(String resourcePath)
    {
        return modID + "Resources/images/char/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        ModFile thismod = new ModFile();
    }

    @Override
    public void receiveEditCharacters() {
        /*if (Loader.isModLoaded("RainWorld")) {
            System.out.println("Using RW Dreadblon!");
            BaseMod.addCharacter(new RWDreadCharaFile(RWDreadCharaFile.characterStrings.NAMES[1], ModFile.Enums.DREADBLOON),
                    CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, ModFile.Enums.DREADBLOON);
            BaseMod.addCharacter(new LiverTest(LiverTest.characterStrings.NAMES[1], ModFile.Enums.SCRAPBORN),
                    CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, ModFile.Enums.SCRAPBORN);
        }
        else*/
        {
            //System.out.println("not using RW Dreadblon :(");
            BaseMod.addCharacter(new DreadCharacterFile(DreadCharacterFile.characterStrings.NAMES[1], ModFile.Enums.DREADBLOON),
                    CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, ModFile.Enums.DREADBLOON);
        }
        new AutoAdd(modID)
            .packageFilter(AbstractEasyPotion.class)
            .any(AbstractEasyPotion.class, (info, potion) -> {
                if (potion.pool == null)
                    BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID);
                else
                    BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID, potion.pool);
            });
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID)
            .packageFilter(AbstractEasyDynamicVariable.class)
            .any(DynamicVariable.class, (info, var) -> 
                BaseMod.addDynamicVariable(var));
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + getLangString() + "/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIstrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/" + getLangString() + "/Orbstrings.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/" + getLangString() + "/Stancestrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/" + getLangString() + "/Potionstrings.json");
    }

    @Override
    public void receiveAddAudio() {
        for (ProAudio a : ProAudio.values())
            BaseMod.addAudio(makeID(a.name()), makePath("audio/" + a.name().toLowerCase() + ".ogg"));
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/" + getLangString() + "/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass DREADBLOON;
        @SpireEnum(name = "DREAD_COLOR")
        public static AbstractCard.CardColor DREAD_COLOR;
        @SpireEnum(name = "DREAD_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;

        @SpireEnum
        public static AbstractPlayer.PlayerClass SCRAPBORN;
        @SpireEnum(name = "SCRAP_COLOR")
        public static AbstractCard.CardColor SCRAP_COLOR;
        @SpireEnum(name = "SCRAP_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR_SCRAPBORN;

        /*
        @SpireEnum
        public static AbstractPlayer.PlayerClass BLOONARIUS;
        @SpireEnum(name = "BLONAR_COLOR")
        public static AbstractCard.CardColor BLONAR_COLOR;
        @SpireEnum(name = "BLONAR_COLOR")
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
        public static AbstractPlayer.PlayerClass BLASTA;
        @SpireEnum(name = "BLASTA_COLOR")
        public static AbstractCard.CardColor BLASTA_COLOR;
        @SpireEnum(name = "BLASTA_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR_BLASTAPOPOULOS;
        */
    }
}
