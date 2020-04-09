package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class OldTorch extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("OldTorch");

   
   public OldTorch() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/old_torch.png")) , RelicTier.COMMON, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }



    public void atBattleStart() {
       this.counter = 0;
        if (AbstractDungeon.player.hasRelic("Lantern")){
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
   }

    
       public void atTurnStart() {
             if (this.counter < 2) {
                   this.counter++;
                 }
             if (this.counter >= 2) {
                   flash();
                 AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                 AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
                   this.counter = -1;
                   this.grayscale = true;
                 }
           }

    public void onVictory() { this.counter = -1; }
   
   public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }
 
 
 
   
   public CustomRelic makeCopy() { return new OldTorch(); }
 }