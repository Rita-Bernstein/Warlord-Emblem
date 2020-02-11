package WarlordEmblem.cards.Kael;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.character.Kael;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class SunStrike extends CustomCard {
  public static final String ID = WarlordEmblem.makeID("SunStrike");
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

  public static final String NAME = cardStrings.NAME;
  public static final String IMG = WarlordEmblem.assetPath("/img/cards/Kael/Kael_sun_strike.png");
  private static final int COST = 2;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final CardType TYPE = CardType.ATTACK;
  private static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;
  private static final CardRarity RARITY = CardRarity.SPECIAL;
  private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
  public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;



  public SunStrike() {

    super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    this.exhaust = true;
    this.isEthereal = true;
    this.tags.add(AbstractCard.CardTags.STRIKE);
    this.baseDamage = 35;

  }
  

  
  public boolean canUse(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
    if (Kael.SunStrike_ColdDown != 0){
      this.cantUseMessage = EXTENDED_DESCRIPTION[0] + Kael.SunStrike_ColdDown + EXTENDED_DESCRIPTION[1];
      return false;
    }
    return true;
  }
  
  public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
    AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.ORANGE)));
    AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));


    Kael.SunStrike_ColdDown = 2;
  }

  @Override
  public void triggerOnEndOfPlayerTurn() {
    if (Kael.SunStrike_ColdDown  >0)
      Kael.SunStrike_ColdDown  -= 1;
    super.triggerOnEndOfPlayerTurn();
  }

  @Override
    public AbstractCard makeCopy() { return new SunStrike(); }


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
