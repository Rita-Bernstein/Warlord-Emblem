package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class EvilWorm extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("EvilWorm");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/evil_worm.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;


    public EvilWorm() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 7;
        this.damage = this.baseDamage;
        this.exhaust = true;
        this.tags.add(CustomTagsEnum.Evil_Realm_Tag);
        this.tags.add(CustomTagsEnum.Realm_Tag);

        if(AbstractDungeon.player != null){
            if(hasEvilRealm()){
                this.glowColor = Color.GREEN;
            }else {
                this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.GREEN)));
        boolean needPoison = false;

        if (m.hasPower("Poison")) {
            needPoison = true;
        }

        if (!needPoison) {
            if(super.hasEvilRealm()){
                AbstractDungeon.actionManager
                        .addToBottom(new DamageAction(m, new DamageInfo(p, this.damage + AbstractDKCard.RealmMagicNumber, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }else {
                AbstractDungeon.actionManager
                        .addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }

        }
        else {
            if(super.hasEvilRealm()){
                AbstractDungeon.actionManager
                        .addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.damage + AbstractDKCard.RealmMagicNumber), this.damage));
            }else {
                AbstractDungeon.actionManager
                        .addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.damage), this.damage));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new EvilWorm();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

}
