package dreadbloonsurv;
 import basemod.BaseMod;
 import basemod.abstracts.CustomPlayer;
 import basemod.abstracts.CustomSavable;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.BitmapFont;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.badlogic.gdx.math.MathUtils;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.EnergyManager;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.events.city.Vampires;
 import com.megacrit.cardcrawl.helpers.FontHelper;
 import com.megacrit.cardcrawl.helpers.Hitbox;
 import com.megacrit.cardcrawl.helpers.ScreenShake;
 import com.megacrit.cardcrawl.helpers.TipHelper;
 import com.megacrit.cardcrawl.helpers.input.InputHelper;
 import com.megacrit.cardcrawl.localization.CharacterStrings;
 import com.megacrit.cardcrawl.localization.UIStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.DexterityPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.screens.CharSelectInfo;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
 import java.util.ArrayList;
 import java.util.Iterator;
 import dreadbloonsurv.cards.scrapborn.Defend;
import dreadbloonsurv.cards.scrapborn.Strike;
import org.example.Actions.ChaseCostUpdateAction;
import org.example.ModCore;
 import org.example.UIs.FoodPointUI;
 import org.example.UIs.SleepUI;
 import org.example.UIs.WorkLevelIndicator;
 import org.example.cards.attacks.Liver_Rock;
 import org.example.potions.WorkFlower;
 import org.example.powers.Inherent;
 import org.example.powers.OverSleepBuff;
 import org.example.powers.OverSleepDeBuff;
 import org.example.powers.SleepBuff;
 import org.example.tools.Tools;
 public class LiverTest extends CustomPlayer implements CustomSavable<LiverTest.ModSaveData> {
     private static final String[] ORB_TEXTURES;
           private static final float[] LAYER_SPEED;
           static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("dreadbloonsurv:Scrapborn");
           public FoodPointUI foodPointUI = new FoodPointUI(null);
           public SleepUI sleepUI = new SleepUI(null);
           private static final UIStrings UIs = CardCrawlGame.languagePack.getUIString("Liver:WorkLevel");
           public int workLevel;
           public int maxWorkLevel = 4, food, maxFood = 999, sleepfood = 999;
           private static Hitbox workLevelHitbox;
           private final WorkLevelIndicator workLevelIndicator;
           private final float X = 94.0F * Settings.scale;
           private final float Y = 340.0F * Settings.scale;
           private int meowtime = 0;
           public boolean onclick = false;
           private final Texture meow = new Texture("Images/CharacterImg/RainWorld/LiverLiHui1.png");
           private final Texture idle = new Texture("Images/CharacterImg/RainWorld/LiverLiHui.png");
           private final Texture sleep = new Texture("Images/CharacterImg/RainWorld/LiverLiHuiSleep.png");
           public LiverTest(String name, PlayerClass SCRAPBORN) {
             super(name, SCRAPBORN, ORB_TEXTURES, "Images/CharacterImg/RainWorld/orbs/vfx.png", LAYER_SPEED, null, null);
             if (this.hb == null)
                   this.hb = new Hitbox(0.0F, 0.0F, 110.0F * Settings.scale, 110.0F * Settings.scale);
             this.dialogX = this.drawX + 0.0F * Settings.scale;
             this.dialogY = this.drawY + 150.0F * Settings.scale;
             initializeClass("Images/CharacterImg/RainWorld/LiverLiHui.png", "Images/CharacterImg/RainWorld/Camp1.png", "Images/CharacterImg/RainWorld/Camp1.png", "Images/CharacterImg/RainWorld/Died_Lihui.png",
                      getLoadout(), 0.0F, 0.0F, 200.0F, 220.0F, new EnergyManager(3));
             float hitboxWidth = 110.0F;
             float hitboxHeight = 110.0F;
             workLevelHitbox = new Hitbox(this.X, this.Y, hitboxWidth, hitboxHeight);
             this.workLevel = 4;
             this.food = 0;
             this.workLevelIndicator = new WorkLevelIndicator();
             this.maxHealth = 9;
             BaseMod.addSaveField("Rainworld:ModSaveData", this);
           }
           public ArrayList<String> getStartingDeck() {
             ArrayList<String> retVal = new ArrayList<>();
             for (int x = 0; x < 5; x++)
                   retVal.add(Strike.ID);
             for (int i = 0; i < 5; i++)
                   retVal.add(Defend.ID);
             retVal.add("Liver:Rock");
             retVal.add("Liver:Spear");
             return retVal;
           }
           public ArrayList<String> getStartingRelics() {
             ArrayList<String> retVal = new ArrayList<>();
             retVal.add("Liver:RelicFruit");
             retVal.add(PrismaticShard.ID);
             return retVal;
           }
           public CharSelectInfo getLoadout() {
             return new CharSelectInfo(characterStrings.NAMES[0], characterStrings.TEXT[0], 9, 9, 0, 99, 5, this,

                         getStartingRelics(),
                         getStartingDeck(), false);
           }
           public String getTitle(AbstractPlayer.PlayerClass playerClass) {
             return characterStrings.NAMES[0];
           }
           public AbstractCard.CardColor getCardColor() {
             return ModFile.Enums.SCRAP_COLOR;
           }
           public AbstractCard getStartCardForEvent() {
             return new Liver_Rock();
           }
           public Color getCardTrailColor() {
             return ModCore.MY_COLOR;
           }
           public int getAscensionMaxHPLoss() {
             return 1;
           }
           public BitmapFont getEnergyNumFont() {
             return FontHelper.energyNumFontBlue;
           }
           public void doCharSelectScreenSelectEffect() {
             CardCrawlGame.sound.playA("MEOW", MathUtils.random(-0.15F, 0.15F));
             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
           }
           public ArrayList<CutscenePanel> getCutscenePanels() {
             ArrayList<CutscenePanel> panels = new ArrayList<>();
             panels.add(new CutscenePanel("Images/CharacterImg/RainWorld/Heart1.png"));
             panels.add(new CutscenePanel("Images/CharacterImg/RainWorld/Heart2.png"));
             panels.add(new CutscenePanel("Images/CharacterImg/RainWorld/Heart3.png"));
             panels.add(new CutscenePanel("Images/CharacterImg/RainWorld/Heart4.png"));
             return panels;
           }
           public String getCustomModeCharacterButtonSoundKey() {
             return "MEOW";
           }
           public String getLocalizedCharacterName() {
             return characterStrings.NAMES[0];
           }
           public AbstractPlayer newInstance() {
             return new LiverTest(this.name, this.chosenClass);
           }
           public String getSpireHeartText() {
             return characterStrings.TEXT[1];
           }
           public Color getSlashAttackColor() {
             return ModCore.MY_COLOR;
           }
           public String getVampireText() {
             return Vampires.DESCRIPTIONS[0];
           }
           public Color getCardRenderColor() {
             return ModCore.MY_COLOR;
           }
           public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
             return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL };
           }
           //Damage patch

       private void updateCardsOnDamage() {
             if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                   Iterator<AbstractCard> var1 = this.hand.group.iterator();
                   while (var1.hasNext()) {
                         AbstractCard c = var1.next();
                         c.tookDamage();
                       }
                   var1 = this.discardPile.group.iterator();
                   while (var1.hasNext()) {
                         AbstractCard c = var1.next();
                         c.tookDamage();
                       }
                   var1 = this.drawPile.group.iterator();
                   while (var1.hasNext()) {
                         AbstractCard c = var1.next();
                         c.tookDamage();
                       }
                 }
           }
           public void relive() {
             if (hasPower("Liver:WorkLevelLockBuff")) {
                   getPower("Liver:WorkLevelLockBuff").onSpecificTrigger();
                 } else {
                   lossWorkLevel();
                   if (hasRelic("Liver:MoonSkin") && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
                         AbstractDungeon.actionManager.addToTop(new GainBlockAction(this, 20));
                 }
             this.currentHealth = this.maxHealth;
             healthBarUpdatedEvent();
           }
           public void render(SpriteBatch sb) {
             if (hasPower("Liver:SleepBuff") || hasPower("Liver:OverSleepBuff")) {
                   this.img = this.sleep;
                 } else if (this.meowtime != 0) {
                   this.meowtime--;
                   if (this.meowtime == 0)
                         this.img = this.idle;
                 } else if (hasPower("Liver:Inherent")) {
                   if (this.onclick) {
                         this.img = this.meow;
                         this.meowtime = 30;
                       } else if (this.currentHealth > 0) {
                         this.img = this.idle;
                       }
                 }
             super.render(sb);
           }

       public void renderWorkLevelBar(SpriteBatch sb) {
             this.workLevelIndicator.update(this.workLevel);
             this.workLevelIndicator.render(sb);
             workLevelHitbox.move(this.X + 55.0F, this.Y + 55.0F);
             workLevelHitbox.render(sb);
             if (workLevelHitbox.hovered)
                   TipHelper.renderGenericTip(InputHelper.mX + 20.0F * Settings.scale, InputHelper.mY, UIs.TEXT[0], UIs.TEXT[1] + (this.workLevel + 1) + "/" + (

                               Tools.hasCard("Liver:stubborn") ? this.maxWorkLevel : (this.maxWorkLevel + 1)));
           }
           public void renderUI(SpriteBatch sb) {
             this.foodPointUI.render(sb);
             this.sleepUI.render(sb);
           }
           public void increaseMaxHp(int amount, boolean showEffect) {
             if (!Settings.isEndless || !AbstractDungeon.player.hasBlight("FullBelly")) {
                   addFood(amount);
                   heal(amount, true);
                   healthBarUpdatedEvent();
                 }
           }
           public int getFood() {
             return this.food;
           }
           public void forceIncreaseMaxHp(int amount, boolean showEffect) {
             super.increaseMaxHp(amount, showEffect);
           }
           public void addFood(int amount) {
             CardCrawlGame.sound.play("Liver:FoodUp");
             boolean full = (this.food == this.maxFood);
             this.food += amount;
             if (!full)
                   AbstractDungeon.effectsQueue.add(new TextAboveCreatureEffect(this.hb.cX - this.animX, this.hb.cY, characterStrings.TEXT[2] + amount, Settings.GREEN_TEXT_COLOR));
             if (this.food > this.maxFood) {
                   int damage = this.food - this.maxFood;
                   if (hasRelic("Liver:LongLegMushroom") && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                         boolean isdamageself = false;
                         for (AbstractMonster mo : (AbstractDungeon.getMonsters()).monsters) {
                               if (!mo.isDeadOrEscaped() && mo.currentHealth > 0) {
                                     AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(this, damage, DamageInfo.DamageType.THORNS)));
                                     isdamageself = true;
                                   }
                             }
                         if (isdamageself)
                               AbstractDungeon.actionManager.addToBottom(new DamageAction(this, new DamageInfo(this, damage, DamageInfo.DamageType.THORNS)));
                       }
                   if (hasPower("Liver:FatWorldBuff"))
                         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, (getPower("Liver:FatWorldBuff")).amount), (getPower("Liver:FatWorldBuff")).amount));
                 }
             this.food = Math.min(this.food, this.maxFood);
           }
           public void start() {
             addPower(new Inherent(this));
             this.sleepUI.picnum = 0;
           }
           public void sleep(boolean isover) {
             int cost = this.sleepfood;
             if (hasRelic("Liver:SleepPotions"))
                   cost = 3;
             if (isover)
                   cost = this.maxFood;
             if (this.food >= cost) {
                   this.food -= cost;
                   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SleepBuff(this, 1)));
                 } else if (!isover) {
                   this.food = 0;
                   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new OverSleepBuff(this, 1)));
                   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new OverSleepDeBuff(this, 1)));
                 }
           }
           public void onVictory() {
             super.onVictory();
             CardCrawlGame.sound.stop("Liver:MushRoom");
             CardCrawlGame.music.unsilenceBGM();
             if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss)
                   addMaxWorkLevel(1);
           }
           public void addWorkLevel(int amount) {
             if (!hasRelic("Mark of the Bloom")) {
                   if (!hasPower("Liver:WorkErrorBuff")) {
                         CardCrawlGame.sound.play("Liver:WorkErrorUp");
                         this.workLevel += amount;
                         if ((this.workLevel > this.maxWorkLevel || (this.workLevel == this.maxWorkLevel && Tools.hasCard("Liver:stubborn"))) &&
                                   hasPower("Liver:Inherent")) {
                               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 1), 1));
                               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new DexterityPower(this, 1), 1));
                             }
                       }
                   this.currentHealth = this.maxHealth;
                   this.workLevel = Math.min(this.workLevel, Tools.hasCard("Liver:stubborn") ? (this.maxWorkLevel - 1) : this.maxWorkLevel);
                   healthBarUpdatedEvent();
                 }
           }
           public void addMaxWorkLevel(int amount) {
             this.maxWorkLevel += amount;
             if (this.maxWorkLevel > 9)
                   this.maxWorkLevel = 9;
             this.currentHealth = this.maxHealth;
           }
           public void lossWorkLevel() {
             if (hasPower("Liver:WorkLevelLockBuff")) {
                   getPower("Liver:WorkLevelLockBuff").onSpecificTrigger();
                 } else if (this.workLevel > 0) {
                   if (hasRelic("Liver:FlowerAgain")) {
                         if ((getRelic("Liver:FlowerAgain")).counter != 0)
                               (getRelic("Liver:FlowerAgain")).counter = 0;
                         AbstractDungeon.actionManager.addToBottom(new ObtainPotionAction(new WorkFlower()));
                       }
                   this.workLevel--;
                   CardCrawlGame.sound.play("Liver:WorkErrorDown");
                   if (hasPower("Liver:CatComingBuff"))
                         getPower("Liver:CatComingBuff").onSpecificTrigger();
                 } else if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                   AbstractDungeon.actionManager.addToBottom(new DamageAction(this, new DamageInfo(this, 9999, DamageInfo.DamageType.NORMAL)));
                 } else {
                   AbstractDungeon.player.damage(new DamageInfo(null, 9999, DamageInfo.DamageType.HP_LOSS));
                 }
           }
           public void OnUseAckCard() {
             AbstractDungeon.actionManager.addToBottom(new ChaseCostUpdateAction());
           }
           public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
             if (c.type == AbstractCard.CardType.ATTACK) {
                   useFastAttackAnimation();
                   OnUseAckCard();
                 }
             c.calculateCardDamage(monster);
             if (c.cost == -1 && EnergyPanel.totalCount < energyOnUse && !c.ignoreEnergyOnUse)
                   c.energyOnUse = EnergyPanel.totalCount;
             if (c.cost == -1 && c.isInAutoplay)
                   c.freeToPlayOnce = true;
             c.use(this, monster);
             AbstractDungeon.actionManager.addToBottom(new UseCardAction(c, monster));
             if (!c.dontTriggerOnUseCard)
                   this.hand.triggerOnOtherCardPlayed(c);
             this.hand.removeCard(c);
             this.cardInUse = c;
             c.target_x = (Settings.WIDTH / 2);
             c.target_y = (Settings.HEIGHT / 2);
             if (c.costForTurn > 0 && !c.freeToPlay() && !c.isInAutoplay && (!hasPower("Corruption") || c.type != AbstractCard.CardType.SKILL))
                   this.energy.use(c.costForTurn);
             if (!this.hand.canUseAnyCard() && !this.endTurnQueued)
                   AbstractDungeon.overlayMenu.endTurnButton.isGlowing = true;
           }
           public void update() {
             super.update();
             this.foodPointUI.update();
             this.sleepUI.update();
             workLevelHitbox.update();
             if (!hasPower("Liver:SleepBuff") && !hasPower("Liver:OverSleepBuff"))
                   if (this.hb.hovered && InputHelper.justClickedLeft) {
                         onClick();
                         this.onclick = true;
                       } else {
                         this.onclick = false;
                       }
           }
           private void onClick() {
             CardCrawlGame.sound.playA("MEOW", MathUtils.random(-0.15F, 0.15F));
           }
           public static class ModSaveData {
             public int workLevel;
                     public int maxWorkLevel;
                     public ModSaveData(int workLevel, int maxWorkLevel) {
                   this.workLevel = workLevel;
                   this.maxWorkLevel = maxWorkLevel;
                 }
           }
           public ModSaveData onSave() {
             return new ModSaveData(this.workLevel, this.maxWorkLevel);
           }
           public void onLoad(ModSaveData modSaveData) {
             if (modSaveData == null)
                   modSaveData = new ModSaveData(2, 4);
             this.workLevel = modSaveData.workLevel;
             this.maxWorkLevel = modSaveData.maxWorkLevel;
             healthBarUpdatedEvent();
           }
           static {
             String[] arrayOfString = new String[11];
             arrayOfString[0] = "Images/CharacterImg/RainWorld/EPanel/layer5.png";
             arrayOfString[1] = "Images/CharacterImg/RainWorld/EPanel/layer4.png";
             arrayOfString[2] = "Images/CharacterImg/RainWorld/EPanel/layer3.png";
             arrayOfString[3] = "Images/CharacterImg/RainWorld/EPanel/layer2.png";
             arrayOfString[4] = "Images/CharacterImg/RainWorld/EPanel/layer1.png";
             arrayOfString[5] = "Images/CharacterImg/RainWorld/EPanel/layer0.png";
             arrayOfString[6] = "Images/CharacterImg/RainWorld/EPanel/layer5d.png";
             arrayOfString[7] = "Images/CharacterImg/RainWorld/EPanel/layer4d.png";
             arrayOfString[8] = "Images/CharacterImg/RainWorld/EPanel/layer3d.png";
             arrayOfString[9] = "Images/CharacterImg/RainWorld/EPanel/layer2d.png";
             arrayOfString[10] = "Images/CharacterImg/RainWorld/EPanel/layer1d.png";
             arrayOfString[3] = "Images/CharacterImg/null.png";
             arrayOfString[4] = "Images/CharacterImg/null.png";
             arrayOfString[9] = "Images/CharacterImg/null.png";
             arrayOfString[10] = "Images/CharacterImg/null.png";
             ORB_TEXTURES = arrayOfString;
             float[] arrayOfFloat = new float[10];
             arrayOfFloat[0] = -40.0F;
             arrayOfFloat[1] = -32.0F;
             arrayOfFloat[2] = 20.0F;
             arrayOfFloat[3] = -20.0F;
             arrayOfFloat[4] = 0.0F;
             arrayOfFloat[5] = -10.0F;
             arrayOfFloat[6] = -8.0F;
             arrayOfFloat[7] = 5.0F;
             arrayOfFloat[8] = -5.0F;
             arrayOfFloat[9] = 0.0F;
             LAYER_SPEED = arrayOfFloat;
           }
     }


/*
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.26
 */
