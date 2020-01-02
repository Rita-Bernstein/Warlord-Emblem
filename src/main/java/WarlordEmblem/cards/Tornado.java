package WarlordEmblem.cards;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.character.Kael;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class Tornado extends CustomCard {
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(WarlordEmblem.makeID("Tornado"));
  public static final String ID = WarlordEmblem.makeID("Tornado");
  public static final String NAME = cardStrings.NAME;
  public static final String IMG = WarlordEmblem.assetPath("/img/cards/Kael/Kael_strong_wind.png");
  private static final int COST = 2;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final CardType TYPE = CardType.ATTACK;
  private static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;
  private static final CardRarity RARITY = CardRarity.RARE;
  private static final CardTarget TARGET = CardTarget.ENEMY;
  public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;




  public Tornado() {
    super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    this.exhaust = true;
    this.isEthereal = true;
    this.baseDamage = 0;
    this.baseMagicNumber = 5;
    this.magicNumber = this.baseMagicNumber;
    this.isMultiDamage = true;


  }


  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.SKY)));


    if (m.hasPower("Poison"))
      this.baseDamage += m.getPower("Poison").amount;
    if (m.hasPower("Weakened"))
      this.baseDamage += m.getPower("Weakened").amount;
    if (m.hasPower("Vulnerable"))
      this.baseDamage += m.getPower("Vulnerable").amount;


      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m,p, "Poison"));
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m,p, "Weakened"));
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p, "Vulnerable"));


    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
  }




  @Override
  public void triggerOnEndOfPlayerTurn() {
    if (Kael.Tornado_ColdDown >0)
      Kael.Tornado_ColdDown  -= 1;
  }

  @Override
    public AbstractCard makeCopy() { return new Tornado(); }

  @Override
  public boolean canUpgrade() { return false; }


  @Override
  public void upgrade() {}
}

