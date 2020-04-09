package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RuneSword extends CustomRelic {

    public static final String ID = WarlordEmblem.makeID("RuneSword");

    public final static int MAX = 100;
    private final int INITIAL_MAX = 6;
    private final int INITIAL_REGEN = 2;
    public int max = 0;
    private int regen = 0;

    public int runeGain = 0;

    public RuneSword() {
        super(ID,new Texture( WarlordEmblem.assetPath("img/relics/rune_sword.png")), AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        flash();
        max = INITIAL_MAX;
        regen = INITIAL_REGEN;
        counter = 0;
        runeGain = 0;
    }

    @Override
    public void onVictory() {
        counter = -1;
    }

    @Override
    public void atTurnStart() {
        plusRune(regen);
    }




    public boolean isRuneFull() {
        return counter == max;
    }

        public  int getMaxRune(){
        return max;
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {

        if (this.max > -1 && this.counter > -1) {
            if (inTopPanel) {
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.max), this.currentX + 30.0F * Settings.scale, this.currentY + 16.0F * Settings.scale, Color.YELLOW);
            } else {
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.max), this.currentX + 30.0F * Settings.scale, this.currentY + 16.0F * Settings.scale, Color.YELLOW);
            }
        }
        /*
        if(AbstractDungeon.player != null && WarlordEmblem.RuneCountDisplay && this.counter > -1){
            if((((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoom) && !AbstractDungeon.player.isDead)){
                if (this.max > -1) {
                    if (inTopPanel) {
                        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.max), AbstractDungeon.player.drawX + 120.0F * Settings.scale, AbstractDungeon.player.drawY + 35.0F * Settings.scale, Color.YELLOW);
                    } else {
                        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.max), AbstractDungeon.player.drawX + 120.0F * Settings.scale, AbstractDungeon.player.drawY + 35.0F * Settings.scale, Color.YELLOW);
                    }
                }
                if (this.counter > -1) {
                    if (inTopPanel) {
                        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.counter),  AbstractDungeon.player.drawX + 120.0F * Settings.scale, AbstractDungeon.player.drawY +10.0F * Settings.scale, Color.WHITE);
                    } else {
                        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.counter), AbstractDungeon.player.drawX + 120.0F * Settings.scale, AbstractDungeon.player.drawY +10.0F * Settings.scale, Color.WHITE);
                    }
                }
            }
        }
*/
        super.renderCounter(sb, inTopPanel);
    }

    //符文管理==========================
    public void plusRune(int amount) {
        if(counter != max)
        flash();

        int actualAmount = amount;

        if(counter + amount > max){
            actualAmount = max - counter;
            counter = max;
        }else {
            counter += amount;
        }
        this.runeGain += actualAmount;
    }

    public void plusMax(int amount) {
        flash();
        max += amount;
    }

    public void decreaseMax(int amount) {
        flash();
        if(max <= amount)
        {
            max = 0;
            this.counter = 0;
            return;
        }

        max -= amount;
        if(this.counter > max){
            this.counter = max;
        }
    }

    public void useRune(int amount) {
        if (amount <= 0)
            return;
        if (amount > counter)
            amount = counter;
        flash();
        counter -= amount;
    }
    //符文管理==========================

    public AbstractRelic makeCopy() {
        return new RuneSword();
    }
}
