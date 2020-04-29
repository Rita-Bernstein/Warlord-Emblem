package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 邪恶系技能
 * 
 * @author 王凯迪
 *
 */
public class CondemnStrike extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("CondemnStrike");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/condemn_strike.png");
    private static final int COST = 2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int UPGRADE_BONUS = 1;

    public CondemnStrike() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 19;
        this.tags.add(CardTags.STRIKE);
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
        int count = 0;
        for (AbstractCard c : p.hand.group) {
            if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                count++;
            }
        }
        int actualBase = this.damage;
        if (hasEvilRealm())
            actualBase += AbstractDKCard.SecondRealmMagicNumber;
        int actualDamage = (int) (actualBase * Math.pow(2, count));

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, actualDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public AbstractCard makeCopy() {
        return new CondemnStrike();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }
}
