package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class SevereWinter extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("SevereWinter");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/severe_winter.png");
    private static final int COST = 3;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


    public SevereWinter() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 10;
        this.damage = this.baseDamage;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CustomTagsEnum.Ice_Realm_Tag);
        this.tags.add(CustomTagsEnum.Realm_Tag);
        this.isMultiDamage = true;

        if(AbstractDungeon.player != null){
            if(hasIceRealm()){
                this.glowColor = Color.BLUE;
            }else {
                this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

       public static int countCards() {
             int count = 0;
             for (AbstractCard c : AbstractDungeon.player.hand.group) {
                   if (isStrike(c)) {
                         count++;
                       }
                 }
             for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                   if (isStrike(c)) {
                         count++;
                       }
                 }
             for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                   if (isStrike(c)) {
                         count++;
                       }
                 }
             return count;
           }

    public static boolean isStrike(AbstractCard c) { return c.hasTag(AbstractCard.CardTags.STRIKE); }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int frostCount = countCards();
           if (Settings.FAST_MODE) {
                 addToBot(new VFXAction(new BlizzardEffect(frostCount,AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
                 } else {
                  addToBot(new VFXAction(new BlizzardEffect(frostCount, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
                 }

        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));

        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
        if (!hasIceRealm()){
            addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -1), -1,
                            true, AbstractGameAction.AttackEffect.NONE));
        }
        else{
            addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -1 - AbstractDKCard.RealmMagicNumber),
                            -1 - AbstractDKCard.RealmMagicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }}

       public void calculateCardDamage(AbstractMonster mo) {
             int realBaseDamage = this.baseDamage;
             this.baseDamage += this.magicNumber * countCards();
             super.calculateCardDamage(mo);
             this.baseDamage = realBaseDamage;
             this.isDamageModified = (this.damage != this.baseDamage);
           }

       public void applyPowers() {
             int realBaseDamage = this.baseDamage;
             this.baseDamage += this.magicNumber * countCards();
             super.applyPowers();
             this.baseDamage = realBaseDamage;
             this.isDamageModified = (this.damage != this.baseDamage);
           }


    public AbstractCard makeCopy() {
        return new SevereWinter();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}
