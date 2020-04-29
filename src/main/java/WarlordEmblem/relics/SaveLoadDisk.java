package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SaveLoadDisk extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("SaveLoadDisk");

 
   
   public SaveLoadDisk() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/save_load_disk.png")) , RelicTier.BOSS, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }
 
 

   public void atBattleStart() {
       flash();
       AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
       AbstractDungeon.player.currentHealth = (int)Math.ceil(AbstractDungeon.player.maxHealth*0.5);
   }




   
   public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }
 
 
 
   
   public CustomRelic makeCopy() { return new SaveLoadDisk(); }
 }