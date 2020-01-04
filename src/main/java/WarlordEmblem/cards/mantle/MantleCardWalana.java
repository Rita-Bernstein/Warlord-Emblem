package WarlordEmblem.cards.mantle;

import java.util.ArrayList;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.relics.mantle.MantleWalana;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MantleCardWalana extends AbstractMantleCard {
    public static final String ID = WarlordEmblem.makeID("MantleCardWalana");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/Mantle/mantle_card_walana.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;



    public MantleCardWalana() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasRelic(WarlordEmblem.makeID("MantleWalana"))) {
            MantleWalana relic = (MantleWalana) p.getRelic(WarlordEmblem.makeID("MantleWalana"));

            relic.useMantle();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        CardGroup group = p.drawPile;
        ArrayList<AbstractCard> cardList = group.group;
        for (AbstractCard card : cardList) {
            if (card.cost == 0) {
                this.cantUseMessage = EXTENDED_DESCRIPTION[0];
                return false;
            }
        }
        return true;
    }

    public AbstractCard makeCopy() {
        return new MantleCardWalana();
    }
}
