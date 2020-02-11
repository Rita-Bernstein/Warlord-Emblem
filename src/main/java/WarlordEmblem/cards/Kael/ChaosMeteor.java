package WarlordEmblem.cards.Kael;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.character.Kael;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;

public class ChaosMeteor extends CustomCard {
  public static final String ID = WarlordEmblem.makeID("ChaosMeteor");
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

  public static final String NAME = cardStrings.NAME;
  public static final String IMG = WarlordEmblem.assetPath("/img/cards/Kael/Kael_chaos_failing_stone.png");
  private static final int COST = 3;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final CardType TYPE = CardType.ATTACK;
  private static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;
  private static final CardRarity RARITY = CardRarity.SPECIAL;
  private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
  public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;



  public ChaosMeteor() {
    super(ID, NAME, IMG,COST,DESCRIPTION,TYPE, COLOR, RARITY, TARGET);
    this.exhaust = true;
    this.isEthereal = true;
    this.baseDamage = 35;

    this.baseMagicNumber = 2;
    this.magicNumber =  this.baseMagicNumber;
  }



  @Override
  public boolean canUse(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
          if (Kael.ChaosMeteor_ColdDown != 0){
          this.cantUseMessage = EXTENDED_DESCRIPTION[0] + Kael.ChaosMeteor_ColdDown + EXTENDED_DESCRIPTION[1];
            return false;
            }


    return true;
  }

  @Override
  public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {


    AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.RED)));
    addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));

    for (AbstractMonster abstractMonster : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new VerticalImpactEffect(abstractMonster.hb.cX + abstractMonster.hb.width / 4.0F, abstractMonster.hb.cY - abstractMonster.hb.height / 4.0F)));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster, AbstractDungeon.player, new VulnerablePower(abstractMonster, this.magicNumber, false), this.magicNumber, true));
    }


    Kael.ChaosMeteor_ColdDown = 4;
  }


  @Override
  public void triggerOnEndOfPlayerTurn() {
    if (Kael.ChaosMeteor_ColdDown >0)
      Kael.ChaosMeteor_ColdDown -= 1;
    super.triggerOnEndOfPlayerTurn();
  }

  @Override
  public AbstractCard makeCopy() { return new ChaosMeteor(); }


  @Override
  public boolean canUpgrade() { return false; }


  @Override
  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBaseCost(1);
    }
  }

}


/* Location:              F:\BaiduYunDownload\杀戮尖塔_战神徽章补丁合集\杀戮尖塔_战神徽章补丁V3.8(支持20180927版本)\athena.jar!\org\dakiler\slayhelper\a\a\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */