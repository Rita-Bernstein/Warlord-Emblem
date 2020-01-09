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

public class RealmRiderPower extends AbstractPower {
  public static final String POWER_ID = WarlordEmblem.makeID("RealmRiderPower");
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(WarlordEmblem.makeID("RealmRiderPower"));
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public RealmRiderPower(AbstractCreature owner) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.type = PowerType.BUFF;
    this.isTurnBased = false;
    updateDescription();
    this.img = new Texture(WarlordEmblem.assetPath("img/powers/RealmRiderPower.png"));

    //loadRegion("anger");
  }
  
  public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0] ;}

}
