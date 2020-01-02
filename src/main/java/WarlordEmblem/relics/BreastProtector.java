package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BreastProtector extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("BreastProtector");

    private boolean used = false;
   
   public BreastProtector() { super(ID,new Texture(WarlordEmblem.assetPath("/img/relics/breast_protector.png")) , RelicTier.RARE, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }


    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (!this.used  && info.output > 20){
            this.used = true;
            return super.onAttackedToChangeDamage(info, 20);
        }
        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    public void atBattleStart() {
       this.used = false;
    }


    public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }




   public CustomRelic makeCopy() { return new BreastProtector(); }
 }