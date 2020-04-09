package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;

public class BreastProtector extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("BreastProtector");
    private int amount = 28;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractCreature");
    public static final String[] TEXT = uiStrings.TEXT;

   public BreastProtector() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/breast_protector.png")) , RelicTier.UNCOMMON, CustomRelic.LandingSound.FLAT); }



   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }


    public void onEquip() {
        if (!Settings.isEndless || !AbstractDungeon.player.hasBlight("FullBelly")) {

       AbstractDungeon.player.maxHealth += amount;
        AbstractDungeon.effectsQueue.add(new TextAboveCreatureEffect(AbstractDungeon.player.hb.cX - AbstractDungeon.player.animX, AbstractDungeon.player.hb.cY, TEXT[2] + Integer.toString(amount), Settings.GREEN_TEXT_COLOR));
        AbstractDungeon.topPanel.panelHealEffect();
        AbstractDungeon.effectsQueue.add(new HealEffect(AbstractDungeon.player.hb.cX - AbstractDungeon.player.animX, AbstractDungeon.player.hb.cY, amount));
        AbstractDungeon.player.healthBarUpdatedEvent();
        }
   }

    public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 48); }

   public CustomRelic makeCopy() { return new BreastProtector(); }
 }