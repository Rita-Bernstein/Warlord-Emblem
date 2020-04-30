package WarlordEmblem.cards.mantle;

import WarlordEmblem.WarlordEmblem;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MantleCardJean extends AbstractMantleCard {
    public static final String ID = WarlordEmblem.makeID("MantleCardJean");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/Mantle/mantle_card_jean.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public MantleCardJean() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        upgradeAllCardsInGroup(p.hand);
        upgradeAllCardsInGroup(p.drawPile);
        upgradeAllCardsInGroup(p.discardPile);
        upgradeAllCardsInGroup(p.exhaustPile);
    }


    private void upgradeAllCardsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.cost % 2 == 0 && c.cost >0) {
                c.cost -= 1;
                c.costForTurn -= 1;
                c.isCostModified = true;
                if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                    c.superFlash();
                }
            }
        }
    }

    public AbstractCard makeCopy() {
        return new MantleCardJean();
    }
}
