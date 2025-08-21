package dreadbloonsurv.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GetLeakTargetAction extends AbstractGameAction {
    public boolean hasStarted;
    private AbstractMonster hovered;
    private java.util.function.Consumer<AbstractMonster> onChosen;
    private AbstractBloon bloon;
    private static final int SEGMENTS = 20;
    private Vector2[] points = new Vector2[20];
    private Vector2 controlPoint;
    private float arrowScaleTimer = 0.0F;
    private AbstractMonster hoveredMonster = null;

    public GetLeakTargetAction(AbstractBloon source, java.util.function.Consumer<AbstractMonster> onChosen) {
        this.bloon = source;
        this.onChosen = onChosen;
        this.hasStarted = false;
        this.isDone = false;
        GameCursor.hidden = true;
        for(int i = 0; i < this.points.length; ++i) {
            this.points[i] = new Vector2();
        }
    }

    public void update() {
        if (!this.hasStarted && this.isDone) { return; }

        // Cancel conditions
        if (InputHelper.justClickedRight || AbstractDungeon.isScreenUp || CInputActionSet.cancel.isJustPressed()) {
            CInputActionSet.cancel.unpress();
            stop();
            return;
        }

        // Hover logic
        hovered = null;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.hb.hovered && !m.isDying) { hovered = m; break; }
        }

        // Confirm
        if (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) {
            InputHelper.justClickedLeft = false;
            CInputActionSet.select.unpress();
            if (hovered != null && onChosen != null) onChosen.accept(hovered);
            stop();
        }
    }

    public void render(SpriteBatch sb) {
        if (this.isDone) return;
        if (hovered != null) hovered.renderReticle(sb);
        renderTargetingUi(sb);
    }

    private void stop() {
        this.isDone = true;
        GameCursor.hidden = false;
    }

    private void renderTargetingUi(SpriteBatch sb) {
        float x = (float)InputHelper.mX;
        float y = (float)InputHelper.mY;
        this.controlPoint = new Vector2(this.bloon.cX - (x - this.bloon.cY) / 4.0F, y + (y - this.bloon.cY - 40.0F * Settings.scale) / 2.0F);
        float arrowScale;
        if (this.hoveredMonster == null) {
            arrowScale = Settings.scale;
            this.arrowScaleTimer = 0.0F;
            sb.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
        } else {
            this.arrowScaleTimer += Gdx.graphics.getDeltaTime();
            if (this.arrowScaleTimer > 1.0F) {
                this.arrowScaleTimer = 1.0F;
            }

            arrowScale = Interpolation.elasticOut.apply(Settings.scale, Settings.scale * 1.2F, this.arrowScaleTimer);
            sb.setColor(new Color(1.0F, 0.2F, 0.3F, 1.0F));
        }

        Vector2 tmp = new Vector2(this.controlPoint.x - x, this.controlPoint.y - y);
        tmp.nor();
        this.drawCurvedLine(sb, new Vector2(this.bloon.cX, this.bloon.cY - 40.0F * Settings.scale), new Vector2(x, y), this.controlPoint);
        sb.draw(ImageMaster.TARGET_UI_ARROW, x - 128.0F, y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, arrowScale, arrowScale, tmp.angle() + 90.0F, 0, 0, 256, 256, false, false);
    }

    private void drawCurvedLine(SpriteBatch sb, Vector2 start, Vector2 end, Vector2 control) {
        float radius = 7.0F * Settings.scale;

        for(int i = 0; i < this.points.length - 1; ++i) {
            this.points[i] = Bezier.quadratic(this.points[i], (float)i / 20.0F, start, control, end, new Vector2());
            radius += 0.4F * Settings.scale;
            float angle;
            if (i != 0) {
                Vector2 tmp = new Vector2(this.points[i - 1].x - this.points[i].x, this.points[i - 1].y - this.points[i].y);
                angle = tmp.nor().angle() + 90.0F;
            } else {
                Vector2 tmp = new Vector2(this.controlPoint.x - this.points[i].x, this.controlPoint.y - this.points[i].y);
                angle = tmp.nor().angle() + 270.0F;
            }

            sb.draw(ImageMaster.TARGET_UI_CIRCLE, this.points[i].x - 64.0F, this.points[i].y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, radius / 18.0F, radius / 18.0F, angle, 0, 0, 128, 128, false, false);
        }
    }
}
