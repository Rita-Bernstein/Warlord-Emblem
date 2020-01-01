package WarlordEmblem.powers;

import WarlordEmblem.WarlordEmblem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DeafeningBlastPower extends AbstractPower {
  public static final String POWER_ID = WarlordEmblem.makeID("DeafeningBlastPower");
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(WarlordEmblem.makeID("DeafeningBlastPower"));
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public DeafeningBlastPower(AbstractCreature owner) {
    this.name = NAME;
    this.ID = WarlordEmblem.makeID("StrongPhysique");
    this.owner = owner;
    this.amount = -1;
    this.type = PowerType.DEBUFF;
    this.isTurnBased = false;
    updateDescription();
    //this.img = new Texture(WarlordEmblem.assetPath("img/powers/Exploding.png"));

    loadRegion("anger");
  }
  
  public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0]; }
  
  public void atEndOfRound() {
    if (this.amount == 0) {
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, WarlordEmblem.makeID("DeafeningBlastPower")));
    } else {
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, WarlordEmblem.makeID("DeafeningBlastPower"), 1));
    } 
  }
  
  public float atDamageGive(float paramFloat, DamageInfo.DamageType paramDamageType) { return (paramDamageType == DamageInfo.DamageType.NORMAL) ? (paramFloat * 0.5F) : paramFloat; }
}

