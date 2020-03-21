package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.actions.FrostmourneAction;
import WarlordEmblem.patches.CardColorEnum;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import com.badlogic.gdx.graphics.Color;


public class Frostmourne extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("Frostmourne");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/frostmourne.png");
    private static final int COST = 3;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public Frostmourne() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 20;
        this.damage = this.baseDamage;
        this.baseBlock = 8;
        this.block = this.baseBlock;

        //this.selfRetain = true;
        //this.exhaust = true;
    }


    public void triggerOnEndOfTurnForPlayingCard() {
        addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
        addToBot(new ApplyPowerAction(AbstractDungeon.player,null,new StrengthPower(AbstractDungeon.player,this.magicNumber),this.magicNumber));
        super.triggerOnEndOfTurnForPlayingCard();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

             if (Settings.FAST_MODE) {
                  addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
                   for (int i = 0; i < 3; i++) {
                         addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
                       }
                } else {
                   addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
                   for (int i = 0; i < 3; i++) {
                         addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
                       }
                }
        addToBot(new FrostmourneAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 1));
    }

    public AbstractCard makeCopy() {
        return new Frostmourne();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isInnate = true;
            this.selfRetain = true;
        }
    }

}
