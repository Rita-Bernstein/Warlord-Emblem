package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import WarlordEmblem.powers.RealmIncreasePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.WhirlwindAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RealmStorm extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("RealmStorm");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/rune_storm.png");
    private static final int COST = 2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


    public RealmStorm() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.isMultiDamage = true;
        this.baseDamage = 13;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 1;
        if(AbstractDungeon.player.hasPower(RealmIncreasePower.POWER_ID)){
            RealmIncreasePower rip = (RealmIncreasePower) AbstractDungeon.player.getPower(RealmIncreasePower.POWER_ID);
            amount += rip.amount;
        }

        addToBot(new WhirlwindAction(p, this.multiDamage, this.damageTypeForTurn, true, amount));
    }

    public AbstractCard makeCopy() {
        return new RealmStorm();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }
}
