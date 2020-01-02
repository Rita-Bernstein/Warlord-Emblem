package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
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
public class CurseStrike extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("CurseStrike");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("/img/cards/DeathKnight/curse_strike.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;


    public final static String ERROR = EXTENDED_DESCRIPTION[0];

    public CurseStrike() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 12;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if (hasEvilRealm())
            return true;
        for (AbstractCard c : p.hand.group) {
            if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                return true;
            }
        }
        this.cantUseMessage = ERROR;
        return false;
    }

    public AbstractCard makeCopy() {
        return new CurseStrike();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }
}
