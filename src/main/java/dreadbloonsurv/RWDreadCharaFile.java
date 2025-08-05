package dreadbloonsurv;

import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dreadbloonsurv.util.modifedclasses.ImmunityStance;
import org.example.UIs.WorkLevelIndicator;
import org.example.potions.WorkFlower;
import org.example.tools.Tools;
import org.example.UIs.FoodPointUI;
import org.example.UIs.SleepUI;

import static dreadbloonsurv.ModFile.makeID;

public class RWDreadCharaFile extends DreadCharacterFile implements CustomSavable<RWDreadCharaFile.ModSaveData> {
    public RWDreadCharaFile(String name, PlayerClass setClass) {
        super(name, setClass);
        this.baseKarmaHP = this.maxHealth;
        float hitboxWidth = 110.0F;
        float hitboxHeight = 110.0F;
        workLevelHitbox = new Hitbox(this.X, this.Y, hitboxWidth, hitboxHeight);
        this.workLevel = 4;
        this.workLevelIndicator = new WorkLevelIndicator();
        this.maxHealth = this.baseKarmaHP;
        BaseMod.addSaveField(makeID("KarmaSaveData"), this);
    }
    public int baseKarmaHP;
    public int workLevel;
    public int maxWorkLevel = 4; //no food or sleep mech.
    public FoodPointUI foodPointUI = new FoodPointUI(null);
    public SleepUI sleepUI = new SleepUI(null);
    private final Hitbox workLevelHitbox;
    private static final UIStrings UIs = CardCrawlGame.languagePack.getUIString("Liver:WorkLevel");
    private final WorkLevelIndicator workLevelIndicator;
    private final float X = 94.0F * Settings.scale;
    private final float Y = 340.0F * Settings.scale;

    public void relive() {
         if (hasPower("Liver:WorkLevelLockBuff")) {
               getPower("Liver:WorkLevelLockBuff").onSpecificTrigger();
             } else {
               lossWorkLevel();
               if (hasRelic("Liver:MoonSkin") && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
                     AbstractDungeon.actionManager.addToTop(new GainBlockAction(this, 20));
             }
         this.currentHealth = this.maxHealth;
                    AbstractDungeon.actionManager.addToTop(new ChangeStanceAction(new ImmunityStance()));
         healthBarUpdatedEvent();
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
                   this.maxHealth = (int) Math.ceil(Math.max(10.0, (double)75/this.workLevel+1));
                   this.currentHealth = this.maxHealth;
                   this.workLevel = Math.min(this.workLevel, Tools.hasCard("Liver:stubborn") ? (this.maxWorkLevel - 1) : this.maxWorkLevel);
                   healthBarUpdatedEvent();
                 }
           }
           public void addMaxWorkLevel(int amount) {
             this.maxWorkLevel += amount;
             if (this.maxWorkLevel > 3) //Max is 4 for dread.
                   this.maxWorkLevel = 3;
             this.currentHealth = this.maxHealth;
                        this.maxHealth -= this.maxHealth / 2;
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
                            this.maxHealth += this.maxHealth;
                   CardCrawlGame.sound.play("Liver:WorkErrorDown");
                   if (hasPower("Liver:CatComingBuff"))
                         getPower("Liver:CatComingBuff").onSpecificTrigger();
                 } else if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                   AbstractDungeon.actionManager.addToBottom(new DamageAction(this, new DamageInfo(this, 9999, DamageInfo.DamageType.NORMAL)));
                 } else {
                   AbstractDungeon.player.damage(new DamageInfo(null, 9999, DamageInfo.DamageType.HP_LOSS));
                 }
           }
           public void update() {
             super.update();
               foodPointUI.update();
               sleepUI.update();
               workLevelHitbox.update();
           }


    public void renderUI(SpriteBatch sb) {
        this.foodPointUI.render(sb);
        this.sleepUI.render(sb);
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
                   modSaveData = new ModSaveData(4, 4);
             this.workLevel = modSaveData.workLevel;
             this.maxWorkLevel = modSaveData.maxWorkLevel;
             healthBarUpdatedEvent();
           }

}

