package WarlordEmblem.powers;

import WarlordEmblem.WarlordEmblem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class WrathOfSindragosaPower extends AbstractPower {
  public static final String POWER_ID = WarlordEmblem.makeID("WrathOfSindragosaPower");
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(WarlordEmblem.makeID("WrathOfSindragosaPower"));
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public WrathOfSindragosaPower(AbstractCreature owner, int Amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = Amount;
    this.type = PowerType.BUFF;
    this.isTurnBased = false;
    updateDescription();
    //this.img = new Texture(WarlordEmblem.assetPath("img/powers/WrathOfSindragosaPower.png"));

    loadRegion("anger");
  }
  
  public void updateDescription() {
      this.description = powerStrings.DESCRIPTIONS[0] + powerStrings.DESCRIPTIONS[7]
              +powerStrings.DESCRIPTIONS[1] + this.amount + powerStrings.DESCRIPTIONS[2] + powerStrings.DESCRIPTIONS[7]
              +powerStrings.DESCRIPTIONS[3] + this.amount + powerStrings.DESCRIPTIONS[4] + powerStrings.DESCRIPTIONS[7]
              +powerStrings.DESCRIPTIONS[5] + this.amount + powerStrings.DESCRIPTIONS[6];
  }

     public void onUseCard(AbstractCard card, UseCardAction action) {
         if (card.type == AbstractCard.CardType.ATTACK) {
           for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
             AbstractDungeon.actionManager.addToBottom(
                     new ApplyPowerAction(mo, AbstractDungeon.player, new WeakPower(mo, this.amount, false), this.amount, true));
           }
            flash();
           }

       else if (card.type == AbstractCard.CardType.SKILL) {
           addToBot(new DamageAllEnemiesAction(null,DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
           flash();
         }

       else if (card.type == AbstractCard.CardType.POWER) {
           for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
             AbstractDungeon.actionManager.addToBottom(
                     new ApplyPowerAction(mo, AbstractDungeon.player, new StrengthPower(mo, -this.amount), -this.amount, true));
           }
           flash();
       }
       }
  public void atEndOfTurn(boolean isPlayer) { addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, WarlordEmblem.makeID("WrathOfSindragosaPower"))); }
}
