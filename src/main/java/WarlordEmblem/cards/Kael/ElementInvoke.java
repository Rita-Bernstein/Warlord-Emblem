package WarlordEmblem.cards.Kael;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.character.Kael;
import WarlordEmblem.orbs.ForgedSpiritOrb;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class ElementInvoke extends CustomCard {
  public static final String ID = WarlordEmblem.makeID("ElementInvoke");
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

  public static final String NAME = cardStrings.NAME;
  public static final String IMG = WarlordEmblem.assetPath("/img/cards/Kael/Kael_element_invoke.png");
  private static final int COST = 0;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final CardType TYPE = AbstractCard.CardType.SKILL;
  private static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;
  private static final CardRarity RARITY = CardRarity.SPECIAL;
  private static final CardTarget TARGET = AbstractCard.CardTarget.SELF;
  public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;

  public ElementInvoke() {
    super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    this.exhaust = true;
    this.isEthereal = true;

  }







  @Override
  public boolean canUse(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
    boolean bool = super.canUse(paramAbstractPlayer, paramAbstractMonster);
    if (!bool)
      return false; 
    if (paramAbstractPlayer.orbs == null || paramAbstractPlayer.orbs.size() < 3) {
      this.cantUseMessage = EXTENDED_DESCRIPTION[0];
      return false;
    } 
    if (!(paramAbstractPlayer.orbs.get(0) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot) && !(paramAbstractPlayer.orbs.get(1) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot) && !(paramAbstractPlayer.orbs.get(2) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot))
      return true; 
    this.cantUseMessage = EXTENDED_DESCRIPTION[0];
    return false;
  }




  @Override
  public void applyPowers() {
    super.applyPowers();

    //判断栏位数量
    AbstractPlayer abstractPlayer = AbstractDungeon.player;
    if (abstractPlayer.orbs == null || abstractPlayer.orbs.size() < 3)
      return;


    //获取非空充能球数量
    int i = 0;
    int j;
    for (j = abstractPlayer.orbs.size() - 1; j >= 0; j--) {
      AbstractOrb abstractOrb = (AbstractOrb)abstractPlayer.orbs.get(j);
      if (!(abstractOrb instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot)) {
        i = j;
        break;
      }
    }


    if (i < 2){
      return;
    }
      /*===============失败返回================*/

    /*判断充能球类型*/
    byte FireOrb = 0;
    byte FrostOrb = 0;
    byte LightningOrb = 0;
    for (int k = i; k > i - 3; k--) {
      AbstractOrb abstractOrb = AbstractDungeon.player.orbs.get(k);

      if (abstractOrb instanceof com.megacrit.cardcrawl.orbs.Frost) {
        FrostOrb++;
      } else if (abstractOrb instanceof com.megacrit.cardcrawl.orbs.Lightning) {
        LightningOrb++;
      } else  {
        FireOrb++;
      }
    }
    /*判断充能球类型*/

//更新卡牌描述=================

    String str = "";

    if (FrostOrb == 3) {

      if (Kael.KaelColdSnap_ColdDown > 0)
        str = "," + Kael.KaelColdSnap_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[2] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new KaelColdSnap();
      initializeDescription();
    }//急速冷却

    else if (FrostOrb == 2 && LightningOrb == 1) {


      if (Kael.GhostWalk_ColdDown > 0)
        str = "," + Kael.GhostWalk_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[3] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new GhostWalk();
      initializeDescription();
    }//幽灵漫步

    else if (LightningOrb == 2 && FireOrb == 1) {
      if (Kael.Alacrity_ColdDown > 0)
        str = "," + Kael.Alacrity_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[4] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new Alacrity();
      initializeDescription();
    }//灵动迅捷

    else if (FireOrb == 3) {
      if (Kael.SunStrike_ColdDown > 0)
        str = "," + Kael.SunStrike_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[5] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new SunStrike();
      initializeDescription();
    }//阳炎冲击

    else if (LightningOrb == 3) {
      if (Kael.EMP_ColdDown > 0)
        str = "," + Kael.EMP_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[6] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new EMP();
      initializeDescription();
    }//电磁脉冲

    else if (FireOrb == 2 && LightningOrb == 1) {

      if (Kael.ChaosMeteor_ColdDown > 0)
        str = "," + Kael.ChaosMeteor_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[7] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new ChaosMeteor();
      initializeDescription();
    }//混沌陨石

    else if (FrostOrb == 2 && FireOrb == 1) {

      if (Kael.IceWall_ColdDown > 0)
        str = "," + Kael.IceWall_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION+EXTENDED_DESCRIPTION[8] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new IceWall();
      initializeDescription();
    }//寒冰之墙

    else if (FrostOrb == 1 && LightningOrb == 1 && FireOrb == 1) {

      if (Kael.DeafeningBlast_ColdDown > 0)
        str = "," + Kael.DeafeningBlast_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION+EXTENDED_DESCRIPTION[9] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new DeafeningBlast();
      initializeDescription();
    }//超震声波


    else if (LightningOrb == 2 && FrostOrb == 1) {

      if (Kael.Tornado_ColdDown > 0)
        str = "," + Kael.Tornado_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION+EXTENDED_DESCRIPTION[10] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new Tornado();
      initializeDescription();
    }//"强袭飓风"


    else if (FireOrb == 2 && FrostOrb == 1) {

      if (Kael.ForgeSpirit_ColdDown > 0)
        str = "," + Kael.ForgeSpirit_ColdDown + EXTENDED_DESCRIPTION[1];
      this.rawDescription = DESCRIPTION+EXTENDED_DESCRIPTION[11] + str + EXTENDED_DESCRIPTION[12];
      this.cardsToPreview = new ForgeSpirit();
      initializeDescription();
    }//熔炉精灵

    else {
      this.rawDescription = DESCRIPTION+"";
      initializeDescription();
    } 
  }

  //更新卡牌描述=================

  @Override
  public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
    //同上 判断栏位数量
    if (paramAbstractPlayer.orbs == null || paramAbstractPlayer.orbs.size() < 3)
      return;

//同上 判断栏位数量
    int i = 0;
    int j;
    for (j = paramAbstractPlayer.orbs.size() - 1; j >= 0; j--) {
      AbstractOrb abstractOrb = (AbstractOrb)paramAbstractPlayer.orbs.get(j);
      if (!(abstractOrb instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot)) {
        i = j;
        break;
      }
    }

    if (i < 2)
      return;

    //同上

    byte FireOrb = 0;
    byte FrostOrb = 0;
    byte LightningOrb = 0;
    for (int k = i; k > i - 3; k--) {
      AbstractOrb abstractOrb = AbstractDungeon.player.orbs.get(k);

      if (abstractOrb instanceof com.megacrit.cardcrawl.orbs.Frost) {
        FrostOrb++;
      } else if (abstractOrb instanceof com.megacrit.cardcrawl.orbs.Lightning) {
        LightningOrb++;
      } else  {
        FireOrb++;
      }
    }

    if (FrostOrb == 3) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new KaelColdSnap(), false));
    } else if (FrostOrb == 2 && LightningOrb == 1) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new GhostWalk(), false));
    } else if (LightningOrb == 2 && FireOrb == 1) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Alacrity(), false));
    } else if (FireOrb == 3) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new SunStrike(), false));
    } else if (LightningOrb == 3) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new EMP(), false));
    } else if (FireOrb == 2 && LightningOrb == 1) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new ChaosMeteor(), false));
    } else if (FrostOrb == 2 && FireOrb == 1) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new IceWall(), false));
    } else if (FrostOrb == 1 && LightningOrb == 1 && FireOrb == 1) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new DeafeningBlast(), false));
    } else if (LightningOrb == 2 && FrostOrb == 1) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Tornado(), false));
    } else if (FireOrb == 2 && FrostOrb == 1) {
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new ForgeSpirit(), false));
    } 
  }


  private void orbTypes(){

  }


  @Override
  public AbstractCard makeCopy() { return new ElementInvoke(); }

  @Override
  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
      initializeDescription();
      this.isEthereal = false;
    }
  }
}


