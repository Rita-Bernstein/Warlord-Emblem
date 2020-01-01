package WarlordEmblem.powers;

import WarlordEmblem.WarlordEmblem;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ColdSnapPower extends AbstractPower {
  public static final String POWER_ID = WarlordEmblem.makeID("ColdSnapPower");
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(WarlordEmblem.makeID("ColdSnapPower"));
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public ColdSnapPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = WarlordEmblem.makeID("StrongPhysique");
    this.owner = owner;
    this.amount = amount;
    this.type = PowerType.DEBUFF;
    this.isTurnBased = false;
    updateDescription();
    //this.img = new Texture(WarlordEmblem.assetPath("img/powers/Exploding.png"));

    loadRegion("anger");
  }
  
  public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1]; }
  
  public int onAttacked(DamageInfo paramDamageInfo, int paramInt) {
    if (paramInt < this.owner.currentHealth && paramInt > 0 && paramDamageInfo.owner != null && paramDamageInfo.type == DamageInfo.DamageType.NORMAL) {
      flash();
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, AbstractDungeon.player, new WeakPower(this.owner, 1, false), 1, true));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, AbstractDungeon.player, new VulnerablePower(this.owner, 1, false), 1, true));
      AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    } 
    return paramInt;
  }
}
