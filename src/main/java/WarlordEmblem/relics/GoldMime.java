package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class GoldMime extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("GoldMime");


   
   public GoldMime() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/goldmime.png")) , RelicTier.UNCOMMON, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }
/*
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type == DamageInfo.DamageType.HP_LOSS && damageAmount >= 0 && info.output > 0){
            this.flash();
            AbstractDungeon.player.gainGold(info.output);
        }


        return super.onAttacked(info, damageAmount);
    }
*/
    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.owner != null  && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0 && info.output > 0) {
            this.flash();

            AbstractDungeon.player.gainGold(damageAmount);
        }
        return super.onAttackedToChangeDamage(info, damageAmount);
    }


    public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }




   public CustomRelic makeCopy() { return new GoldMime(); }
 }