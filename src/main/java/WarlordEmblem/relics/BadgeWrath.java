package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class BadgeWrath extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("BadgeWrath");

 
   
   public BadgeWrath() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/badge_wrath.png")) , RelicTier.UNCOMMON, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }
 
 

   public void atBattleStart() {
       flash();
            for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
                AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,AbstractDungeon.player,new StrengthPower(m,-1)));
            }
            if(AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("BadgeBless"))){
                for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
                    AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, 1,false)));
                }
            }
   }




   
   public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }
 
 
 
   
   public CustomRelic makeCopy() { return new BadgeWrath(); }
 }