package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import WarlordEmblem.relics.RuneSword;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

public class RuneRegen extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("RuneRegen");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/rune_regen.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public RuneRegen() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //this.exhaust = true;
        this.tags.add(CustomTagsEnum.Rune_Tag);
        this.selfRetain = true;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager
                .addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.BLACK, p.hb.cX, p.hb.cY), 0.33F));
        AbstractDungeon.actionManager
                .addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.PURPLE, p.hb.cX, p.hb.cY), 0.33F));
        AbstractDungeon.actionManager
                .addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.CYAN, p.hb.cX, p.hb.cY), 0.0F));
        AbstractDungeon.actionManager
                .addToBottom(new VFXAction(p, new BorderLongFlashEffect(Color.MAGENTA), 0.0F, true));

        //plusRune(this.magicNumber);

        int extraRuneEnergy = super.getRuneMax() - super.getRuneCount();

        if(extraRuneEnergy >= this.magicNumber)
        {
            plusRune(this.magicNumber);
        }
        else
        {
            plusRune(extraRuneEnergy);
            plusMax(this.magicNumber-extraRuneEnergy);
        }
    }

    public AbstractCard makeCopy() {
        return new RuneRegen();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.exhaust = false;
        }
    }

}
