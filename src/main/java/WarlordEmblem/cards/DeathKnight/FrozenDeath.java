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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class FrozenDeath extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("FrozenDeath");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/frozen_death.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public FrozenDeath() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CustomTagsEnum.Ice_Realm_Tag);
        this.tags.add(CustomTagsEnum.Realm_Tag);

        if(AbstractDungeon.player != null){
            if(hasIceRealm()){
                this.glowColor = Color.BLUE;
            }else {
                this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.SKY)));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));

        if (m.hasPower("Weakened")) {
            if (!hasIceRealm())
                AbstractDungeon.actionManager
                        .addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false),
                                this.magicNumber, true, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            else
                AbstractDungeon.actionManager
                        .addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber + AbstractDKCard.RealmMagicNumber, false),
                                this.magicNumber + AbstractDKCard.RealmMagicNumber, true, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new FrozenDeath();
    }
}
