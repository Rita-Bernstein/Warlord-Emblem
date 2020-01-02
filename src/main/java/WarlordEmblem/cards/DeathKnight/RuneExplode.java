package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class RuneExplode extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("RuneExplode");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("/img/cards/DeathKnight/rune_explode.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public RuneExplode() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 4;
        this.baseMagicNumber = 4;
        this.magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
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
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new MindblastEffect(p.dialogX, p.dialogY ,p.flipHorizontal)));

        int amount = super.getRuneCount();
        if (amount > this.magicNumber)
            amount = this.magicNumber;
        for (int i = 0; i < amount; i++)
            AbstractDungeon.actionManager
                    .addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_HEAVY));
        super.useRune(amount);
    }

    public AbstractCard makeCopy() {
        return new RuneExplode();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

}
