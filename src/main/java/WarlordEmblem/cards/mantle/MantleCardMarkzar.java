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
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public MantleCardMarkzar() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // 添加5张随机稀有卡牌
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup group = getEachRare(AbstractDungeon.player.chosenClass);
        ArrayList<AbstractCard> cardList = group.group;

        for (int i = 0; i < 5; i++) {
            Collections.shuffle(cardList);
            AbstractCard card = cardList.get(0);
            if (card.cost > 1) {
                card.cost = 1;
                card.costForTurn = 1;
                card.isCostModified = true;
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(card,
                    1, true, false,false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
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
