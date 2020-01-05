package WarlordEmblem.cards.mantle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import WarlordEmblem.WarlordEmblem;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MantleCardKazakus extends AbstractMantleCard {
    public static final String ID = WarlordEmblem.makeID("MantleCardKazakus");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/Mantle/mantle_card_kazakus.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;


    public MantleCardKazakus() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.cardsToPreview = new MantleCardKazakusPotion();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int v1 = AbstractDungeon.cardRandomRng.random(0,9);
        int v2 = AbstractDungeon.cardRandomRng.random(0,9);
        int v3 = AbstractDungeon.cardRandomRng.random(0,9);
        AbstractDungeon.actionManager
                .addToBottom(new MakeTempCardInHandAction(new MantleCardKazakusPotion(v1, v2, v3), false));
    }


    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        CardGroup group = p.drawPile;
        HashSet<String> nameSet = new HashSet<String>();
        ArrayList<AbstractCard> cardList = group.group;
        for (AbstractCard card : cardList) {
            String name = card.name;
            if (nameSet.contains(name)) {
                this.cantUseMessage = EXTENDED_DESCRIPTION[0];
                return false;
            }
            nameSet.add(name);
        }
        return true;
    }

    public AbstractCard makeCopy() {
        return new MantleCardKazakus();
    }

}
