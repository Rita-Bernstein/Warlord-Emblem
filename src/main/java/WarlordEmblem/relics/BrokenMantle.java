package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BrokenMantle extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("BrokenMantle");

 
   
   public BrokenMantle() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/broken_mantle.png")) , RelicTier.COMMON, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }
 
 

   public void atBattleStart() {
       if (AbstractDungeon.player.currentHealth <= (int)Math.floor(AbstractDungeon.player.maxHealth*0.5)){
           flash();
           AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
           if(AbstractDungeon.player.hasRelic("CloakClasp")){
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, null, new DexterityPower(AbstractDungeon.player, 4)));
           }
           AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, null, new DexterityPower(AbstractDungeon.player, 3)));
       }
         }




   
   public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }
 
 
 
   
   public CustomRelic makeCopy() { return new BrokenMantle(); }
 }