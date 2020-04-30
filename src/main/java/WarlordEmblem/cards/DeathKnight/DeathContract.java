package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class DeathContract extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("DeathContract");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/death_contract.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;


    public final static String ERROR = EXTENDED_DESCRIPTION[0];

    public DeathContract() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 10;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
        this.tags.add(CustomTagsEnum.Blood_Realm_Tag);
        this.tags.add(CustomTagsEnum.Realm_Tag);

        if(AbstractDungeon.player != null){
            if(hasBloodRealm()){
                this.glowColor = Color.RED;
            }else {
                this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectList
                .add(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        super.useRune(3);
        p.increaseMaxHp(magicNumber, true);
        addToBot(new HealAction(p,p,AbstractDKCard.BaseRealmMagicNumber));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        int amount = super.getRuneCount();
        if (amount >= 3) {
            return true;
        } else {
            this.cantUseMessage = ERROR;
            return false;
        }
    }

    public AbstractCard makeCopy() {
        return new DeathContract();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
        }
    }
}
