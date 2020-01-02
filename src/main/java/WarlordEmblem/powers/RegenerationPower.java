package WarlordEmblem.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RegenerationPower extends AbstractPower {
  public static final String POWER_ID = "Regeneration";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Regeneration");
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  
  private int healAmount;
  
  public RegenerationPower(AbstractCreature owner, int turns, int heal) {
    this.name = NAME;
    this.ID = "Regeneration";
    this.owner = owner;
    this.amount = turns;
    this.healAmount = heal;
    updateDescription();
    loadRegion("regen");
    this.type = AbstractPower.PowerType.BUFF;
  }


  
  public void updateDescription() { this.description = DESCRIPTIONS[0] + this.healAmount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]; }


  
  public void atEndOfTurn(boolean isPlayer) {
    flash();
    AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, this.healAmount));
    
    if (this.amount == 0) {
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Regeneration"));
    } else {
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Regeneration", 1));
    } 
  }
}
