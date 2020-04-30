package WarlordEmblem.cards.mantle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import WarlordEmblem.WarlordEmblem;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MantleCardMarkzar extends AbstractMantleCard {
    public static final String ID = WarlordEmblem.makeID("MantleCardMarkzar");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/Mantle/mantle_card_markzar.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public MantleCardMarkzar() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // 添加5张随机稀有卡牌
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard rareCard ;
        for (int i = 0; i < 5; i++) {
            rareCard = AbstractDungeon.srcRareCardPool.group.get(
                    AbstractDungeon.cardRng.random(AbstractDungeon.srcRareCardPool.group.size() - 1)).makeCopy();
            if (rareCard.cost > 1) {
                rareCard.cost = 1;
                rareCard.costForTurn = 1;
                rareCard.isCostModified = true;
            }

            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(rareCard,1 ,true,true));
        }
    }

    public AbstractCard makeCopy() {
        return new MantleCardMarkzar();
    }

    /**
     * get rare card group
     * 
     * @param chosenClass
     * @return
     */
    public static CardGroup getEachRare(final AbstractPlayer.PlayerClass chosenClass) {
        final CardGroup everyRareCard = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        switch (chosenClass) {
        case IRONCLAD: {
            for (final Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
                if (c.getValue().color == AbstractCard.CardColor.RED
                        && c.getValue().rarity == AbstractCard.CardRarity.RARE) {
                    everyRareCard.addToBottom(c.getValue());
                }
            }
            break;
        }
        case THE_SILENT: {
            for (final Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
                if (c.getValue().color == AbstractCard.CardColor.GREEN
                        && c.getValue().rarity == AbstractCard.CardRarity.RARE) {
                    everyRareCard.addToBottom(c.getValue());
                }
            }
            break;
        }
            case DEFECT: {
            for (final Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
                if (c.getValue().color == AbstractCard.CardColor.BLUE
                        && c.getValue().rarity == AbstractCard.CardRarity.RARE) {
                    everyRareCard.addToBottom(c.getValue());
                }
            }
            break;
        }
        }
        return everyRareCard;
    }
}
