package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SadisticPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class EvilEager extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("EvilEager");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/evil_eager.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;


    private final static int MAX = 3;

    public EvilEager() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CustomTagsEnum.Evil_Realm_Tag);
        this.tags.add(CustomTagsEnum.Realm_Tag);

        if(AbstractDungeon.player != null){
            if(hasEvilRealm()){
                this.glowColor = Color.GREEN;
            }else {
                this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.GREEN)));
        int amount = super.getRuneCount();
        if (amount > MAX)
            amount = MAX;

        if (hasEvilRealm()) {
            AbstractDungeon.actionManager
                    .addToBottom(new ApplyPowerAction(p, p, new SadisticPower(p, 2*amount + AbstractDKCard.RealmMagicNumber), 2*amount + AbstractDKCard.RealmMagicNumber));
        }else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SadisticPower(p, 2*amount), 2*amount));
        }
        super.useRune(amount);
    }

    public AbstractCard makeCopy() {
        return new EvilEager();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}