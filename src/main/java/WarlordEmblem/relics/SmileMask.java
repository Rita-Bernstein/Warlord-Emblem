package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SmileMask extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("SmileMask");

 
   
   public SmileMask() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/smile_mask.png")) , RelicTier.COMMON, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }


       public void onEquip() { this.counter = 0; }
    
    
    
       public void atTurnStart() {
             if (this.counter == -1) {
                   this.counter += 2;
                 } else {
                   this.counter++;
                 }
        
             if (this.counter == 3) {
                   this.counter = 0;
                   flash();
                 AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                 if(AbstractDungeon.player.hasRelic("Happy Flower")){
                     AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player,1));
                 }
                 AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player,1));
                 }
           }




   
   public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }
 
 
 
   
   public CustomRelic makeCopy() { return new SmileMask(); }
 }