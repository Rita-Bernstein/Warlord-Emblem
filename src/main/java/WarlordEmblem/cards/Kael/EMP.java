package WarlordEmblem.cards.Kael;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.character.Kael;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import java.util.ArrayList;

public class EMP extends CustomCard {
  public static final String ID = WarlordEmblem.makeID("EMP");
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

  public static final String NAME = cardStrings.NAME;
  public static final String IMG = WarlordEmblem.assetPath("/img/cards/Kael/Kael_elect_pulse.png");
  private static final int COST = 1;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final CardType TYPE = AbstractCard.CardType.SKILL;
  private static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;
  private static final CardRarity RARITY = CardRarity.SPECIAL;
  private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
  public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;





  public EMP() {
    super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    this.exhaust = true;
    this.isEthereal = true;


  }

  @Override
  public boolean canUse(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
    if (Kael.EMP_ColdDown != 0){
      this.cantUseMessage = EXTENDED_DESCRIPTION[0] + Kael.EMP_ColdDown + EXTENDED_DESCRIPTION[1];
      return false;
    }
    return true;
  }


  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player,AbstractDungeon.player,mo.currentBlock));
      AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(mo, AbstractDungeon.player));
    }

    Kael.EMP_ColdDown = 2;
  }

  @Override
  public void triggerOnEndOfPlayerTurn() {
    if (Kael.EMP_ColdDown >0)
      Kael.EMP_ColdDown -= 1;
    super.triggerOnEndOfPlayerTurn();
  }

  @Override
  public AbstractCard makeCopy() { return new EMP(); }

  @Override
  public boolean canUpgrade() { return false; }


  @Override
  public void upgrade() {
    if (!this.upgraded) {
    upgradeName();
    upgradeBaseCost(1);
  }  }
}
