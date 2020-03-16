package WarlordEmblem.cards.DeathKnight;

import java.util.ArrayList;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RunePerfusion extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("RunePerfusion");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/rune_perfusion.png");
    private static final int COST = 3;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;


    public final static String ERROR = EXTENDED_DESCRIPTION[0];

    public RunePerfusion() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.tags.add(CustomTagsEnum.Rune_Tag);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup handGroup = p.hand;
        ArrayList<AbstractCard> cardList = handGroup.group;
        for (AbstractCard card : cardList) {
            if (card == this || card.type == CardType.CURSE || card.type == CardType.STATUS)
                continue;
            if(card.cost > 0){
                card.cost = 0;
            }
            card.costForTurn = 0;
            card.isCostModified = true;
            card.superFlash(Color.GOLD.cpy());
        }
        super.useRune(this.magicNumber);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        int amount = super.getRuneCount();
        if (amount >= this.magicNumber) {
            return true;
        } else {
            this.cantUseMessage = ERROR;
            return false;
        }
    }

    public AbstractCard makeCopy() {
        return new RunePerfusion();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }

}
