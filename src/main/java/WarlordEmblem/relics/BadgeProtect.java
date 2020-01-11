package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BadgeProtect extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("BadgeProtect");

    public boolean trigger = false;
   
   public BadgeProtect() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/badge_protect.png")) , RelicTier.COMMON, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }





    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.currentBlock == 0 || this.trigger) {
            this.trigger = false;
            flash();
            stopPulse();
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new WeakPower(mo, 1,true)));
            }
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }


    public void atTurnStart() {
        this.trigger = false;
        if (AbstractDungeon.player.currentBlock == 0) {
            beginLongPulse();
        }
    }


    public int onPlayerGainedBlock(float blockAmount) {
        if (blockAmount > 0.0F) {
            stopPulse();
        }

        return MathUtils.floor(blockAmount);
    }



    public void onVictory() { stopPulse(); }


   public CustomRelic makeCopy() { return new BadgeProtect(); }
 }