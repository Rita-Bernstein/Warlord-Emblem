package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;

public class Execute extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("Execute");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/execute.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public Execute() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.shuffleBackIntoDrawPile = false;
        int actualDamage = this.damage;
        //addToBot(new AnimateJumpAction(p));

        addToBot(new VFXAction(new GoldenSlashEffect(m.hb.cX + 60.0F * Settings.scale, m.hb.cY, true), 0.1F));
        addToBot(new DamageAction(m,new DamageInfo(p, actualDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (m.currentHealth <= m.maxHealth / 2) {
            addToBot(new VFXAction(new GoldenSlashEffect(m.hb.cX - 60.0F * Settings.scale, m.hb.cY, true), 0.1F));
            addToBot(new DamageAction(m,new DamageInfo(p, actualDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            this.shuffleBackIntoDrawPile = true;
        }

    }

    public AbstractCard makeCopy() {
        return new Execute();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }
}
