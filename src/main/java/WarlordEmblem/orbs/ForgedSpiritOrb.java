package  WarlordEmblem.orbs;

import WarlordEmblem.WarlordEmblem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;

public class ForgedSpiritOrb extends AbstractOrb {
  public static final String ORB_ID = WarlordEmblem.makeID("ForgedSpirit");
  private static final OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString(WarlordEmblem.makeID("ForgedSpirit"));
  public static final String[] DESC = orbStrings.DESCRIPTION;
  private float vfxTimer;
  private float vfxIntervalMin;
  private float vfxIntervalMax;
  private static final float ORB_WAVY_DIST = 0.04F;
  private static final float PI_4 = 12.566371F;

  public ForgedSpiritOrb() {
    this.vfxTimer = 1.0F;
    this.vfxIntervalMin = 0.1F;
    this.vfxIntervalMax = 0.4F;
    this.ID = ORB_ID;
    this.img = ImageMaster.ORB_PLASMA;
    this.name = orbStrings.NAME;
    this.baseEvokeAmount = 2;
    this.evokeAmount = this.baseEvokeAmount;
    this.basePassiveAmount = 1;
    this.passiveAmount = this.basePassiveAmount;
    updateDescription();
    this.angle = MathUtils.random(360.0F);
    this.channelAnimTimer = 0.5F;
  }
  
  public void updateDescription() {
    applyFocus();
    this.description = orbStrings.DESCRIPTION[0] + this.passiveAmount + orbStrings.DESCRIPTION[1] + this.evokeAmount + orbStrings.DESCRIPTION[2];
  }
  
  public void onEvoke() {
    AbstractPlayer abstractPlayer = AbstractDungeon.player;
    MonsterGroup monsterGroup = (AbstractDungeon.getCurrRoom()).monsters;
    AbstractMonster abstractMonster = monsterGroup.getRandomMonster(true);
    if (abstractMonster != null) {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.evokeAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster, AbstractDungeon.player, new VulnerablePower(abstractMonster, 2, false), 2, true));
    } 
  }
  
  public void onStartOfTurn() {
    AbstractPlayer abstractPlayer = AbstractDungeon.player;
    MonsterGroup monsterGroup = (AbstractDungeon.getCurrRoom()).monsters;
    AbstractMonster abstractMonster = monsterGroup.getRandomMonster(true);
    if (abstractMonster != null) {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.passiveAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster, AbstractDungeon.player, new VulnerablePower(abstractMonster, 1, false), 1, true));
    } 
  }
  
  public void updateAnimation() {
       super.updateAnimation();
         this.angle += Gdx.graphics.getDeltaTime() * 45.0F;
         this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer < 0.0F) {
             AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(this.cX, this.cY));
             this.vfxTimer = MathUtils.random(this.vfxIntervalMin, this.vfxIntervalMax);
    } 
  }
  
  public void render(SpriteBatch sb) {
    sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.c.a / 2.0F));

    sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
    sb.setBlendFunction(770, 1);
    sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, -this.angle, 0, 0, 96, 96, false, false);
    sb.setBlendFunction(770, 771);


    sb.setColor(this.c.a*0.8F);
    sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 12.0F, 0, 0, 96, 96, false, false);

    renderText(sb);
    this.hb.render(sb);
  }
  
  public AbstractOrb makeCopy() { return new ForgedSpiritOrb(); }
  
  public void playChannelSFX() { CardCrawlGame.sound.play("ORB_PLASMA_CHANNEL", 0.1F); }
}

