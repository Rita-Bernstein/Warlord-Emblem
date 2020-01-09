package WarlordEmblem.powers;

import WarlordEmblem.WarlordEmblem;
import com.badlogic.gdx.graphics.Texture;
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

public class LichkingWraithPower extends AbstractPower {
  public static final String POWER_ID = WarlordEmblem.makeID("LichkingWraithPower");
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(WarlordEmblem.makeID("LichkingWraithPower"));
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public LichkingWraithPower(AbstractCreature owner, int Amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = Amount;
    this.type = PowerType.BUFF;
    this.isTurnBased = false;
    updateDescription();
    this.img = new Texture(WarlordEmblem.assetPath("img/powers/LichkingWraithPower.png"));

    //loadRegion("anger");
  }
  
  public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0] + this.amount+ powerStrings.DESCRIPTIONS[1];}

  public void atStartOfTurnPostDraw() {
    flash();
    addToBot(new ApplyPowerAction(this.owner, this.owner, new RealmIncreasePower(this.owner, this.amount), this.amount));
  }
}
