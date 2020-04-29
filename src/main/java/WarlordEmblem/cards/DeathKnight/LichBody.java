package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class LichBody extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("LichBody");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/lich_body.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public LichBody() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 7;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CustomTagsEnum.Ice_Realm_Tag);
        this.tags.add(CustomTagsEnum.Realm_Tag);

        if(AbstractDungeon.player != null){
            if(hasIceRealm()){
                this.glowColor = Color.BLUE;
            }else {
                this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        if (hasIceRealm())
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
                    new WeakPower(m, this.magicNumber + AbstractDKCard.RealmMagicNumber, false), this.magicNumber + AbstractDKCard.RealmMagicNumber, true));
        else
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
                    new WeakPower(m, this.magicNumber, false), this.magicNumber, true));
    }

    public AbstractCard makeCopy() {
        return new LichBody();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
        }
    }
}
