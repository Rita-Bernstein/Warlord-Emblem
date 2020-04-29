package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.powers.RegenerationPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class UndeadWill extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("UndeadWill");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/undead_will.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public UndeadWill() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 8;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // Kaidi 3是每回合恢复量 2是回合数
        // AbstractDungeon.actionManager
        // .addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.BLACK,
        // p.hb.cX, p.hb.cY), 0.33F));
        // AbstractDungeon.actionManager
        // .addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.PURPLE,
        // p.hb.cX, p.hb.cY), 0.33F));
        // AbstractDungeon.actionManager
        // .addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.CYAN,
        // p.hb.cX, p.hb.cY), 0.0F));
        AbstractDungeon.actionManager
                .addToBottom(new VFXAction(p, new BorderLongFlashEffect(Color.MAGENTA), 0.0F, true));

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager
                .addToBottom(new ApplyPowerAction(p, AbstractDungeon.player, new RegenerationPower(p, 2, 3), 2));
    }

    public AbstractCard makeCopy() {
        return new UndeadWill();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }
}
