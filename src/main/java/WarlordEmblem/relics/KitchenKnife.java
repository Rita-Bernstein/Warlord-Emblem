package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class KitchenKnife extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("KitchenKnife");


   
   public KitchenKnife() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/kitchen_knife.png")) , RelicTier.UNCOMMON, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }

    public void onEquip() { this.counter = 0; }

    @Override
       public void onMonsterDeath(AbstractMonster m) {
        if (((m.isDying || m.currentHealth <= 0) && !m.halfDead &&!m.hasPower("Minion"))){
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(m, this));
            if(this.counter <= 8){
                AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player,AbstractDungeon.player,2));
                this.counter += 2;
                if (this.counter >8 ){this.counter = -1;}
            }
        }

        if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }

    }

    public void onVictory() {
        if(this.counter <=8){
            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player,AbstractDungeon.player,2));
        }
       this.counter = -1;
   }


   public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }

    public void atBattleStart() { this.counter = 0; }


   public CustomRelic makeCopy() { return new KitchenKnife(); }
 }