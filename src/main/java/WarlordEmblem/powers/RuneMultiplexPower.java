package WarlordEmblem.powers;

import WarlordEmblem.WarlordEmblem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class RuneMultiplexPower extends AbstractPower {
  public static final String POWER_ID = WarlordEmblem.makeID("RuneMultiplexPower");
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(WarlordEmblem.makeID("RuneMultiplexPower"));
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public RuneMultiplexPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.type = PowerType.BUFF;
    //this.isTurnBased = false;
    updateDescription();
    this.img = new Texture(WarlordEmblem.assetPath("img/powers/RuneMultiplexPower.png"));

    //loadRegion("anger");
  }

  
  public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];}

  public void atEndOfTurn(boolean isPlayer) {
    if (isPlayer)
      addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, WarlordEmblem.makeID("RuneMultiplexPower")));
  }
  }
