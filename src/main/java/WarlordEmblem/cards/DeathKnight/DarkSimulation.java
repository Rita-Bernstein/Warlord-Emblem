package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.actions.DarkSimulationAction;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DarkSimulation extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("DarkSimulation");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/dark_simulation.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    public static final Logger logger = LogManager.getLogger(WarlordEmblem.class.getSimpleName());


    public DarkSimulation() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> RealmCardChoices = new ArrayList<AbstractCard>();
        ArrayList<AbstractCard.CardTags> CardTypeMark = new ArrayList<CardTags>();

        if (hasBloodRealm()) {
            CardTypeMark.add(CustomTagsEnum.Blood_Realm_Tag);
        }
        if (hasIceRealm()) {
            CardTypeMark.add(CustomTagsEnum.Ice_Realm_Tag);
        }
        if (hasEvilRealm()) {
            CardTypeMark.add(CustomTagsEnum.Evil_Realm_Tag);
        }

        if(CardTypeMark.size() != 0){
            addToBot(new DarkSimulationAction(returnRandomRealmCardInCombat(CardTypeMark)));
     }
    }



    public static ArrayList<AbstractCard> returnRandomRealmCardInCombat(ArrayList<AbstractCard.CardTags> tags) {
           ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
           ArrayList<AbstractCard> returnCard = new ArrayList<AbstractCard>();
        for (AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
            if (c.hasTag(CustomTagsEnum.Realm_Tag)  && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }
        for (AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
            if (c.hasTag(CustomTagsEnum.Realm_Tag)  && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }
        for (AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
            if (c.hasTag(CustomTagsEnum.Realm_Tag)  && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }


            int temp;
           for(int i = 0;i < 3;i++){
               temp = AbstractDungeon.cardRng.random(list.size() - 1);
               returnCard.add(list.get(temp));
               list.remove(temp);
           }

           return returnCard;
       }



        public AbstractCard makeCopy() { return new DarkSimulation(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

}
